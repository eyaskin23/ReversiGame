import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.TryTwo;

/**
 * A list of tests for the testing of two strategies.
 */
public class ExampleTryTwoStrats {
  @Test
  public void testingCaptureStrategyFirst() {
    TryTwo easy = new TryTwo(new CaptureStrategy(), new GoForCornersStrategy());
    Board mockBoard = new Board(7);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove(-1, -1);
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(1, -2, 2));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

  @Test
  public void testingGoForCornersFirst() {
    TryTwo easy = new TryTwo(new GoForCornersStrategy(), new CaptureStrategy());
    Board mockBoard = new Board(7);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove(-1, -1);
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(2, -1, 2));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

  @Test
  public void testingDoingTheSecondStrategy() {
    TryTwo easy = new TryTwo((dummyBoard, dummyPlayer) -> Optional.empty(), new CaptureStrategy());
    Board mockBoard = new Board(7);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove(-1, -1);
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(1, -2, 2));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

}
