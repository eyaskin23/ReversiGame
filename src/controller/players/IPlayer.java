package controller.players;

/**
 * A Player interface for all text-based views, to be used in the Reversi game.
 */
public interface IPlayer {

  void makeMove(int row, int column);

  void setHasPassed();


}
