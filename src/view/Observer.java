package view;

/**
 * Observer interface representing a generic observer in the observer pattern.
 * This interface is used to notify implementing classes of certain events or changes.
 */
public interface Observer {

  /**
   * Update method called to notify the observer of a change or event.
   */
  void update();

  /**
   * Method called to notify the observer of a game over condition, this allows the game
   * to know when the game is over.
   */
  void onGameOver();
}
