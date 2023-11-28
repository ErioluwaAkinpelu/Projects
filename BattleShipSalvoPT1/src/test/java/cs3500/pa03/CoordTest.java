package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Coord tests
 */
class CoordTest {
  Coord c;

  @BeforeEach

  public void beforeEach() {
     c = new Coord(1, 3);

  }

  /**
   * test getter functions
   */
  @Test
  public void testGet() {

    assertEquals(1, c.getX());
    assertEquals(3, c.getY());
  }
}