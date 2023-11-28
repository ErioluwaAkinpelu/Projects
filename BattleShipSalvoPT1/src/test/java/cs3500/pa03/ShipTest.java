package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ship tests
 */
class ShipTest {
  Ship s;
  ArrayList<Coord> cList;

  Coord c;
  Coord c2;

  /**
   * before each
   */
  @BeforeEach
public void beforeEach() {
    c = new Coord(2, 2);
    c2 = new Coord(2, 2);
    cList = new ArrayList<>();
    cList.add(c);
    cList.add(c2);


 s = new Ship(ShipType.CARRIER, 2, cList, false);

}

  /**
   * test get locations , is sunk, get ship type , and return size
   */
  @Test
  public void testGet() {

    assertEquals(cList, s.getLocations());
    assertEquals(ShipType.CARRIER, s.getShipType());
    assertFalse(s.isSunk());
    assertEquals(2, s.returnsize());
  }

  /**
   * test reduce size
   */
  @Test
  public void testReduce() {
    assertEquals(2, s.returnsize());
    s.reduceSize();
    assertEquals(1, s.returnsize());
    s.reduceSize();
    assertTrue(s.isSunk());
  }

}