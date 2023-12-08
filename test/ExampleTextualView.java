import controller.players.Player;
import controller.players.PlayerType;
import model.Board;

import org.junit.Assert;
import org.junit.Test;

import controller.TextualController;

/**
 * Tests that tes the textual view.
 */
public class ExampleTextualView {

  /**
   * Tests that the controller throws an exception when passed in a null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }

  /**
   * Tests that the initial board correctly prints out as a string.
   */
  @Test
  public void testInitialBoard() {
    Board board = new Board(11);
    TextualController controller = new TextualController(board);
    Assert.assertEquals(controller.toString(),
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "_ _ _ _ X _ O _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n");

  }

  /**
   * Tests that the board correctly prints out a move with coordinates
   * - 1 and -1.
   */
  @Test
  public void testMove() {
    Board board = new Board(11);
    TextualController controller = new TextualController(board);
    Player player1 = new Player("name", PlayerType.WHITE, board);
    player1.makeMove(-1, -1);
    Assert.assertEquals(controller.toString(),
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    " _ _ _ X X X _ _ _ _ \n" +
                    "_ _ _ _ X _ O _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n");

  }

  /**
   * Tests that the board correctly prints out a second move with coordinates
   * - 1 and -2.
   */
  @Test
  public void testSecondMove() {
    Board board = new Board(11);
    TextualController controller = new TextualController(board);
    Player player1 = new Player("player1", PlayerType.WHITE, board);
    Player player2 = new Player("player2", PlayerType.BLACK, board);
    player1.makeMove(-1, -1);
    player2.makeMove(1, -2);

    Assert.assertEquals(
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "  _ _ _ _ O _ _ _ _ \n" +
                    " _ _ _ X X O _ _ _ _ \n" +
                    "_ _ _ _ X _ O _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n", controller.toString());
  }
}