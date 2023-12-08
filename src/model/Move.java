package model;

import java.util.Objects;

/**
 * Represents a move in the game.
 */
public class Move {
  private int x;
  private int y;
  private int piecesCaught;

  /**
   * Constructor that takes in a row and a column.
   */
  public Move(int x, int y) {
    this.x = x;
    this.y = y;
    this.piecesCaught = 0; // Default to 0, can be set later if needed
  }

  /**
   * Constructor that takes in a row, column, and the
   * number of pieces that the player flips.
   */
  public Move(int x, int y, int piecesCaught) {
    this.x = x;
    this.y = y;
    this.piecesCaught = piecesCaught;
  }

  /**
   * Gets the x coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate.
   */
  public int getY() {
    return y;
  }

  /**
   * Gets the number of pieces flipped.
   */
  public int getPiecesCaught() {
    return piecesCaught;
  }

  /**
   * Sets the x coordinate.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y coordinate.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Sets the number of pieces caught.
   */
  public void setPiecesCaught(int piecesCaught) {
    this.piecesCaught = piecesCaught;
  }

  /**
   * Overrides the equals method to ensure uniqueness.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Move move = (Move) o;
    return x == move.x && y == move.y && piecesCaught == move.piecesCaught;
  }

  /**
   * Overrides the hashCode method to ensure uniqueness.
   */
  @Override
  public int hashCode() {
    return Objects.hash(x, y, piecesCaught);
  }
}
