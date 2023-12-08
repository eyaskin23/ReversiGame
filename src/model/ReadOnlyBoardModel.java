package model;


import controller.players.Player;
import controller.players.PlayerType;

import java.util.List;

/**
 * Creates a read only copy board.
 */
public interface ReadOnlyBoardModel {

  int getScoreWhite();

  int getScoreBlack();

  int getBoardSize();

  boolean isGameOver();

  boolean isBoardFull();

  int countPieces(PlayerType type);

  boolean hasPlayerPassed(PlayerType type);

  HexShape getCurrentHex(int row, int column);

  boolean isValidMove(int x, int y, PlayerType playerType);

  boolean isValidCoordinate(int q, int r);

  List<Move> getValidMovesWithCaptures(Player player);

  boolean isCornerMove(Move move, int boardSize);

  int getMidPoint();

  Board getRegularBoard();

  PlayerType getCurrentTurn();
}
