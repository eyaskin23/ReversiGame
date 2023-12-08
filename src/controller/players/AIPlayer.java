package controller.players;

import java.util.Optional;

import model.ReadOnlyBoardModel;
import model.strategies.IStrategy;
import model.Move;

/**
 * Represents a computer player in the reversi game.
 */
public class AIPlayer extends Player implements IPlayer, TurnAIPopUp {
  private final IStrategy strategy;

  /**
   * Constructor for an AIPlayer.
   */
  public AIPlayer(String name, PlayerType type, ReadOnlyBoardModel board, IStrategy strategy) {
    super("Computer", type, board);
    this.strategy = strategy;
    this.hasPassed = false;
  }

  /**
   * Makes a move for the AIPlayer.
   */
  public void makeMove() {
    Optional<Move> selectedMove = strategy.selectMove(this.board, this);
    if (selectedMove.isPresent()) {
      super.makeMove(selectedMove.get().getX(), selectedMove.get().getY());
    } else {
      this.setHasPassed();
    }
  }


  /**
   * Tells the AI, to make the move.
   */
  @Override
  public void itIsNowYourTurnMessage() {
    this.makeMove();
  }
}
