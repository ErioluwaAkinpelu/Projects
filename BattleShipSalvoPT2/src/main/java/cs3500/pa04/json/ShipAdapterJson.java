package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.Ship;

/**
 * Adapts a Ship instance into a Json that the server can understand
 *
 * @param start where the coordinate begins
 * @param length the length of the ship
 * @param direction whether the ship is horizontal or vertical
 */
public record ShipAdapterJson(@JsonProperty("coord") CoordJson start,
                              @JsonProperty("length") int length,
                              @JsonProperty("direction") cs3500.pa03.Direction direction) {

  /**
   * Method that converts a ship to a ShipAdapterJson
   *
   * @param myShip the ship being converted
   */
  public ShipAdapterJson(Ship myShip) {
    this(
        new CoordJson(myShip.getLocations().get(0).getX(), myShip.getLocations().get(0).getY()),
        myShip.returnsize(),
        myShip.numToDir(myShip));
  }
}
