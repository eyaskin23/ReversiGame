import org.junit.Assert;
import org.junit.Test;

import javax.swing.JLabel;

import controller.MockController;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;

/**
 * A set of examples to test the Mock Controller.
 */
public class ExampleMockController {

  @Test
  public void testOnPass() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    DrawUtils view = new DrawUtils(board);
    JLabel score = new JLabel("Dummy Score");
    view.setScoreLabel(score);
    Board boardReg = board.getRegularBoard();
    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);
    controller1.onPass();
    Assert.assertTrue(controller1.getLog().toString().contains("Player passed"));
  }


  @Test
  public void testOnPlayerMove() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    DrawUtils view = new DrawUtils(board);
    JLabel score = new JLabel("Dummy Score");
    view.setScoreLabel(score);
    Board boardReg = board.getRegularBoard();
    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);
    controller1.onPlayerMove(-1,-1);
    Assert.assertTrue(controller1.getLog().toString().contains("Player moved to row"));
  }

}
