import org.junit.Assert;
import org.junit.Test;

import javax.swing.JLabel;

import controller.MockController;
import controller.players.Player;
import controller.players.PlayerType;
import model.BoardModel;
import model.HexBoard;
import model.ReadOnlyBoardModel;
import view.DrawUtils;
import view.MockGame;

/**
 * Testing class for the mocked version of the game.
 */
public class ExampleMockGame {

  @Test
  public void testStartGame() {
    ReadOnlyBoardModel board = new HexBoard(7, false);

    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human", PlayerType.WHITE, board);
    DrawUtils view = new DrawUtils(board);

    JLabel score = new JLabel("Dummy Score");
    view.setScoreLabel(score);
    BoardModel boardReg = board.getRegularBoard();

    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);

    MockController controller2 = new MockController(player2, boardReg, view);
    player.setMoveHandler(controller2);

    MockGame game = new MockGame(controller1, controller2, boardReg);

    game.start();

    Assert.assertTrue(game.getLog().contains("Game has Started"));
  }
}
