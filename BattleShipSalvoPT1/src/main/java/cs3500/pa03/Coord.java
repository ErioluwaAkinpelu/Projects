package cs3500.pa03;

/**
 * class for a location on a board
 */
public class Coord {
  private int x;
  private int y;

  /**
   * @param x the x location
   * @param y the y location
   */
  Coord(int x, int y){
    this.x = x;
    this.y = y;
  }

  /**
   * @return the x of a coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * @return returns the y of a coordinate
   */
  public int getY(){
    return y;
  }
}
