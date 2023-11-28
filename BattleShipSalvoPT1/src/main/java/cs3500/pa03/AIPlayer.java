package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * representation of AI
 */
public class AIPlayer implements Player {


  private ArrayList<Ship> ComputerShips;

  public  ArrayList<Coord> hitAIAlready;

  private ArrayList<Coord> AlreadyShotAtPlayer;
  private int width;
  private int height;
  public int numOfShips;
  char [][] board;
  ArrayList<Coord> shipCoord;

  /**
   * @param height height of AI board
   * @param width width of AI board
   */
  AIPlayer(int height, int width) {
    this.width = width;
    this.height = height;
    ComputerShips = new ArrayList<>();
    AlreadyShotAtPlayer = new ArrayList<>();
    shipCoord = new ArrayList<>();
  }


  /**
   * @param height height of board
   * @param width width of board
   * @param size size of ship
   * @return Coord of one ship
   */
  public ArrayList<Coord> createFleetPositions(int height, int width,int size) {
    Random rand = new Random();
    int VorH = rand.nextInt(2);
    ArrayList<Coord> localCoord = new ArrayList<>();
    if(VorH == 0) {
      //will be horizontal
      int startOfShip;
      int row = rand.nextInt(width);
      if(size == height){
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(height - size);
      }
      int i = 0;
      while (i < size) {
        Coord c = new Coord(row,startOfShip);
        if(shipCoord.stream().anyMatch(n -> (n.getX()== c.getX() && (n.getY() == c.getY())))) {
          row = rand.nextInt(width);
          if(size == height){
            startOfShip = 0;
          } else {
            startOfShip = rand.nextInt(height - size);
          }
          localCoord = new ArrayList<Coord>();
          i = 0;
          continue;
        } else {
          localCoord.add(c);
          startOfShip += 1;
        }
        i++;
      }
    }
    else {
      int column = rand.nextInt(height);
      int startOfShip;
      if(width == size){
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(width - size);
      }
      int i =0;
      while(i < size) {
        Coord c = new Coord(startOfShip,column);
        if(shipCoord.stream().anyMatch(n -> (n.getX()== c.getX() && (n.getY() == c.getY())))) {
          column = rand.nextInt(height);
          if(width == size){
            startOfShip = 0;
          } else {
            startOfShip = rand.nextInt(width - size);
          }
          localCoord = new ArrayList<Coord>();
          i = 0;
          continue;
        } else {
          localCoord.add(c);

          startOfShip += 1;
        }
        i++;
      }
    }
    shipCoord.addAll(localCoord);
    return localCoord;
  }

  /**
   * @return return list of AI Ships
   */
  public ArrayList<Ship> returnShips() {
    return ComputerShips;
  }

  /**
   * @return returns AI name
   */
  @Override
  public String name() {
    return null;
  }

  /**
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    int numOfShip = 0;
    for(int i = 0; i < specifications.size(); i++) {
      if (i == 0) {
        numOfShip = specifications.get(ShipType.CARRIER);
      } else if (i == 1) {
        numOfShip = specifications.get(ShipType.BATTLESHIP);
      }else if (i == 2) {
        numOfShip = specifications.get(ShipType.DESTROYER);
      }
      else if (i == 3) {
        numOfShip = specifications.get(ShipType.SUBMARINE);
      }
      for(int s = 0; s < numOfShip; s++){
        if(i == 0){
          Ship Carrier = new Ship(ShipType.CARRIER, 6, createFleetPositions(height,width,6), false);
          ComputerShips.add(Carrier);
          numOfShips += 1;
        }
        else if(i == 1){
          Ship BattleShip = new Ship(ShipType.BATTLESHIP, 5, createFleetPositions(height,width,5), false);
          ComputerShips.add(BattleShip);
          numOfShips += 1;
        }
        else if(i == 2){
          Ship Destroyer = new Ship(ShipType.DESTROYER, 4, createFleetPositions(height,width,4), false);
          ComputerShips.add(Destroyer);
          numOfShips += 1;
        }
        else if(i == 3){
          Ship Submarine = new Ship(ShipType.SUBMARINE, 3, createFleetPositions(height,width,3), false);
          ComputerShips.add(Submarine);
          numOfShips += 1;
        }
      }
    }
    return ComputerShips;
  }

  /**
   * @return the shots made by the AI
   */
  @Override
  public List<Coord> takeShots() {
    Random rand = new Random();
    ArrayList<Coord> deployedShots = new ArrayList<>();
    for (int i = 0; i < ComputerShips.size(); i++) {
    int x = rand.nextInt(height);
    int y = rand.nextInt(width);
    Coord c = new Coord(x,y);
    deployedShots.add(c);

}
    return deployedShots;
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return returns the oponnent shots that hit your ship
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {

    ArrayList<Coord> recievedShots = new ArrayList<>();
    for (Ship s : ComputerShips){
      for(int i = 0; i < opponentShotsOnBoard.size(); i++) {
        Coord c = opponentShotsOnBoard.get(i);
        if (s.getLocations().stream().anyMatch(n -> (n.getX()== c.getX() && (n.getY() == c.getY()))) &&
            !AlreadyShotAtPlayer.stream().anyMatch(n -> (n.getX()== c.getX() && (n.getY() == c.getY())))) {
          s.reduceSize();
          AlreadyShotAtPlayer.add(c);
          recievedShots.add(c);
        }
      }
    }

    return recievedShots;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
 hitAIAlready = new ArrayList<>();
 hitAIAlready.addAll(shotsThatHitOpponentShips);
  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }
}
