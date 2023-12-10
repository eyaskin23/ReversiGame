package model;

import controller.players.Player;
import controller.players.PlayerType;

import java.util.List;

/**
 * Represents a mock board used for testing strategies.
 */
public class Mock extends HexBoard
        implements ReadOnlyBoardModel, BoardModel {
  List<Move> validMoves;
  StringBuilder log;
  ReadOnlyBoardModel board;

  /**
   * Constructor used for mock, to test that the StringBuilder
   * is correctly being updated.
   */
  public Mock(ReadOnlyBoardModel board, List<Move> validMoves, StringBuilder log) {
    this.validMoves = validMoves;
    this.board = board;
    this.log = log;
  }

  /**
   * Returns the score of a white player in the board.
   */
  @Override
  public int getScoreWhite() {
    log.append("Getting white player's score: ").append(board.getScoreWhite());
    return board.getScoreWhite();
  }

  /**
   * Returns the score of a black player in the board.
   */
  @Override
  public int getScoreBlack() {
    log.append("Getting black player's score: ").append(board.getScoreBlack());
    return board.getScoreBlack();
  }

  /**
   * Returns the board size.
   */
  @Override
  public int getBoardSize() {
    log.append("Getting board size: ").append(board.getBoardSize());
    return board.getBoardSize();
  }

  /**
   * Determines if the game is over.
   */
  @Override
  public boolean isGameOver() {
    if (board.isGameOver()) {
      log.append("view.Game is over.");
    } else {
      log.append("view.Game is still running.");
    }
    return board.isGameOver();
  }

  /**
   * Determines if the board is full.
   */
  @Override
  public boolean isBoardFull() {
    if (board.isBoardFull()) {
      log.append("The board is full.");
    } else {
      log.append("The board is not full.");
    }
    return board.isBoardFull();
  }

  /**
   * Counts the amount of pieces that a certain player has
   * on the board.
   */
  @Override
  public int countPieces(PlayerType type) {
    if (type == null) {
      throw new IllegalArgumentException("PlayerType cannot be null");
    }
    int piecesCount = board.countPieces(type);
    log.append("Counting pieces for ").append(type).append(": ").append(piecesCount);
    return piecesCount;
  }

  /**
   * Determines if a player has passed their turn.
   */
  @Override
  public boolean hasPlayerPassed(PlayerType type) {
    if (board.hasPlayerPassed(type)) {
      log.append("Player has passed");
    } else {
      log.append("Player has not passed.");
    }
    return board.hasPlayerPassed(type);
  }

  /**
   * Returns the current hex in a board.
   */
  @Override
  public HexShape getCurrentHex(int row, int column) {
    return cellsThatMakeTheBoard[row][column];
  }

  /**
   * Determines if the passed in coordinates is a valid move.
   */
  @Override
  public boolean isValidMove(int x, int y, PlayerType playerType) {
    if (board.isValidMove(x, y, playerType)) {
      log.append("Valid move.");
    } else {
      log.append("Not a valid move.");
    }
    return board.isValidMove(x, y, playerType);
  }

  /**
   * Determines if the passed in coordinates are valid.
   */
  @Override
  public boolean isValidCoordinate(int q, int r) {
    if (board.isValidCoordinate(q, r)) {
      log.append("Valid coordinate.");
    } else {
      log.append("Not a valid coordinate.");
    }
    return board.isValidCoordinate(q, r);
  }

  /**
   * Returns a list of valid moves for the capture strategy.
   */
  @Override
  public List<Move> getValidMovesWithCaptures(Player player) {
    log.append("Checking valid :")
            .append(player)
            .append(board.getValidMovesWithCaptures(player));
    return board.getValidMovesWithCaptures(player);
  }

  /**
   * Determines if a certain move is a corner.
   */
  @Override
  public boolean isCornerMove(Move move, int boardSize) {
    if (board.isCornerMove(move, boardSize)) {
      log.append(move).append(" is a corner move.");
    } else {
      log.append(move).append(" is not a corner move.");
    }
    return board.isCornerMove(move, boardSize);
  }

  /**
   * Returns the log for each method for testing.
   */
  public StringBuilder getLog() {
    return log;
  }
}

