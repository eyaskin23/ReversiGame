package model.strategies;

import java.util.Optional;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Interface for all types of strategies.
 */
public interface IStrategy {

  /**
   * Represents a certain move that an AIPlayer makes.
   */
  Optional<Move> selectMove(ReadOnlyBoardModel board, Player player);
}
