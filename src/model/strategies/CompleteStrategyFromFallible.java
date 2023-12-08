package model.strategies;

import java.util.Optional;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Converts fallible strategies into fallible strategies.
 * Basically a complete strategy.
 */
public class CompleteStrategyFromFallible
        implements InfallibleHexGameStrategy {
  FallibleHexGameStrategy strategyToTry;

  /**
   * Selects a certain move for the AIPlayer to make.
   */
  public Optional<Move> selectMove(ReadOnlyBoardModel boardModel, Player player)
          throws IllegalStateException {
    Optional<Move> firstMove = this.strategyToTry
            .selectMove(boardModel, player);
    if (firstMove.isPresent()) {
      return firstMove;
    }
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }
}
