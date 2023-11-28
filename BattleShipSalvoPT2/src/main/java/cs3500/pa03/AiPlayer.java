package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * representation of AI
 */
public class AiPlayer implements Player {


  private final ArrayList<Ship> computerShips;

  public  ArrayList<Coord> hitAiAlready;

  private final ArrayList<Coord> alreadyShotAtPlayer;
  private final int width;
  private final int height;
  public int numOfShips;
  private char[][] board;
  ArrayList<Coord> shipCoord;

  ArrayList<Coord> notAlreadyShotCoords = new ArrayList<>();


  /**
   * @param height height of AI board
   * @param width width of AI board
   */
  public AiPlayer(int height, int width) {
    this.width = width;
    this.height = height;
    computerShips = new ArrayList<>();
    alreadyShotAtPlayer = new ArrayList<>();
    shipCoord = new ArrayList<>();
  }

  /**
   * @param height height of board
   * @param width width of board
   * @param size size of ship
   * @return Coord of one ship
   */
  public ArrayList<Coord> createFleetPositions(int height, int width, int size, int vorh) {
    Random rand = new Random();
    ArrayList<Coord> localCoord = new ArrayList<>();
    if (vorh == 0) {
      //will be horizontal
      int startOfShip;
      if (size == height) {
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(height - size);
      }
      int i = 0;
      while (i < 1) {
        int row = rand.nextInt(width);
        Coord c = new Coord(row, startOfShip);
        if (!allValidCoordinates(c, size, vorh)) {
          continue;
        }
        localCoord = createValidCoordinates(c, size, vorh);
        i = 1;
      }
    } else {
      int startOfShip;
      if (width == size) {
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(width - size);
      }
      int i = 0;
      while (i < 1) {
        int column = rand.nextInt(height);
        Coord c = new Coord(startOfShip, column);
        if (!allValidCoordinates(c, size, vorh)) {
          continue;
        }
        localCoord = createValidCoordinates(c, size, vorh);
        i = 1;
      }
    }
    shipCoord.addAll(localCoord);
    return localCoord;
  }

  private boolean allValidCoordinates(Coord coord, int size, int direction) {
    //0 is horizontal, 1 is vertical
    ArrayList<Coord> coordList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      if (direction == 0) {
        Coord c = new Coord(coord.getX() + i, coord.getY());
        if (shipCoord.stream().anyMatch(n -> (n.getX() == c.getX()
            && (n.getY() == c.getY())))
            && coord.getX() + i < width) {
          return false;
        }
        coordList.add(new Coord(coord.getX() + i, coord.getY()));
      } else {
        Coord c = new Coord(coord.getX(), coord.getY() + i);
        if (shipCoord.stream().anyMatch(n -> (n.getX() == c.getX()
            && (n.getY() == c.getY())))
            && coord.getY() + i < height) {
          return false;
        }
        coordList.add(new Coord(coord.getX(), coord.getY() + i));
      }
    }
    return true;
  }

  private ArrayList<Coord> createValidCoordinates(Coord coord, int size, int direction) {
    //0 is horizontal, 1 is vertical
    ArrayList<Coord> coordList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      if (direction == 0) {
        coordList.add(new Coord(coord.getX() + i, coord.getY()));
      } else {
        coordList.add(new Coord(coord.getX(), coord.getY() + i));
      }
    }
    return coordList;
  }

  /**
   * @return return list of AI Ships
   */
  public ArrayList<Ship> returnShips() {
    return computerShips;
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
   * @return Returns the list of ships that are included in the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    int numOfShip = 0;
    for (int i = 0; i < specifications.size(); i++) {
      if (i == 0) {
        numOfShip = specifications.get(ShipType.CARRIER);
      } else if (i == 1) {
        numOfShip = specifications.get(ShipType.BATTLESHIP);
      } else if (i == 2) {
        numOfShip = specifications.get(ShipType.DESTROYER);
      } else if (i == 3) {
        numOfShip = specifications.get(ShipType.SUBMARINE);
      }
      for (int s = 0; s < numOfShip; s++) {
        if (i == 0) {
          int vorh = new Random().nextInt(2);
          Ship carrier = new Ship(ShipType.CARRIER, 6,
              createFleetPositions(height, width, 6, vorh), false, vorh);
          computerShips.add(carrier);
          numOfShips += 1;
        } else if (i == 1) {
          int vorh = new Random().nextInt(2);
          Ship battleShip = new Ship(ShipType.BATTLESHIP, 5,
              createFleetPositions(height, width, 5, vorh), false, vorh);
          computerShips.add(battleShip);
          numOfShips += 1;
        } else if (i == 2) {
          int vorh = new Random().nextInt(2);
          Ship destroyer = new Ship(ShipType.DESTROYER, 4,
              createFleetPositions(height, width, 4, vorh), false, vorh);
          computerShips.add(destroyer);
          numOfShips += 1;
        } else if (i == 3) {
          int vorh = new Random().nextInt(2);
          Ship submarine = new Ship(ShipType.SUBMARINE, 3,
              createFleetPositions(height, width, 3, vorh), false, vorh);
          computerShips.add(submarine);
          numOfShips += 1;
        }
      }
    }
    initializeAllCoords();
    return computerShips;
  }

  /**
   * @return the shots made by the AI
   */
  @Override
  public List<Coord> takeShots() {
    Random rand = new Random();
    ArrayList<Coord> deployedShots = new ArrayList<>();
    for (int i = 0; i < computerShips.size(); i++) {
      if (notAlreadyShotCoords.size() > 0) {
        int randomCoord = rand.nextInt(notAlreadyShotCoords.size());
        Coord c = notAlreadyShotCoords.get(randomCoord);
        deployedShots.add(c);
        notAlreadyShotCoords.remove(randomCoord);
      }
    }
    return deployedShots;
  }

  private void initializeAllCoords() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        //????????????????????????
        notAlreadyShotCoords.add(new Coord(j, i));
      }
    }
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return returns the oponnent shots that hit your ship
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {

    ArrayList<Coord> recievedShots = new ArrayList<>();
    for (Ship s : computerShips) {
      for (int i = 0; i < opponentShotsOnBoard.size(); i++) {
        Coord c = opponentShotsOnBoard.get(i);
        if (s.getLocations().stream()
            .anyMatch(n -> (n.getX() == c.getX() && (n.getY() == c.getY())))
            && !alreadyShotAtPlayer.stream()
            .anyMatch(n -> (n.getX() == c.getX() && (n.getY() == c.getY())))) {
          s.reduceSize();
          alreadyShotAtPlayer.add(c);
          recievedShots.add(c);
        }
      }
    }

    filterDestroyedShips();
    return recievedShots;
  }

  private void filterDestroyedShips() {
    ArrayList<Ship> destroyedShips = new ArrayList<>();
    for (Ship s : computerShips) {
      if (s.isSunk()) {
        destroyedShips.add(s);
      }
    }

    for (Ship s : destroyedShips) {
      computerShips.remove(s);
    }
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    hitAiAlready = new ArrayList<>();
    hitAiAlready.addAll(shotsThatHitOpponentShips);
  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }

}