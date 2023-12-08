package controller;

/**
 * Sets up an enum for directions.
 */
public enum DirectionsEnum {
  NORTH_EAST(1, -1),
  EAST(1, 0),
  SOUTH_EAST(0, 1),
  SOUTH_WEST(-1, 1),
  WEST(-1, 0),
  NORTH_WEST(0, -1);

  private final int qMove;
  private final int rMove;

  /**
   * Constructor used to make a move.
   */
  DirectionsEnum(int qMove, int rMove) {
    this.qMove = qMove;
    this.rMove = rMove;
  }

  /**
   * Gets the Row move.
   */
  public int getQMove() {
    return qMove;
  }

  /**
   * Gets the Column move.
   */
  public int getRMove() {
    return rMove;
  }
}

