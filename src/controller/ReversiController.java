package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.BoardModel;
import view.MockViewClass;
import view.Observer;
import view.PlayerActionListener;
import view.ReversiView;

/**
 * The main controller for a player to interact with a board through the view.
 * Adhering to OOD principles.
 */
public class ReversiController implements PlayerActionListener, Observer, MoveHandler {
  private final Player player;
  private BoardModel board;
  private ReversiView view;
  private boolean turnMessageDisplayed = false;
  private boolean isUpdating = false;


  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
   */
  public ReversiController(Player player, BoardModel board, ReversiView view) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
    this.board = board;
    this.view = view;
    board.addObserver(this);
    view.resetGameOverHandled();
  }

  /**
   * Places the piece on the board.
   */
  private void placeKey(int x, int y) {
    if (!board.isValidMove(x, y, player.getType())) {
      view.showInvalidMoveMessage();
      return;
    }
    if (x > board.getBoardSize() / 2 || x < -board.getBoardSize()
            || y > board.getBoardSize() / 2 || y < -board.getBoardSize()) {
      view.showInvalidMoveMessage();
      return;
    }
    int q = x + board.getBoardSize() / 2;
    int r = y + board.getBoardSize() / 2;
    board.placePiece(q, r, player.getType());
    board.flipPieces(q, r, player.getType());
  }

  /**
   * Controller, moves for the player onto the board.
   */
  @Override
  public void onPlayerMove(int row, int column) {
    if (handleTurn() || board.isGameOver()) {
      return;
    }
    if (board.isValidMove(row, column, player.getType())) {
      this.placeKey(row, column);
      player.resetHasPassed();
      resetOpponentPassedState();
    } else {
      view.showInvalidMoveMessage();
      return;
    }
    checkAndUpdateGameState();
  }

  /**
   * Resets the opponent's passed state, so it doesn't keep if one have passed.
   */
  private void resetOpponentPassedState() {
    if (player.getType() == PlayerType.WHITE) {
      board.resetBlackPassed();
    } else {
      board.resetWhitePassed();
    }
  }

  /**
   * The Controller signals that the player has passed and sends messages.
   */
  @Override
  public void onPass() {
    if (handleTurn()) {
      return;
    }
    board.playerPass(player.getType());
    if (!(player instanceof AIPlayer)) {
      view.showThatIPassedTurnMessage();
    }
    player.setHasPassed();
    checkAndUpdateGameState();
  }

  /**
   * Updates the controller.
   */
  @Override
  public void update() {
    if (isUpdating) {
      return;
    }
    try {
      isUpdating = true;
      if (board.isGameOver() && !view.getGameOverHandleState()) {
        view.handleGameOver();
        return;
      }
      updateScoreInView();
      if (board.isGameOver()) {
        view.handleGameOver();
      } else {
        if (board.isPlayerTurn(player)) {
          if (!turnMessageDisplayed) {
            if (player instanceof AIPlayer) {
              ((AIPlayer) player).makeMove();
              if (player.getHasPassed()) {
                onPass();
              }
              board.notifyObservers();
              checkAndUpdateGameState();
            } else {
              turnMessageDisplayed = true;
              view.itIsNowYourTurnMessage();
            }
          }
        } else {
          turnMessageDisplayed = false;
        }
        view.updateBoard(board);
        //view.requestFocusInWindow();
      }
    } finally {
      isUpdating = false;
    }
  }

  /**
   * Updates the score in the view.
   */
  private void updateScoreInView() {
    int blackScore = board.getScoreBlack();
    int whiteScore = board.getScoreWhite();
    view.updateScore(blackScore, whiteScore);
  }

  /**
   * Tells the view to do something, when the game is over.
   */
  @Override
  public void onGameOver() {
    view.handleGameOver();
  }


  private boolean handleTurn() {
    if (!board.isPlayerTurn(player)) {
      view.itIsNotYourTurnMessage();
      return true;
    }
    return false;
  }

  private void checkAndUpdateGameState() {
    view.updateBoard(board);

    if (board.isGameOver()) {
      view.handleGameOver();
      return;
    }

    // Reset opponent's 'passed' state and switch turns
    if (player.getType() == PlayerType.WHITE) {
      board.resetBlackPassed();
    } else {
      board.resetWhitePassed();
    }

    board.switchTurns();
    turnMessageDisplayed = false;
  }

  @Override
  public void handleMove(Player player, int row, int column) {
    this.placeKey(row, column);
  }

}
