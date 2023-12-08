package view;

import controller.players.PlayerType;
import model.Board;
import model.HexShape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the view of a Reversi Board.
 */
public interface ReversiView {

  /**
   * Updates the view.
   */
  void update();

  /**
   * Finds the hex based on the mouse position and should
   * recognize the Hex it is on.
   */
  HexShape findHex(int mouseX, int mouseY);

  /**
   * Returns the hex size of a single hex.
   */
  int getHexSize();

  /**
   * Checks if the mouse exists in a hex.
   */
  boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize);

  /**
   * Returns the window width.
   */
  int getWindowWidth();

  /**
   * Draws each individual hexagon.
   */
  void drawEachHexagon(Graphics g, HexShape hex,
                       int centerX, int centerY, int hexSize, PlayerType playerType);

  /**
   * Returns the color for a player.
   */
  Color getColor(PlayerType playerType);

  /**
   * Draws the board out.
   */
  void drawBoard(Graphics g, Board board);


  /**
   * Gets the height of the window made.
   */
  int getWindowHeight();
}
