package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Coord tests
 */
class CoordTest {
  Coord coord;

  @BeforeEach

  public void beforeEach() {
    coord = new Coord(1, 3);

  }

  /**
   * test getter functions
   */
  @Test
  public void testGet() {

    assertEquals(1, coord.getX());
    assertEquals(3, coord.getY());
  }
}