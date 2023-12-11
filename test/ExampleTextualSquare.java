import org.junit.Assert;
import org.junit.Test;

import controller.TextualSquareController;

/**
 * Tests the controller initiates correctly for the textual square board.
 */
public class ExampleTextualSquare {

  /**
   * Tests that the controller throws an exception when passed in a null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualSquareController(null));
  }
}
