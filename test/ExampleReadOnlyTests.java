import controller.players.Player;
import controller.players.PlayerType;
import model.HexBoard;
import model.HexShape;

import org.junit.Assert;
import org.junit.Test;

import controller.TextualController;

/**
 * Tests the method for read only methods for Board.
 */
public class ExampleReadOnlyTests {

  HexBoard board = new HexBoard(11, false);

  /**
   * Tests a Board that is given an even number which
   * should not work.
   */
  @Test
  public void testingEvenBoardInvalid() {
    Assert.assertThrows(IllegalStateException.class, () -> new HexBoard(12, false));
  }

  /**
   * Tests a Board that is given a negative number which
   * should not work.
   */
  @Test
  public void testingNegativeBoardValid() {
    Assert.assertThrows(IllegalStateException.class, () -> new HexBoard(-6, false));
  }

  /**
   * Tests that coordinates of a 2D array are correct in our model.
   */
  @Test
  public void testingCoordinatesOfAHexagonBasedOn2DArray() {
    HexBoard regularBoard = new HexBoard(7, false);

    HexShape topLeft = regularBoard.getCurrentHex(0, 3);
    HexShape topLeftRepresentation = new HexShape(-3, 0, null);
    Assert.assertEquals(topLeft.getColumn(), topLeftRepresentation.getColumn());

    HexShape middle = regularBoard.getCurrentHex(3, 3);
    HexShape middleRepresentation = new HexShape(0, 0, null);
    Assert.assertEquals(middle.getRow(), middleRepresentation.getRow());
    Assert.assertEquals(middle.getColumn(), middleRepresentation.getColumn());


    HexShape bottomLeft = regularBoard.getCurrentHex(6, 0);
    HexShape btmLeftRep = new HexShape(3, -3, null);
    Assert.assertEquals(bottomLeft.getRow(), btmLeftRep.getRow());
    Assert.assertEquals(bottomLeft.getColumn(), btmLeftRep.getColumn());

    HexShape topRightHex = regularBoard.getCurrentHex(0, 6);
    HexShape topRightRepresentation = new HexShape(-3, 3, null);
    Assert.assertEquals(topRightHex.getRow(), topRightRepresentation.getRow());
    Assert.assertEquals(topRightHex.getColumn(), topRightRepresentation.getColumn());

    HexShape rightMiddle = regularBoard.getCurrentHex(3, 4);
    HexShape rightMiddleRep = new HexShape(0, 1, null);
    Assert.assertEquals(rightMiddle.getRow(), rightMiddleRep.getRow());
    Assert.assertEquals(rightMiddle.getColumn(), rightMiddleRep.getColumn());
  }


  /**
   * Tests the Initial board
   * prints out the empty and white/black
   * players correctly.
   */

  @Test
  public void testInitialBoard() {
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
   * Tests that the board constructor is not null,
   * and that the size is eleven.
   */

  @Test
  public void testBoard() {
    Assert.assertNotNull(board);
    Assert.assertEquals(11, board.getBoardSize());
  }


  /**
   * Tests that a valid coordinate is passed into the game.
   */
  @Test
  public void testValidCoordinate() {
    Assert.assertTrue(board.isValidCoordinate(0, 0));
    Assert.assertTrue(board.isValidCoordinate(10, 10));
  }


  /**
   * Tests that a valid move is passed into the game.
   */
  @Test
  public void testValidMove() {
    HexBoard board1 = new HexBoard(7, false);
    Assert.assertTrue(board1.isValidMove(-1, -1, PlayerType.WHITE));
  }


  /**
   * Tests that a board is full when it's first started.
   */
  @Test
  public void testBoardFullInitalState() {
    Assert.assertFalse(board.isBoardFull());
  }


  /**
   * Tests that the correct amount of pieces
   * is in the game when started.
   */
  @Test
  public void testInitialCountPieces() {
    Assert.assertEquals(3, board.countPieces(PlayerType.WHITE));
    Assert.assertEquals(3, board.countPieces(PlayerType.BLACK));
  }

  /**
   * Tests that the count of hex shapes gets correctly updated, when a piece is placed.
   */
  @Test
  public void testCount() {
    HexBoard board = new HexBoard(11, false);
    Player player1 = new Player("e", PlayerType.WHITE, board);

    player1.makeMove(-1, -1);

    board.getCurrentHex(11 / 2, 11 / 2 + 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 + 1, 11 / 2).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(11 / 2, 11 / 2 - 1).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(11 / 2 + 1, 11 / 2 - 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 - 1, 11 / 2).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 - 1, 11 / 2 + 1).setPlayerType(PlayerType.WHITE);

    Assert.assertEquals(3, board.countPieces(PlayerType.WHITE));
    Assert.assertEquals(board.getBoardSize(), 11);
  }

  /**
   * Tests whether both players have passed their move.
   */
  @Test
  public void testBothPlayersPassed() {
    HexBoard board = new HexBoard();

    Player player1 = new Player("e", PlayerType.WHITE, board);
    Player player2 = new Player("s", PlayerType.BLACK, board);
    Assert.assertFalse(player1.hasPassed);
    Assert.assertFalse(player2.hasPassed);
  }

  /**
   * Tests whether a player has any more valid moves.
   */
  @Test
  public void testTrapped() {
    HexBoard board = new HexBoard();

    HexShape hex = new HexShape(0, 0, null);
    hex.setPlayerType(PlayerType.EMPTY);

    Player player1 = new Player("e", PlayerType.WHITE, board);
    Player player2 = new Player("s", PlayerType.BLACK, board);
    Assert.assertFalse(board.isBoardFull());

  }

  /**
   * Tests that a board size is correct upon intialization.
   */
  @Test
  public void testGetBoardSize() {
    HexBoard board1 = new HexBoard(11, false);
    Assert.assertEquals(11, board1.getBoardSize());

    HexBoard board2 = new HexBoard();
    Assert.assertEquals(7, board2.getBoardSize());

    HexBoard board3 = new HexBoard(15, false);
    Assert.assertEquals(15, board3.getBoardSize());

  }

  /**
   * Tests that the count of pieces after a move is correct.
   */
  @Test
  public void testCountPiecesAfter1Move() {
    HexBoard board1 = new HexBoard(7, false);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    Assert.assertEquals(3, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(3, board1.countPieces(PlayerType.BLACK));

    player1.makeMove(-1, -1);
    Assert.assertEquals(3, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(3, board1.countPieces(PlayerType.BLACK));
  }

  /**
   * Tests that upon intialization, a board is set with correct players.
   */
  @Test
  public void testIsBoardFullWhenBoardIsInitialized() {
    HexBoard board1 = new HexBoard();
    Assert.assertFalse(board1.isBoardFull());
  }

  /**
   * Tests valid coordinates.
   */
  @Test
  public void testValidCoordinates() {
    HexBoard board = new HexBoard(7, false); // board size is now 7

    Assert.assertTrue(board.isValidCoordinate(0, 0));  // Top-left corner
    Assert.assertTrue(board.isValidCoordinate(0, 6));  // Top-right corner
    Assert.assertTrue(board.isValidCoordinate(6, 0));  // Bottom-left corner
    Assert.assertTrue(board.isValidCoordinate(6, 6));  // Bottom-right corner
    Assert.assertTrue(board.isValidCoordinate(3, 3));  // Center of the board
  }

  /**
   * Tests that a board with invalid coordinates cannot be started.
   */
  @Test
  public void testInvalidCoordinates() {
    HexBoard board = new HexBoard(7, false); // board size is now 7

    Assert.assertFalse(board.isValidCoordinate(-1, 0));  // Negative q
    Assert.assertFalse(board.isValidCoordinate(0, -1));  // Negative r
    Assert.assertFalse(board.isValidCoordinate(-1, -1)); // Both q and r negative
    Assert.assertFalse(board.isValidCoordinate(7, 0));   // q out of range (equal to board size)
    Assert.assertFalse(board.isValidCoordinate(0, 7));   // r out of range (equal to board size)
    Assert.assertFalse(board.isValidCoordinate(7, 7));   // Both q and r out of range
    Assert.assertFalse(board.isValidCoordinate(10, 10)); // Both q and r way out of range
  }

  /**
   * Tests that a board is set as full when it has all the player type.
   */
  @Test
  public void testIsBoardFullWhenBoardConsistsOfAllPlayerTypes() {
    HexBoard board1 = new HexBoard();
    for (HexShape[] row : board1.cellsThatMakeTheBoard) {

      for (HexShape hexShape : row) {
        if (hexShape == null) {
          continue;
        }
        hexShape.setPlayerType(PlayerType.WHITE);
      }
    }
    Assert.assertTrue(board1.isBoardFull());
  }

  /**
   * Tests that the score of a game correctly updates.
   */
  @Test
  public void testGetScore() {
    HexBoard board = new HexBoard();
    Assert.assertEquals(
            "Checking that the starting number of white pieces is 3",
            board.getScoreWhite(), 3);

    Assert.assertEquals(
            "Checking that the starting number of black pieces is 3",
            board.getScoreBlack(), 3);

    Player player1 = new Player("S", PlayerType.WHITE, board);
    Player player2 = new Player("E", PlayerType.WHITE, board);
    PlayerType playerOneType = PlayerType.BLACK;
    PlayerType playerTwoType = PlayerType.WHITE;

    Assert.assertEquals(playerOneType.name(), "BLACK");
    Assert.assertEquals(playerTwoType.name(), "WHITE");


    player1.makeMove(-1, -1); // Player One places piece at (-1,-1)
    player1.makeMove(-2, 1);  // Player One places piece at (-2,1)

    Assert.assertEquals(3, board.getScoreWhite());
    Assert.assertEquals(3, board.getScoreBlack());
  }

  @Test
  public void testCopy() {
    HexBoard original = new HexBoard(7, false);
    original.placePiece(3, 3, PlayerType.BLACK);
    original.placePiece(4, 4, PlayerType.WHITE);
    original.playerPass(PlayerType.WHITE);
    original.playerPass(PlayerType.BLACK);

    HexBoard copied = original.deepCopy();

    Assert.assertNotSame("Copied board should not be the same as original board",
            original, copied);

    Assert.assertEquals("Board sizes should be equal",
            original.getBoardSize(), copied.getBoardSize());

    Assert.assertEquals("White passed piece should be copied",
            original.hasPlayerPassed(PlayerType.WHITE),
            copied.hasPlayerPassed(PlayerType.WHITE));
    Assert.assertEquals("Black passed piece should be copied",
            original.hasPlayerPassed(PlayerType.BLACK), copied.hasPlayerPassed(PlayerType.BLACK));

    for (int i = 0; i < original.getBoardSize(); i++) {
      for (int j = 0; j < original.getBoardSize(); j++) {
        HexShape originalHex = original.getCurrentHex(i, j);
        HexShape copiedHex = copied.getCurrentHex(i, j);
        if (originalHex != null) {
          Assert.assertNotNull("Copied HexShape should not be null", copiedHex);
          Assert.assertEquals("HexShape player types should be equal",
                  originalHex.getPlayerType(), copiedHex.getPlayerType());
        } else {
          Assert.assertNull("Copied HexShape should be null", copiedHex);
        }
      }
    }
  }
}
