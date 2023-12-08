package controller.players;

import controller.MoveHandler;
import model.ReadOnlyBoardModel;

/**
 * Represents a single player in a reversi game.
 */
public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;
  final ReadOnlyBoardModel board;
  public boolean hasPassed;
  private MoveHandler moveHandler;

  /**
   * Constructor for player with a name, a player type,
   * and a board.
   */
  public Player(String name, PlayerType type, ReadOnlyBoardModel board) {
    this.name = name;
    this.type = type;
    this.board = board;
    this.hasPassed = false;
  }

  /**
   * Returns the name of a player.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the PLayerType of a player.
   */
  public PlayerType getType() {
    return type;
  }


  /**
   * Resets the has passed boolean for each turn.
   */
  public void resetHasPassed() {
    this.hasPassed = false;
  }


  /**
   * Sets the player to pass and checks if the game is over.
   */
  public void setHasPassed() {
    this.hasPassed = true;
    board.getRegularBoard().checkGameOver();
  }

  /**
   * A Set Move Handler, that sets the move for the Player.
   */
  public void setMoveHandler(MoveHandler moveHandler) {
    this.moveHandler = moveHandler;
  }

  /**
   * This makes the move for the Player that tells the controller what to do.
   */
  public void makeMove(int row, int column) {
    if (moveHandler != null) {
      moveHandler.handleMove(this, row, column);
    }
  }

  /**
   * Returns "White" or "Black" for score purposes and acknowledgment.
   */
  public String getColor() {
    if (this.getType() == PlayerType.WHITE) {
      return whitePlayer();
    } else {
      return blackPlayer();
    }
  }

  /**
   * Returns "White" for a white Player.
   */
  public String whitePlayer() {
    return "White";
  }

  /**
   * Returns "Black" for a black Player.
   */
  public String blackPlayer() {
    return "Black";
  }

  /**
   * Returns the boolean field of the player to see
   * if they passed or not.
   */
  public boolean getHasPassed() {
    return this.hasPassed;
  }

}