package cs3500.pa03;

/**
 * class for a location on a board
 */
public class Coord {
  private int xcoord;
  private int ycoord;

  /**
   * @param x the x location
   * @param y the y location
   */
  public Coord(int x, int y) {
    this.xcoord = x;
    this.ycoord = y;
  }

  /**
   * @return the x of a coordinate
   */
  public int getX() {
    return xcoord;
  }

  /**
   * @return returns the y of a coordinate
   */
  public int getY() {
    return ycoord;
  }
}
