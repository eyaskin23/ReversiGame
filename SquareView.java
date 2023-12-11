package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.players.PlayerType;
import model.BoardModel;
import model.HexShape;
import model.ReadOnlyBoardModel;

public class SquareView extends JPanel implements ReversiView, DrawInterfaceMocker {
  private ReadOnlyBoardModel board;
  boolean isClicked = false;
  private HexShape firstClickedHex;
  private HexShape hoveredHex;
  PlayerActionListener playerAction;
  JLabel scoreLabel;
  private DrawUtils view;
  private boolean isGameOverHandled = false;

  public SquareView(BoardModel board) {
    this.view = new DrawUtils(board);
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
          hoveredHex = currentHover;
          repaint();
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

  public void setEventListener(PlayerActionListener playerActionListener) {
    this.playerAction = playerActionListener;
  }

  @Override
  public void handleGameOver() {
    view.handleGameOver();
  }

  @Override
  public void showInvalidMoveMessage() {
    JOptionPane.showMessageDialog(this, "Invalid move, please try again.",
            "Invalid Move", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void showThatIPassedTurnMessage() {
    JOptionPane.showMessageDialog(this, "You have passed your turn.",
            "Turn Passed", JOptionPane.PLAIN_MESSAGE);
    this.requestFocusInWindow();
  }

  @Override
  public void itIsNowYourTurnMessage() {
    JOptionPane.showMessageDialog(this, "It's your turn.",
            "Your Turn", JOptionPane.INFORMATION_MESSAGE);
    this.requestFocusInWindow();
  }

  /**
   * Paints a board.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g, board);

    if (isClicked) {
      isClicked = false;
    }
  }

  @Override
  public void updateBoard(BoardModel newBoardModel) {
    if (newBoardModel instanceof BoardModel) {
      this.board = newBoardModel;
    }
    repaint();
  }


  @Override
  public boolean getGameOverHandleState() {
    return isGameOverHandled;
  }

  @Override
  public void resetGameOverHandled() {
    isGameOverHandled = false;
  }

  @Override
  public void itIsNotYourTurnMessage() {
    JOptionPane.showMessageDialog(this, "It is not your turn Yet!",
            "Invalid Move", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void updateScore(int blackScore, int whiteScore) {
    scoreLabel.setText("Black: " + blackScore + " White: " + whiteScore);
  }

  @Override
  public void setScoreLabel(JLabel scoreLabel) {
    this.scoreLabel = scoreLabel;
  }

  @Override
  public void update() {
    repaint();

    if (board instanceof BoardModel) {
      handleGameOver();
    }
  }

  @Override
  public HexShape findHex(int mouseX, int mouseY) {
    int squareSize = getHexSize() * 2;
    int sizeOfEntireBoard = board.getBoardSize();
    int gap = 5;

    int startX = getWidth() / 2 - (sizeOfEntireBoard * squareSize / 2) + 1;
    int startY = getHeight() / 2 - (sizeOfEntireBoard * squareSize / 2) + 1;

    for (int row = 0; row < sizeOfEntireBoard; row++) {
      for (int col = 0; col < sizeOfEntireBoard; col++) {
        int x = startX + col * (squareSize + gap);
        int y = startY + row * (squareSize + gap);

        if (isPointInHex(mouseX + 50, mouseY + 50, x, y, squareSize)) {
          return board.getCurrentHex(row, col);
        }
      }
    }
    return null;
  }


  @Override
  public int getHexSize() {
    return (getWindowHeight() * getWindowWidth()) / 15000;
  }

  @Override
  public boolean isPointInHex(int x, int y, int squareX, int squareY, int squareSize) {
    return x >= squareX && x <= squareX + squareSize &&
            y >= squareY && y <= squareY + squareSize;
  }

  @Override
  public int getWindowWidth() {
    return Math.min(this.getWidth(), 650);
  }

  @Override
  public void drawEachHexagon(Graphics g, HexShape hex, int centerX, int centerY, int hexSize, PlayerType playerType) {

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else if (hex.equals(hoveredHex)) {
      g.setColor(Color.GREEN);
    } else {
      g.setColor(Color.GRAY);
    }

    g.fillRect(centerX, centerY, hexSize, hexSize);
    g.setColor(Color.BLACK);
    g.drawRect(centerX, centerY, hexSize, hexSize);

    int ovalSize = hexSize / 2;
    int ovalX = centerX + (hexSize - ovalSize) / 2;
    int ovalY = centerY + (hexSize - ovalSize) / 2;

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else {
      g.setColor(getColor(playerType));
    }

    g.fillOval(ovalX, ovalY, ovalSize, ovalSize);
  }

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
        color = Color.GRAY;
        break;
    }
    return color;
  }

  public void drawBoard(Graphics g, ReadOnlyBoardModel board) {
    int squareSize = getHexSize() * 2;
    int gap = 5;

    int sizeOfEntireBoard = board.getBoardSize() + 1;

    sizeOfEntireBoard = (sizeOfEntireBoard % 2 == 0) ? sizeOfEntireBoard : sizeOfEntireBoard + 1;

    int startX = getWidth() / 2 - (sizeOfEntireBoard * squareSize / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard * squareSize / 2);

    for (int row = 0; row < sizeOfEntireBoard; row++) {
      for (int col = 0; col < sizeOfEntireBoard; col++) {
        int x = startX + col * (squareSize + gap);
        int y = startY + row * (squareSize + gap);

        if (row < board.getBoardSize() && col < board.getBoardSize()) {
          HexShape hex = board.getCurrentHex(row, col);

          if (hex != null) {
            drawEachHexagon(g, hex, x, y, squareSize, hex.getPlayerType());
          } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, squareSize, squareSize);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, squareSize, squareSize);
          }
        }
      }
    }
  }

  @Override
  public int getWindowHeight() {
    return Math.min(this.getHeight(), 650);
  }
}