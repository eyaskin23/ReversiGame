package model.strategies;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents the capture strategy for an AIPlayer.
 * Capture as many pieces on this turn as possible.
 */
public class CaptureStrategy implements FallibleHexGameStrategy {
  private static final Logger logger = Logger.getLogger(CaptureStrategy.class.getName());

  static {
    try {
      FileHandler fileHandler = new FileHandler("strategy-transcript.txt");
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);
    } catch (Exception e) {
      logger.warning("Failed to initialize logger file handler: " + e.getMessage());
    }
  }

  /**
   * Selects a certain move the AIPlayer to make based on the board.
   */
  @Override
  public Optional<Move> selectMove(ReadOnlyBoardModel board, Player player) {
    logger.info("Selecting move for player: " + player.getName() + " " + player.getColor());
    List<Move> validMoves = board.getValidMovesWithCaptures(player);

    if (validMoves.isEmpty()) {
      logger.info("No valid moves available. Passing turn.");
      return Optional.empty();
    }
    Move bestMove = validMoves.get(0);
    for (Move move : validMoves) {
      if (move.getPiecesCaught() > bestMove.getPiecesCaught()) {
        bestMove = move;
      } else if (move.getPiecesCaught() == bestMove.getPiecesCaught()) {
        if (move.getY() < bestMove.getY()
                || (move.getX() == bestMove.getX() && move.getY() < bestMove.getY())) {
          bestMove = move;
        }
      }
    }
    logger.info("Selected move: " + bestMove.getX() + ", " + bestMove.getY());
    logger.info("Capturing " + bestMove.getPiecesCaught() + " pieces!");
    return Optional.of(bestMove);
  }
}

