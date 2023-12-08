package model.strategies;

import java.util.Optional;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Represents an infallible strategy in reversi game.
 */
public interface InfallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel boardModel,
                            Player player) throws IllegalStateException;
}
