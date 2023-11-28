package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.ArrayEquals;

/**
 * AIPlayer tests
 */
class AIPlayerTest {
  AIPlayer ai;

  /**
   * before each
   */
@BeforeEach
  public void beforeEach(){

  ai = new AIPlayer(4,4);

}

  /**
   * tests for create locations
   */
  @Test
  public void testcreateFleetPositions (){
    assertEquals(5,ai.createFleetPositions(8,8,5).size());
    assertEquals(6,ai.createFleetPositions(6,6,6).size());
  }

  /**
   * tests for test name
   */
  @Test
  public void testName (){
    assertEquals(null,ai.name());

  }

  /**
   * tests for setup
   */
  @Test
  public void testSetup (){
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.BATTLESHIP,1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);
    assertEquals(6,ai.setup(8,8, specifications).size());

  }

  /**
   * tests for take shots
   */
  @Test
  public void testtakeShots (){
  assertEquals(0, ai.takeShots().size());
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.BATTLESHIP,1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);
    ai.setup(8,8, specifications);
    assertEquals( 6,ai.takeShots().size());


  }

  /**
   * tests for report damage
   */
  @Test
  public void testReportDamage (){
  ArrayList<Coord> cList = new ArrayList<>();
  Coord c = new Coord(2,2);
  cList.add(c);
assertEquals(0,ai.reportDamage(cList).size());


  }

  /**
   * tests for successfulHits
   */
  @Test
  public void testSuccessFulHits (){
    ArrayList<Coord> cList = new ArrayList<>();
    Coord c = new Coord(2,2);
    cList.add(c);
    ai.successfulHits(cList);
    assertEquals(1,ai.hitAIAlready.size());


  }


  /**
   * tests for return ships
   */
@Test
  public void testreturnShips (){
  assertEquals(0,ai.returnShips().size());
  HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
  specifications.put(ShipType.CARRIER, 2);
  specifications.put(ShipType.BATTLESHIP,1);
  specifications.put(ShipType.DESTROYER, 2);
  specifications.put(ShipType.SUBMARINE, 1);
  ai.setup(8,8, specifications);

  assertEquals(6,ai.returnShips().size());
  }
}