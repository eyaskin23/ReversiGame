package view;

import javax.swing.JLabel;

import model.ReadOnlyBoardModel;

/**
 * Mocks the view class for testing.
 */

public class MockViewClass implements DrawInterfaceMocker {
  private final StringBuilder log;
  private DrawUtils view;

  /**
   * Constructor for the Mocked View.
   */
  public MockViewClass(ReadOnlyBoardModel board) {
    this.log = new StringBuilder();
  }


  /**
   * Responsible for handling the Game Over state.
   */
  @Override
  public void handleGameOver() {
    this.log.append("Handled Game Over");
    view.handleGameOver();
  }

  /**
   * Responsible for showing invalid move message.
   */
  @Override
  public void showInvalidMoveMessage() {
    this.log.append("Invalid Move Pop Up");
    view.showInvalidMoveMessage();

  }

  /**
   * Responsible for showing that I passed the turn.
   */
  @Override
  public void showThatIPassedTurnMessage() {
    this.log.append("I passed turn Pop Up.");
    view.showThatIPassedTurnMessage();
  }

  /**
   * Responsible for showing it is now my turn.
   */
  @Override
  public void itIsNowYourTurnMessage() {
    this.log.append("It is now my turn pop up.");
    view.itIsNowYourTurnMessage();

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
    view.resetGameOverHandled();
  }

  /**
   * Responsible for showing it is not your turn.
   */
  @Override
  public void itIsNotYourTurnMessage() {
    this.log.append("It is not your turn pop up.");
    view.itIsNotYourTurnMessage();
  }

  /**
   * Responsible for updating the score.
   */
  @Override
  public void updateScore(int blackScore, int whiteScore) {
    this.log.append("Updated Score.");
    view.updateScore(blackScore, whiteScore);
  }


  /**
   * Responsible for setting the score.
   */
  @Override
  public void setScoreLabel(JLabel scoreLabel) {
    this.log.append("Setting Score.");
    view.setScoreLabel(scoreLabel);
  }

  /**
   * Returns the log as a string version.
   */
  public String getLog() {
    return this.log.toString();
  }
}
