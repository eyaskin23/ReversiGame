import controller.players.Player;
import controller.players.PlayerType;
import model.HexBoard;
import org.junit.Assert;
import org.junit.Test;


/**
 * A set of examples for the player Class.
 */
public class ExamplePlayerTests {

  /**
   * Tests that the name of a player is correct.
   */
  @Test
  public void testGetName() {
    HexBoard firstBoard = new HexBoard();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getName(), "Sebastian");
    Assert.assertEquals(two.getName(), "Christian");
  }

  /**
   * Tests the type of a certain player.
   */
  @Test
  public void testGetType() {
    HexBoard firstBoard = new HexBoard();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getType(), PlayerType.BLACK);
    Assert.assertEquals(two.getType(), PlayerType.WHITE);
  }

  /**
   * Tests that a player is correctly passing.
   */
  @Test
  public void hasPassed() {
    HexBoard firstBoard = new HexBoard();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertFalse(one.hasPassed);
  }

  /**
   * Tests that two players are be passed.
   */
  @Test
  public void setHasPassed() {
    HexBoard firstBoard = new HexBoard();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertFalse(one.hasPassed);
  }

  /**
   * Tests that a piece is being correctly placed.
   */
  @Test
  public void testPlaceKey() {
    HexBoard firstBoard = new HexBoard(7, false);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    one.makeMove(-1, -1);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.EMPTY);
  }

  /**
   * Tests that a piece is being correctly placed.
   */
  @Test
  public void testPlaceKeySquare() {
    HexBoard firstBoard = new HexBoard(8, true);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    one.makeMove(-1, -1);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.EMPTY);
  }

  /**
   * Tests that two players are correctly being initiated.
   */
  @Test
  public void testHasPassedInitalState() {
    HexBoard firstBoard = new HexBoard(7, false);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertFalse(one.hasPassed);
    Assert.assertFalse(two.hasPassed);
  }

  /**
   * Tests that two players are correctly being initiated.
   */
  @Test
  public void testHasPassedInitalStateSquare() {
    HexBoard firstBoard = new HexBoard(8, true);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertFalse(one.hasPassed);
    Assert.assertFalse(two.hasPassed);
  }
}