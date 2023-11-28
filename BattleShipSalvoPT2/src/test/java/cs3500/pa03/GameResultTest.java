package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test game results
 */
class GameResultTest {
  GameResult gameResult;

  ArrayList<Coord> l1;
  Coord c1;


  ArrayList<Coord> l2;

  Coord c2;
  ArrayList<Ship> playerShips;

  ArrayList<Ship> coordShips;

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

    gameResult = new GameResult();

    s1 = new Ship(ShipType.SUBMARINE, 3, l1, false, 1);
    s2 = new Ship(ShipType.SUBMARINE, 3, l1, false, 1);
    s3 = new Ship(ShipType.SUBMARINE, 3, l1, true, 1);
    s4 = new Ship(ShipType.SUBMARINE, 3, l1, true, 1);

  }

  /**
   * test in play
   */
  @Test
  public void testGet() {
    playerShips = new ArrayList<>();
    playerShips.add(s1);
    playerShips.add(s1);
    coordShips = new ArrayList<>();
    coordShips.add(s2);
    coordShips.add(s2);
    assertEquals(GameResult.Result.INPLAY, gameResult.returnResult(playerShips, coordShips));

  }

  /**
   * test draw
   */
  @Test
  public void testDraw() {
    playerShips = new ArrayList<>();
    playerShips.add(s3);
    playerShips.add(s3);
    coordShips = new ArrayList<>();
    coordShips.add(s3);
    coordShips.add(s3);

    assertEquals(GameResult.Result.DRAW, gameResult.returnResult(playerShips, coordShips));

  }

  /**
   * test lost
   */
  @Test
  public void testLost() {
    playerShips = new ArrayList<>();
    playerShips.add(s3);
    playerShips.add(s3);
    coordShips = new ArrayList<>();
    coordShips.add(s1);
    coordShips.add(s3);

    assertEquals(GameResult.Result.LOSE, gameResult.returnResult(playerShips, coordShips));

  }

  /**
   * test win
   */
  @Test
  public void testWin() {
    playerShips = new ArrayList<>();
    playerShips.add(s1);
    playerShips.add(s3);
    coordShips = new ArrayList<>();
    coordShips.add(s3);
    coordShips.add(s3);

    assertEquals(GameResult.Result.WIN, gameResult.returnResult(playerShips, coordShips));

  }

}