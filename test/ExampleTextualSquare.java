import org.junit.Assert;
import org.junit.Test;

import controller.TextualController;
import controller.TextualSquareController;
import controller.players.Player;
import controller.players.PlayerType;
import model.HexBoard;

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
