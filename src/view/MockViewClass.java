package view;

import javax.swing.JLabel;

import model.ReadOnlyBoardModel;

/**
 * Mocks the view class for testing.
 */

public class MockViewClass extends DrawUtils implements DrawInterfaceMocker {
  private final StringBuilder log;

  /**
   * Constructor for the Mocked View.
   */
  public MockViewClass(ReadOnlyBoardModel board) {
    super(board);
    this.log = new StringBuilder();
  }


  /**
   * Responsible for handling the Game Over state.
   */
  @Override
  public void handleGameOver() {
    this.log.append("Handled Game Over");
    super.handleGameOver();
  }

  /**
   * Responsible for showing invalid move message.
   */
  @Override
  public void showInvalidMoveMessage() {
    this.log.append("Invalid Move Pop Up");
    super.showInvalidMoveMessage();

  }

  /**
   * Responsible for showing that I passed the turn.
   */
  @Override
  public void showThatIPassedTurnMessage() {
    this.log.append("I passed turn Pop Up.");
    super.showThatIPassedTurnMessage();
  }

  /**
   * Responsible for showing it is now my turn.
   */
  @Override
  public void itIsNowYourTurnMessage() {
    this.log.append("It is now my turn pop up.");
    super.itIsNowYourTurnMessage();

  }

  /**
   * Responsible for getting the game over handle state.
   */
  @Override
  public boolean getGameOverHandleState() {
    this.log.append("Getting Game Over Handle State.");
    return false;
  }

  /**
   * Responsible for resetting the game over handling.
   */
  @Override
  public void resetGameOverHandled() {
    this.log.append("Resetting Game Over Handling.");
    super.resetGameOverHandled();
  }

  /**
   * Responsible for showing it is not your turn.
   */
  @Override
  public void itIsNotYourTurnMessage() {
    this.log.append("It is not your turn pop up.");
    super.itIsNotYourTurnMessage();
  }

  /**
   * Responsible for updating the score.
   */
  @Override
  public void updateScore(int blackScore, int whiteScore) {
    this.log.append("Updated Score.");
    super.updateScore(blackScore, whiteScore);
  }

  /**
   * Responsible for setting the score.
   */
  @Override
  public void setScoreLabel(JLabel scoreLabel) {
    this.log.append("Setting Score.");
    super.setScoreLabel(scoreLabel);
  }

  /**
   * Returns the log as a string version.
   */
  public String getLog() {
    return this.log.toString();
  }
}
