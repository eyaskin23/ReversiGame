import controller.players.AIPlayer;
import controller.players.Player;
import model.Mock;
import model.ReadOnlyBoardModel;
import model.strategies.GoForCornersStrategy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.players.PlayerType;
import model.HexBoard;
import model.Move;

/**
 * A set of Tests for the Go For Corners Strategy.
 */
public class ExampleGoForCornerStrategies {

  @Test
  public void testPass() {
    HexBoard board1 = new HexBoard(7);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    player1.makeMove(-1, -1);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.playerPass(player1.getType());
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    player.makeMove();
    Assert.assertFalse(mock.getLog().toString().contains("pass"));
  }

  @Test
  public void testPassSquare() {
    HexBoard board1 = new HexBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    player1.makeMove(-1, 2);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.playerPass(player1.getType());
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    player.makeMove();
    Assert.assertFalse(mock.getLog().toString().contains("pass"));
  }

  @Test
  public void testGetScoreWhiteBlack() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.getScoreWhite();
    mock.getScoreBlack();
    Assert.assertTrue(mock.getLog().toString().contains("" + 3));
    Assert.assertTrue(mock.getLog().toString().contains("" + 3));
  }

  @Test
  public void testGetScoreWhiteBlackSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.getScoreWhite();
    mock.getScoreBlack();
    Assert.assertTrue(mock.getLog().toString().contains("" + 2));
    Assert.assertTrue(mock.getLog().toString().contains("" + 2));
  }

  @Test
  public void testGetBoardSize() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.getBoardSize();
    Assert.assertTrue(mock.getLog().toString().contains("" + 7));
  }

  @Test
  public void testGetBoardSizeSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.getBoardSize();
    Assert.assertTrue(mock.getLog().toString().contains("" + 8));
  }

  @Test
  public void testBoardFull() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.isBoardFull();
    Assert.assertTrue(mock.getLog().toString().contains("board is not full"));
  }

  @Test
  public void testBoardFullSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.isBoardFull();
    Assert.assertTrue(mock.getLog().toString().contains("board is not full"));
  }

  @Test
  public void countPieces() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    System.out.println(sb);
    Assert.assertFalse(mock.getLog().toString().contains("5"));
  }

  @Test
  public void countPiecesSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    System.out.println(sb);
    Assert.assertFalse(mock.getLog().toString().contains("8"));
  }

  @Test
  public void testIsValidMove() {
    HexBoard board1 = new HexBoard();
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.isValidMove(-1, -1, PlayerType.WHITE);
    System.out.println(sb);
    Assert.assertTrue(mock.getLog().toString().contains("Valid move"));
  }

  @Test
  public void testValidCoordinate() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isValidCoordinate(-11, 1);
    Assert.assertTrue(mock.getLog().toString().contains("Not a valid coordinate."));
  }

  @Test
  public void testValidCoordinateSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isValidCoordinate(-11, 1);
    Assert.assertTrue(mock.getLog().toString().contains("Not a valid coordinate."));
  }

  @Test
  public void testGetValidMovesWithCaptures() {
    ReadOnlyBoardModel board = new HexBoard(7);
    HexBoard board1 = new HexBoard();
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.getValidMovesWithCaptures(player);
    Assert.assertTrue(mock.getLog().toString().contains("Checking valid :"));
  }

  @Test
  public void testGetValidMovesWithCapturesSquare() {
    ReadOnlyBoardModel board = new HexBoard(8);
    HexBoard board1 = new HexBoard();
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.getValidMovesWithCaptures(player);
    Assert.assertTrue(mock.getLog().toString().contains("Checking valid :"));
  }

  @Test
  public void testIsCornerMove() {
    HexBoard board = new HexBoard(7);
    List<Move> valid = new ArrayList<>();
    Move move = new Move(0, 0);
    valid.add(move);
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isCornerMove(move, 11);
    Assert.assertTrue(mock.getLog().toString().contains("is a corner move"));
  }

  @Test
  public void testIsCornerMoveSquare() {
    HexBoard board = new HexBoard(8);
    List<Move> valid = new ArrayList<>();
    Move move = new Move(0, 0);
    valid.add(move);
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isCornerMove(move, 11);
    Assert.assertTrue(mock.getLog().toString().contains("is a corner move"));
  }
}






