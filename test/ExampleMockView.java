import org.junit.Assert;
import org.junit.Test;

import javax.swing.JLabel;

import controller.MockController;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import view.MockViewClass;

/**
 * Testing class for the mocked view.
 */
public class ExampleMockView {

  @Test
  public void testHandleGameOver() {
    ReadOnlyBoardModel board = new Board(7);
    MockViewClass view = new MockViewClass(board);
    view.handleGameOver();
    Assert.assertTrue(view.getLog().contains("Handled Game Over"));
  }

  @Test
  public void testShowInvalidMoveMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);
    controller1.onPlayerMove(-3, 0);
    Assert.assertTrue(view.getLog().contains("Invalid Move Pop Up"));
  }


  @Test
  public void testShowThatIPassedTurnMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy Score"));
    MockController controller1 = new MockController(player, boardReg, view);
    MockController controller12 = new MockController(player2, boardReg, view);
    player.setMoveHandler(controller1);
    player2.setMoveHandler(controller12);
    controller1.onPass();
    Assert.assertTrue(view.getLog().contains("I passed turn Pop Up."));
  }

  @Test
  public void testItIsNowYourTurnMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy score"));
    MockController controller1 = new MockController(player, boardReg, view);
    MockController controller2 = new MockController(player2, boardReg, view);
    player.setMoveHandler(controller1);
    player2.setMoveHandler(controller2);
    controller1.onPlayerMove(-1, -1);
    Assert.assertTrue(view.getLog().contains("It is now my turn"));
  }

  @Test
  public void testResetGameOverHandledAndGetGameOverHandleState() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy Score"));
    view.resetGameOverHandled();
    Assert.assertTrue(view.getLog().contains("Resetting Game Over Handling."));
    Assert.assertFalse(view.getGameOverHandleState());
    view.getGameOverHandleState();
    Assert.assertTrue(view.getLog().contains("Getting Game Over Handle State."));
  }


  @Test
  public void testItIsNotYourTurnMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy Score"));
    MockController controller1 = new MockController(player, boardReg, view);
    MockController controller12 = new MockController(player2, boardReg, view);
    player.setMoveHandler(controller1);
    player2.setMoveHandler(controller12);
    controller12.onPass();
    Assert.assertTrue(view.getLog().contains("It is not your turn pop up."));
  }


  @Test
  public void testUpdateScore() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy score"));
    MockController controller1 = new MockController(player, boardReg, view);
    controller1.update();
    Assert.assertTrue(view.getLog().contains("Updated Score."));
  }


  @Test
  public void testSettingScore() {
    ReadOnlyBoardModel board = new Board(7);
    MockViewClass view = new MockViewClass(board);
    view.setScoreLabel(new JLabel("Dummy score"));
    Assert.assertTrue(view.getLog().contains("Setting Score."));
  }
}
