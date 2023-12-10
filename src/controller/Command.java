package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.BoardModel;
import model.HexBoard;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.IStrategy;
import model.strategies.TryTwo;

/**
 * A command class for the command line to work.
 */

public class Command {
  private Player player1;
  private Player player2;
  private int boardSize;
  private ReadOnlyBoardModel board;

  /**
   * Initializes the command line arguments.
   */
  public Command(String[] args) {
    parseArguments(args);
  }

  /**
   * Parses the arguments for the game to start.
   */
  private void parseArguments(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException(
              "Insufficient arguments. Expected at least" +
                      " board size and player 1 type or strategy.");
    }

    this.boardSize = Integer.parseInt(args[0]);
    this.board = new HexBoard(boardSize, isSquare(args[3]));

    player1 = findThePlayer(args, 1);
    player2 = findThePlayer(args, 2);
  }

  /**
   * Determines, the player made.
   */
  private Player findThePlayer(String[] args, int playerNumber) {
    String playerType = "human";
    String strategy = null;
    int index = -1;

    if (playerNumber == 1) {
      index = 1;
      if (args.length > 3 && isStrategy(args[3])) {
        strategy = args[3];
      }
    } else if (playerNumber == 2 && args.length > 2) {
      index = 2;
    }

    if (index != -1) {
      if (isStrategy(args[index])) {
        playerType = "ai";
        if (strategy == null) {
          strategy = args[index];
        }
      } else {
        playerType = args[index];
      }
    }

    PlayerType pType = PlayerType.BLACK;
    if (playerNumber == 2) {
      pType = PlayerType.WHITE;
    }

    return createPlayer(pType, playerType, strategy);
  }


  /**
   * Checks to see if a strategy was correctly, made.
   */
  private boolean isStrategy(String input) {
    switch (input.toLowerCase()) {
      case "capture":
      case "corner":
      case "capture corner":
      case "corner capture":
      case "capture capture":
      case "corner corner":
        return true;
      default:
        return false;
    }
  }

  private boolean isSquare(String input) {
    switch(input.toLowerCase()) {
      case "square":
        return true;
      case "hex":
        return false;
      default:
        throw new IllegalArgumentException("Need to specify type of board");
    }
  }

  /**
   * Creates a player.
   */
  private Player createPlayer(PlayerType playerType, String playerTypeStr, String strategyStr) {
    if (playerTypeStr.equalsIgnoreCase("ai")) {
      IStrategy strategy = getStrategy(strategyStr);
      return new AIPlayer("AI Player", playerType, board, strategy);
    } else {
      return new Player("Human Player", playerType, board);
    }
  }

  /**
   * Makes the Strategy based on the string passed.
   */
  private IStrategy getStrategy(String strategyStr) {
    switch (strategyStr.toLowerCase()) {
      case "capture":
      case "capture capture":
        return new CaptureStrategy();
      case "corner":
      case "corner corner":
        return new GoForCornersStrategy();
      case "capture corner":
        return new TryTwo(new CaptureStrategy(), new GoForCornersStrategy());
      case "corner capture":
        return new TryTwo(new GoForCornersStrategy(), new CaptureStrategy());
      default:
        throw new IllegalArgumentException("Invalid strategy: " + strategyStr);
    }
  }

  /**
   * Returns the boardSize that is inputted.
   */
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * Returns the first player.
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * Returns the second player.
   */
  public Player getPlayer2() {
    return player2;
  }

  /**
   * Gets the regular board, from the readOnly Board that the players use.
   */
  public BoardModel getBoard() {
    return board.getRegularBoard();
  }
}
