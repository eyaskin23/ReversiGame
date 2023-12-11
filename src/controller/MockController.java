package controller;

import controller.players.Player;
import model.BoardModel;
import view.DrawUtils;

/**
 * Mocks the controller for testing.
 */
public class MockController extends ReversiController implements Features {
  private StringBuilder log;

  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
   */
  public MockController(Player player, BoardModel board, DrawUtils view) {
    super(player, board, view);
    this.log = new StringBuilder();
  }

  /**
   * Mock method for checking if it hit the method,
   * when a controller indicated to move a piece.
   */
  @Override
  public void onPlayerMove(int row, int column) {
    this.getLog()
            .append("Player moved to row: ")
            .append(row).append(", column: ").append(column).append("\n").toString();
    super.onPlayerMove(row, column);
  }

  /**
   * Mock method for the onPass that helps us see if it passed.
   */
  @Override
  public void onPass() {
    this.getLog().append("Player passed. \n").toString();
    super.onPass();
  }

  /**
   * Getting the Log from the mock.
   */
  public StringBuilder getLog() {
    return this.log;
  }

  /**
   * Makes the mock a string.
   */
  public String toString() {
    return this.getLog().toString();
  }
}
