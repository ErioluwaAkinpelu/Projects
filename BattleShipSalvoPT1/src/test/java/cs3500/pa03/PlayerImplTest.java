package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * player tests
 */
class PlayerImplTest {
PlayerImpl player;
ViewImpl view;

@BeforeEach
public void beforeEach(){

  player = new PlayerImpl(8,8,view);

}

  /**
   * test create locations
   */
//  @Test
//  public void returnOppBoard (){
//  char[][] oppBoard = new char[][];
//    assertEquals(oppBoard,player.getOpponentBoard());
//
//  }
@Test
public void testcreateFleetPositions (){
  assertEquals(5,player.createFleetPositions(8,8,5).size());
  assertEquals(6,player.createFleetPositions(6,6,6).size());
}

  /**
   * test return ships
   */
  @Test
  public void testreturnShips (){
    assertEquals(0,player.returnShips().size());
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.BATTLESHIP,1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);
    player.setup(8,8, specifications);

    assertEquals(6,player.returnShips().size());
    player.getAllCoord();

  }

  /**
   * test name
   */
  @Test
  public void testName (){
    assertEquals("Eri",player.name());

  }

  /**
   * test setup
   */
  @Test
  public void testSetup (){
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    specifications.put(ShipType.CARRIER, 2);
    specifications.put(ShipType.BATTLESHIP,1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);
    assertEquals(6,player.setup(8,8, specifications).size());

  }

  /**
   * test report damage
   */
  @Test
  public void testReportDamage (){
    ArrayList<Coord> cList = new ArrayList<>();
    Coord c = new Coord(2,2);
    cList.add(c);
    assertEquals(0,player.reportDamage(cList).size());


  }

  /**
   * test successful hits
   */
  @Test
  public void testSuccessFulHits (){
    ArrayList<Coord> cList = new ArrayList<>();
    Coord c = new Coord(2,2);
    cList.add(c);
    player.successfulHits(cList);


  }

  /**
   * test return board
   */
  @Test
  public void testReturnBoard(){
    player.getPlayerBoard();
    player.getOpponentBoard();

  }

  /**
   * test report damage
   */
  @Test
  public void testreportDamage(){
    ArrayList<Coord> cList = new ArrayList<>();
    Coord c = new Coord(2,2);
    cList.add(c);
    assertEquals(0,player.reportDamage(cList).size());


  }

  /**
   * test void board functions
   */
  @Test
  public void testVoid(){
  Coord c = new Coord(3,3);
  ArrayList<Coord> cList = new ArrayList<>();
  cList.add(c);
  cList.add(c);
    HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP,1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
 player.setup(8,8, specifications).size();
  player.makeBoard();
  player.editBoard(c);
  player.showHitSpots(cList);
  }


}