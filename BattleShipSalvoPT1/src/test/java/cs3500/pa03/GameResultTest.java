package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test game results
 */
class GameResultTest {
GameResult GR;

ArrayList<Coord> l1;
Coord c1;


ArrayList<Coord> l2;

Coord c2;
ArrayList<Ship> pShips;

ArrayList<Ship> cShips;

Ship s1;

Ship s2;
Ship s3;
Ship s4;

  /**
   * before each
   */
  @BeforeEach

  public void beforeEach() {
    l1 = new ArrayList<>();
    c1 = new Coord(1, 1);

    l2 = new ArrayList<>();
    c2 = new Coord(1, 1);

    GR = new GameResult();

    s1 = new Ship(ShipType.SUBMARINE, 3, l1, false);
    s2 = new Ship(ShipType.SUBMARINE, 3, l1, false);
    s3 = new Ship(ShipType.SUBMARINE, 3, l1, true);
    s4 = new Ship(ShipType.SUBMARINE, 3, l1, true);

  }

  /**
   * test in play
   */
  @Test
  public void testGet() {
    pShips = new ArrayList<>();
    pShips.add(s1);
    pShips.add(s1);
    cShips = new ArrayList<>();
    cShips.add(s2);
    cShips.add(s2);
  assertEquals(GameResult.result.INPLAY,GR.returnResult(pShips,cShips));

  }

  /**
   * test draw
   */
  @Test
  public void testDraw() {
    pShips = new ArrayList<>();
    pShips.add(s3);
    pShips.add(s3);
    cShips = new ArrayList<>();
    cShips.add(s3);
    cShips.add(s3);

    assertEquals(GameResult.result.DRAW,GR.returnResult(pShips,cShips));

  }

  /**
   * test lost
   */
  @Test
  public void testLost() {
    pShips = new ArrayList<>();
    pShips.add(s3);
    pShips.add(s3);
    cShips = new ArrayList<>();
    cShips.add(s1);
    cShips.add(s3);

    assertEquals(GameResult.result.LOSE,GR.returnResult(pShips,cShips));

  }

  /**
   * test win
   */
  @Test
  public void testWin() {
    pShips = new ArrayList<>();
    pShips.add(s1);
    pShips.add(s3);
    cShips = new ArrayList<>();
    cShips.add(s3);
    cShips.add(s3);

    assertEquals(GameResult.result.WIN,GR.returnResult(pShips,cShips));

  }

}