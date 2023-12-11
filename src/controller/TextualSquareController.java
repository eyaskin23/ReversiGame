package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.players.PlayerType;
import model.HexBoard;
import view.ReversiTextualView;

public class TextualSquareController implements ReversiTextualView {
  private final HexBoard board;
  private Appendable output;

  public TextualSquareController(HexBoard board) {
    if (board == null) {
      throw new IllegalArgumentException("Board cannot be null.");
    }
    this.board = board;
    this.output = System.out;
  }

  @Override
  public void render() throws IOException {
    output.append(toString());
  }

  @Override
  public String toString() {
    StringBuilder stringMaker = new StringBuilder();
    int sizeOfEntireBoard = board.getBoardSize();

    Map<PlayerType, Character> playerSymbols = new HashMap<>();
    playerSymbols.put(PlayerType.BLACK, 'O');
    playerSymbols.put(PlayerType.WHITE, 'X');
    playerSymbols.put(PlayerType.EMPTY, '-');

    int middleRow = sizeOfEntireBoard / 2;
    int middleCol = sizeOfEntireBoard / 2;

    for (int row = 0; row < sizeOfEntireBoard; row++) {
      for (int col = 0; col < sizeOfEntireBoard; col++) {
        char symbol;

        if ((row == middleRow || row == middleRow - 1) && (col == middleCol || col == middleCol - 1)) {
          if ((row == middleRow && col == middleCol) || (row == middleRow - 1 && col == middleCol - 1)) {
            symbol = playerSymbols.get(PlayerType.BLACK);
          } else {
            symbol = playerSymbols.get(PlayerType.WHITE);
          }
        } else {
          symbol = playerSymbols.getOrDefault(board.getCurrentHex(row, col).getPlayerType(), '-');
        }

        stringMaker.append(symbol).append(' ');
      }
      stringMaker.append('\n');
    }
    return stringMaker.toString();
  }
}
