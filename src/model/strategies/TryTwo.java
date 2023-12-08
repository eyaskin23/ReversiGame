package model.strategies;

import java.util.Optional;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Represents a class that allows multiple strategies.
 */
public class TryTwo implements FallibleHexGameStrategy {
  IStrategy first;
  IStrategy second;

  /**
   * Represents a constructor for combining two strategies.
   */
  public TryTwo(IStrategy first, IStrategy second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Selects a certain move for an AIPlayer.
   */
  public Optional<Move> selectMove(ReadOnlyBoardModel board, Player player) {
    Optional<Move> firstMove = this.first.selectMove(board, player);
    if (firstMove.isPresent()) {
      return firstMove; // the first strategy succeeded
    }
    return this.second.selectMove(board, player);
  }
}
