package model.strategies;

import java.util.Optional;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Represents a fallible strategy in a ReversiGame.
 */
public interface FallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel board, Player player);
}
