package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ship tests
 */
class ShipTest {
  Ship ship;
  ArrayList<Coord> coordList;

  Coord coord;
  Coord coord2;

  /**
   * before each
   */
  @BeforeEach
  public void beforeEach() {
    coord = new Coord(2, 2);
    coord2 = new Coord(2, 2);
    coordList = new ArrayList<>();
    coordList.add(coord);
    coordList.add(coord2);

    ship = new Ship(ShipType.CARRIER, 2, coordList, false, 1);

  }

  /**
   * test get locations , is sunk, get ship type , and return size
   */
  @Test
  public void testGet() {
    assertEquals(coordList, ship.getLocations());
    assertEquals(ShipType.CARRIER, ship.getShipType());
    assertFalse(ship.isSunk());
    assertEquals(2, ship.returnsize());
  }

  /**
   * test reduce size
   */
  @Test
  public void testReduce() {
    assertEquals(2, ship.returnsize());
    ship.reduceSize();
    assertEquals(1, ship.returnsize());
    ship.reduceSize();
    assertTrue(ship.isSunk());
  }

}