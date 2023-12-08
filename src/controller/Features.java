package controller;

/**
 * Interface for listening to player actions in a game, otherwise
 * a Features Interface.
 */
public interface Features {

  /**
   * Invoked when a player makes a move.
   */
  void onPlayerMove(int row, int column);

  /**
   * Invoked when a player passes their turn.
   */
  void onPass();
}
