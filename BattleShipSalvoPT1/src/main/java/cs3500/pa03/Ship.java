package cs3500.pa03;

import java.util.ArrayList;

/**
 * type of ship
 */
enum ShipType {
CARRIER,
BATTLESHIP,

DESTROYER,
SUBMARINE,
}

/**
 * representation of a ship
 */
public class Ship {
  private ShipType type;
  private int size;

  private ArrayList<Coord> locations;

  private boolean destroyed;

  /**
   * @param type ship type
   * @param size size
   * @param locations locations that ship is
   * @param destroyed whether ship is sunk or not
   */
  Ship(ShipType type, int size, ArrayList<Coord> locations, boolean destroyed){
    this.type = type;
    this.size = size;
    this.locations = locations;
    this.destroyed = destroyed;
  }

  /**
   * @return returns locations of ship
   */
  public ArrayList<Coord> getLocations(){
    return locations;
  }

  /**
   * @return returns type of ship
   */
  public ShipType getShipType() {
    return type;
  }

  /**
   * @return whether or not the ship is sunk
   */
public boolean isSunk() {
    return destroyed;
}

  /**
   * reduce size of ship
   */
  public void reduceSize() {
    if(size > 1) {
      size = size - 1;
    } else if(size == 1) {
      size = 0;
    destroyed = true;
    }
  }

  /**
   * @return the size of ship
   */
  public int returnsize() {
    return this.size;
  }






}
