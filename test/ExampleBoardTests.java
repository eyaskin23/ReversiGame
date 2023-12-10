import controller.players.Player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.players.PlayerType;
import model.HexBoard;
import model.Move;

/**
 * Tests the board methods that are not read only.
 */
public class ExampleBoardTests {

  HexBoard board = new HexBoard(11, false);


  /**
   * Tests that a piece is correctly placed in the board.
   */
  @Test
  public void testPlacePiece() {
    board.placePiece(3, 3, PlayerType.WHITE);
    Assert.assertEquals(PlayerType.WHITE, board.
            getCurrentHex(3, 3).getPlayerType());
  }

  @Test
  public void testCalculatePiecesWithAValidMove() {
    HexBoard defaultBoard = new HexBoard(7, false);
    Player player1 = new Player("Player 1", PlayerType.WHITE, defaultBoard);
    Player player2 = new Player("Player 2", PlayerType.BLACK, defaultBoard);
    int k = defaultBoard.calculateCaptures(-1, -1, player1.getType(), defaultBoard);
    Assert.assertEquals(k, 1);
  }

  @Test
  public void testGetValidMovesWithCaptures() {
    HexBoard defaultBoard = new HexBoard(7, false);
    Player player1 = new Player("Player 1", PlayerType.WHITE, defaultBoard);
    Player player2 = new Player("Player 2", PlayerType.BLACK, defaultBoard);
    List<Move> validMoves = defaultBoard.getValidMovesWithCaptures(player1);

    //Expected moves
    List<Move> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(-1, -1, 1));
    expectedMoves.add(new Move(-2, 1, 1));
    expectedMoves.add(new Move(-1, 2, 1));
    expectedMoves.add(new Move(1, 1, 1));
    expectedMoves.add(new Move(2, -1, 1));
    expectedMoves.add(new Move(1, -2, 1));

    Assert.assertEquals(expectedMoves.size(), validMoves.size());

    for (Move expectedMove : expectedMoves) {
      Assert.assertTrue(validMoves.contains(expectedMove));

    }

    for (Move validMove : validMoves) {
      Assert.assertTrue(expectedMoves.contains(validMove));
    }
  }
}



