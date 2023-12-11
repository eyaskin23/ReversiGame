import org.junit.Assert;
import org.junit.Test;

import javax.swing.JLabel;

import controller.players.Player;
import controller.players.PlayerType;
import model.BoardModel;
import model.HexBoard;
import model.ReadOnlyBoardModel;
import view.MockViewClass;

/**
 * Testing class for the mocked view.
 */
public class ExampleMockView {

  @Test
  public void testHandleGameOver() {
    ReadOnlyBoardModel board = new HexBoard(7);
    MockViewClass view = new MockViewClass(board);
    view.handleGameOver();
    Assert.assertTrue(view.getLog().contains("Handled Game Over"));
  }

  @Test
  public void testHandleGameOverSquare() {
    ReadOnlyBoardModel board = new HexBoard(8);
    MockViewClass view = new MockViewClass(board);
    view.handleGameOver();
    Assert.assertTrue(view.getLog().contains("Handled Game Over"));
  }

  @Test
  public void testResetGameOverHandledAndGetGameOverHandleState() {
    ReadOnlyBoardModel board = new HexBoard(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    BoardModel boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy Score"));
    view.resetGameOverHandled();
    Assert.assertTrue(view.getLog().contains("Resetting Game Over Handling."));
    Assert.assertFalse(view.getGameOverHandleState());
    view.getGameOverHandleState();
    Assert.assertTrue(view.getLog().contains("Getting Game Over Handle State."));
  }

  @Test
  public void testResetGameOverHandledAndGetGameOverHandleStateSquare() {
    ReadOnlyBoardModel board = new HexBoard(8);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    BoardModel boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy Score"));
    view.resetGameOverHandled();
    Assert.assertTrue(view.getLog().contains("Resetting Game Over Handling."));
    Assert.assertFalse(view.getGameOverHandleState());
    view.getGameOverHandleState();
    Assert.assertTrue(view.getLog().contains("Getting Game Over Handle State."));
  }

  @Test
  public void testSettingScore() {
    ReadOnlyBoardModel board = new HexBoard(7);
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy score"));
    Assert.assertTrue(view.getLog().contains("Setting Score."));
  }

  @Test
  public void testSettingScoreSquare() {
    ReadOnlyBoardModel board = new HexBoard(8);
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy score"));
    Assert.assertTrue(view.getLog().contains("Setting Score."));
  }
}
