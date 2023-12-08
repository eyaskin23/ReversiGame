package view;

import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Draws out our board and hexagons.
 */
public class DrawUtils extends JPanel implements ReversiView, DrawInterfaceMocker {
  private Player currentPlayer;

  private ReadOnlyBoardModel board;
  boolean isClicked = false;
  private HexShape firstClickedHex;
  private HexShape hoveredHex;
  PlayerActionListener playerAction;
  JLabel scoreLabel;
  private Player humanPlayer;
  private Player aiPlayer;

  private boolean isGameOverHandled = false;


  /**
   * Sets the player action for the view. Allowing the view to recognize the
   */
  public void setEventListener(PlayerActionListener playerAction) {
    this.playerAction = playerAction;
  }

  @Override
  public void update() {
    repaint();

    if (board instanceof Board) {
      handleGameOver();
    }
  }

  /**
   * Updates the board of the ReadOnlyBoardModel to reflect the changes on the normal board.
   */
  public void updateBoard(ReadOnlyBoardModel newBoardModel) {
    if (newBoardModel instanceof Board) {
      board = newBoardModel;
    }
    repaint();
  }

  /**
   * Constructs a board to be made in the game.
   */
  public DrawUtils(ReadOnlyBoardModel board) {
    this.board = board;
    JButton quitButton;

    setPreferredSize(new Dimension(650, 650));
    setBackground(new Color(this.getWindowWidth() / 11, 34, 83));

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          HexShape newClickedHex = findHex(e.getX(), e.getY());
          if (firstClickedHex != null && firstClickedHex.equals(newClickedHex)) {
            firstClickedHex = null;
            System.out.println("Deselected: " + newClickedHex.getColumn()
                    + ", " + newClickedHex.getRow());
          } else {
            firstClickedHex = newClickedHex;
          }
          repaint();
        } catch (Exception ignored) {
        }
      }
    });
    addMouseMotionListener(new MouseAdapter() {
      public void mouseMoved(MouseEvent e) {
        try {
          HexShape currentHover = findHex(e.getX(), e.getY());
          if (hoveredHex != currentHover) {
            hoveredHex = currentHover;
            repaint();
          }
        } catch (Exception ignored) {
        }
      }
    });
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (firstClickedHex != null) {
            playerAction.onPlayerMove(Integer.parseInt(firstClickedHex.getColumn()),
                    Integer.parseInt(firstClickedHex.getRow()));
            System.out.println("Chosen: "
                    + firstClickedHex.getColumn() + ", " + firstClickedHex.getRow());
          } else {
            System.out.println("No hex selected");
          }
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          playerAction.onPass();
          System.out.println("Pass");
        }
      }
    });
    setFocusable(true);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton passTurnButton = new JButton("Pass Turn");
    passTurnButton.addActionListener((ActionEvent e) -> playerAction.onPass());
    bottomPanel.add(passTurnButton);

    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    bottomPanel.add(quitButton);

    setLayout(new BorderLayout());
    add(bottomPanel, BorderLayout.SOUTH);
  }

  private void triggerAi(int i, int i1) {
    // playerAction.onPlayerMove(i, i1);
  }

  /**
   * Finds a certain hexagon based on the mouse position.
   */
  @Override
  public HexShape findHex(int mouseX, int mouseY) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    double hexHeight = 3.0 / 2 * hexSize;
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard
            * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexesMade;
      if (currentRow <= midPoint) {
        currentHexesMade = midPoint + currentRow + 1;
      } else {
        currentHexesMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexesMade);

      for (int h = 0; h < currentHexesMade; h++) {
        int offSet = (sizeOfEntireBoard - currentHexesMade)
                * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters;
        int centerY = (int) Math.round(startY + currentRow * hexHeight);

        if (isPointInHex(mouseX, mouseY, centerX, centerY, hexSize)) {
          if (currentRow <= midPoint) {
            return board.getCurrentHex(currentRow, h + spacesBefore);
          } else {
            return board.getCurrentHex(currentRow, h);
          }
        }

      }

    }
    return null;
  }

  /**
   * Returns the hex size.
   */
  @Override
  public int getHexSize() {
    return (getWindowHeight() * getWindowWidth()) / 15000;
  }

  /**
   * Determines if the mouse hovers over a hex.
   */
  @Override
  public boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize) {
    double xDistance = Math.abs(x - centerX);
    double yDistance = Math.abs(y - centerY);

    if (xDistance > hexSize * Math.sqrt(3) / 2) {
      return false;
    }
    return (yDistance <= ((double) (hexSize * 3) / 2) / 2);
  }

  /**
   * Gets the window width.
   */
  @Override
  public int getWindowWidth() {
    return Math.min(this.getWidth(), 650);
  }

  /**
   * Paints a board.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g, (Board) board);

    if (isClicked) {
      isClicked = false;
    }
  }

  /**
   * Draws a single hexagon.
   */
  @Override
  public void drawEachHexagon(Graphics g, HexShape hex, int centerX, int centerY,
                              int hexSize, PlayerType playerType) {
    int sides = 6;
    Polygon hexagon = new Polygon();
    int radius = hexSize / 2;

    for (int i = 0; i < sides; i++) {
      double angle = 2 * Math.PI / sides * i + Math.PI / 6;
      int x = (int) (centerX + hexSize * Math.cos(angle));
      int y = (int) (centerY + hexSize * Math.sin(angle));
      hexagon.addPoint(x, y);
    }

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else if (hex.equals(hoveredHex)) {
      g.setColor(Color.GREEN);
    } else {
      g.setColor(Color.LIGHT_GRAY);
    }

    g.fillPolygon(hexagon);
    g.setColor(Color.BLACK);
    g.drawPolygon(hexagon);

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else {
      g.setColor(getColor(playerType));
    }
    g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
  }


  /**
   * Sets the player type for color purposes.
   */
  @Override
  public Color getColor(PlayerType playerType) {
    Color color;
    switch (playerType) {
      case BLACK:
        color = Color.BLACK;
        break;
      case WHITE:
        color = Color.WHITE;
        break;
      default:
        color = Color.LIGHT_GRAY;
        break;
    }
    return color;
  }

  /**
   * Builds the board out.
   */
  @Override
  public void drawBoard(Graphics g, Board board) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    double hexHeight = 3.0 / 2 * hexSize;
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard
            * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexesMade;

      if (currentRow <= midPoint) {
        currentHexesMade = midPoint + currentRow + 1;
      } else {
        currentHexesMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexesMade);

      for (int h = 0; h < currentHexesMade; h++) {
        HexShape currentHex;
        if (currentRow <= midPoint) {
          currentHex = board.getCurrentHex(currentRow, spacesBefore + h);
        } else {
          currentHex = board.getCurrentHex(currentRow, h);
        }
        int offSet = (sizeOfEntireBoard - currentHexesMade)
                * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters;
        int centerY = (int) Math.round(startY + currentRow * hexHeight);

        PlayerButton playerButton = new PlayerButton(currentHex);
        playerButton.setBounds(centerX - hexSize / 2,
                centerY - hexSize / 2, hexSize, hexSize);
        playerButton.setVisible(true);

        drawEachHexagon(g, currentHex, centerX, centerY, hexSize, currentHex.getPlayerType());
      }
    }
  }

  /**
   * Returns the window height.
   */
  @Override
  public int getWindowHeight() {
    return Math.min(this.getHeight(), 650);
  }

  /**
   * Handles the state of the view, when the game is over.
   */
  public void handleGameOver() {
    if (!isGameOverHandled) {
      isGameOverHandled = true;
      int blackScore = board.getScoreBlack();
      int whiteScore = board.getScoreWhite();
      String message;
      if (blackScore > whiteScore) {
        message = "Black won with a score of " + blackScore + " to " + whiteScore + "!";
      } else if (whiteScore > blackScore) {
        message = "White won with a score of " + whiteScore + " to " + blackScore + "!";
      } else {
        message = "It's a draw! Both players scored " + blackScore + ".";
      }
      JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
      Window window = SwingUtilities.getWindowAncestor(this);
      if (window != null) {
        window.dispose();
      }

    }

  }

  /**
   * Signals to the user that they cannot move.
   */
  public void showInvalidMoveMessage() {
    JOptionPane.showMessageDialog(this, "Invalid move, please try again.",
            "Invalid Move", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Signals to the user that passed their turn.
   */
  public void showThatIPassedTurnMessage() {
    JOptionPane.showMessageDialog(this, "You have passed your turn.",
            "Turn Passed", JOptionPane.PLAIN_MESSAGE);
    this.requestFocusInWindow();
  }

  /**
   * Signals to the user it is now their turn.
   */
  public void itIsNowYourTurnMessage() {
    JOptionPane.showMessageDialog(this, "It's your turn.",
            "Your Turn", JOptionPane.INFORMATION_MESSAGE);
    this.requestFocusInWindow();
  }

  /**
   * Checks if the Game Over was handled.
   */
  public boolean getGameOverHandleState() {
    return isGameOverHandled;
  }

  /**
   * Resets the Game Over boolean.
   */
  public void resetGameOverHandled() {
    isGameOverHandled = false;
  }

  /**
   * Tells the player it is not their turn.
   */
  public void itIsNotYourTurnMessage() {
    JOptionPane.showMessageDialog(this, "It is not your turn Yet!",
            "Invalid Move", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Updates the score on the frame.
   */
  public void updateScore(int blackScore, int whiteScore) {
    scoreLabel.setText("Black: " + blackScore + " White: " + whiteScore);
  }

  /**
   * Setter for the score label.
   */
  public void setScoreLabel(JLabel scoreLabel) {
    this.scoreLabel = scoreLabel;
  }
}
