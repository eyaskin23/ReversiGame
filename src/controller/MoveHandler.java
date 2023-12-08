package controller;

import controller.players.Player;

/**
 * A move handler interface, that handles the move for the player.
 */
public interface MoveHandler {

  /**
   * A move handler that signals the controller to handle the move.
   */
  void handleMove(Player player, int row, int column);
}
