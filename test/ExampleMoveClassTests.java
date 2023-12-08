import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Move;

/**
 * A set of Tests for the Move Class.
 */
public class ExampleMoveClassTests {

  private Move move;

  @Before
  public void setUp() {
    move = new Move(1, 3, 7);
  }

  @Test
  public void testGetX() {
    Assert.assertEquals(move.getX(), 1);
  }

  @Test
  public void testGetY() {
    Assert.assertEquals(move.getY(), 3);
  }

  @Test
  public void testGetPiecesCaught() {
    Assert.assertEquals(move.getPiecesCaught(), 7);
  }

  @Test
  public void testSetX() {
    move.setX(5);
    Assert.assertEquals(move.getX(), 5);
  }

  @Test
  public void testSetY() {
    move.setY(5);
    Assert.assertEquals(move.getY(), 5);
  }

  @Test
  public void testSetPiecesCaught() {
    move.setPiecesCaught(65);
    Assert.assertEquals(move.getPiecesCaught(), 65);
  }
}
