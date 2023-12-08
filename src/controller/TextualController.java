package controller;

import java.io.IOException;

import model.Board;
import model.HexShape;
import view.ReversiTextualView;

/**
 * The Textual Controller tracks and exports the board to the user so that
 * they can interact with it.
 */

public class TextualController implements ReversiTextualView {
  private final Board board;
  private final Appendable output;

  /**
   * controller.TextualController constructor that only takes in the model which
   * is the first game state.
   */
  public TextualController(Board board) {
    if (board == null) {
      throw new IllegalArgumentException("model.Board cannot be null");
    }
    this.board = board;
    this.output = System.out;
  }

  /**
   * Constructor for controller.TextualController.
   */
  public TextualController(Board board, Appendable output) {
    if (board == null) {
      throw new IllegalArgumentException("model.Board cannot be null");
    }
    this.board = board;
    this.output = output;
  }

  /**
   * Renders the game using the appendable builder method.
   */
  @Override
  public void render() throws IOException {
    output.append(toString());
  }


  /**
   * Returns a to-Commandline view
   * representation of the Reversi model.Board Model.
   */
  @Override
  public String toString() {
    StringBuilder stringMaker = new StringBuilder();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = sizeOfEntireBoard / 2;

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int numOfHexagons;

      if (currentRow <= midPoint) {
        numOfHexagons = midPoint + currentRow + 1;
      } else {
        numOfHexagons = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - numOfHexagons);

      stringMaker.append(" ".repeat(Math.max(0, spacesBefore)));
      for (int h = 0; h < numOfHexagons; h++) {
        HexShape currentHexagon;
        if (currentRow <= midPoint) {
          currentHexagon = board.getCurrentHex(currentRow, spacesBefore + h);
        } else {
          currentHexagon = board.getCurrentHex(currentRow, h);
        }
        String currentPlayerInTheHexagon = currentHexagon.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }
      stringMaker.append('\n');
    }

    return stringMaker.toString();
  }
}
