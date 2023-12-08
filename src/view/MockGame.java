package view;

import controller.ReversiController;
import model.Board;

/**
 * Extends the Game class for testing, and the mocked version of it.
 */
public class MockGame extends Game implements GameMocked {
  private final StringBuilder log;

  /**
   * Constructor that shows the entire game played, for updating purposes.
   */
  public MockGame(ReversiController controller1, ReversiController controller2, Board board) {
    super(controller1, controller2, board);
    this.log = new StringBuilder();
  }

  /**
   * Tells the game that it started.
   */
  @Override
  public void start() {
    this.log.append("Game has Started");
    super.start();
  }

  /**
   * Returns the log to a string.
   */
  public String getLog() {
    return this.log.toString();
  }
}
