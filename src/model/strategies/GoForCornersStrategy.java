package model.strategies;

import controller.players.Player;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents the strategy for the AIPlayer
 * that goes for the corners in a board.
 */
public class GoForCornersStrategy implements FallibleHexGameStrategy {
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
   * Selects a certain move for the AIPlayer to make based on the board.
   */
  @Override
  public Optional<Move> selectMove(ReadOnlyBoardModel board, Player player) {
    logger.info("Selecting move for player: " + player.getName() + " " + player.getColor());
    List<Move> cornerMoves = new ArrayList<>();
    List<Move> validMoves = board.getValidMovesWithCaptures(player);
    if (validMoves.isEmpty()) {
      logger.info("No valid moves available. Passing turn.");
      //player.setHasPassed();
      return Optional.empty();
    }

    for (Move move : validMoves) {
      if (isCornerMove(move, board.getBoardSize())) {
        cornerMoves.add(move);
      }
    }

    if (cornerMoves.isEmpty()) {
      Move bestMoveWithNoCorner = validMoves.get(0);
      for (Move move : validMoves) {
        if (move.getX() > bestMoveWithNoCorner.getX()) {
          bestMoveWithNoCorner = move;
        }
        if (move.getX() == bestMoveWithNoCorner.getX()) {
          if (move.getY() > bestMoveWithNoCorner.getY()) {
            bestMoveWithNoCorner = move;
          }
        }
      }
      logger.info("Selected move: "
              + bestMoveWithNoCorner.getX() + ", " + bestMoveWithNoCorner.getY());
      return Optional.of(bestMoveWithNoCorner);
    } else {
      Move bestMove = cornerMoves.get(0);
      for (Move cornerMove : cornerMoves) {
        if (cornerMove.getPiecesCaught() > bestMove.getPiecesCaught()) {
          bestMove = cornerMove;
        }
      }
      logger.info("Selected move: " + bestMove.getX() + ", " + bestMove.getY());
      logger.info("Capturing " + bestMove.getPiecesCaught() + " pieces!");
      return Optional.of(bestMove);
    }
  }

  /**
   * Determines if the selected move involves a corner hex.
   */
  private boolean isCornerMove(Move move, int boardSize) {
    int x = move.getX();
    int y = move.getY();
    return (x == 0 && y == (boardSize / 2) || (x == 0 && y == (-(boardSize / 2))) ||
            (x == (boardSize / 2) && y == -(boardSize / 2)) ||
            (x == (-(boardSize / 2)) && y == (boardSize / 2)));
  }
}
