package cs3500.pa03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * represents a manual player
 */
public class PlayerImpl implements Player {

  private ArrayList<Coord> playerCoord; // coord of all players ships

  private ArrayList<Ship> playerShips; // all players current ships

  private ArrayList<Coord> alreadyShotAtPlayer; // shots that were shot at player

  private ArrayList<Coord> shotsThatHit; // shots that hit player

  public int numOfShips; // total number of player shots,

  private int width;
  private int height;
  private Randomable rand;
  char [][] board; // player board
  ArrayList<Coord> shipCoord; // all made Coord for player ships
  private ViewImpl view;

  private char [][] opponentBoard; // shows opponent board from player pov

  /**
   * @param height the height of a player board
   * @param width the width of a player board
   * @param view the view of a player
   */
  public PlayerImpl(int height, int width, ViewImpl view) {
    this.width = width;
    this.height = height;
    playerShips = new ArrayList<>();
    playerCoord = new ArrayList<>();
    numOfShips = 0;
    shipCoord = new ArrayList<>();
    alreadyShotAtPlayer = new ArrayList<>();
    shotsThatHit = new ArrayList<>();
    this.view = view;
    opponentBoard = new char[height][width];
  }

  /**
   * @param rand the Random passed to the player
   * @param height the height of a player board
   * @param width the width of a player board
   * @param view the view of a player
   */
  public PlayerImpl(int height, int width, ViewImpl view, Randomable rand) {
    this.width = width;
    this.height = height;
    playerShips = new ArrayList<>();
    playerCoord = new ArrayList<>();
    numOfShips = 0;
    shipCoord = new ArrayList<>();
    alreadyShotAtPlayer = new ArrayList<>();
    shotsThatHit = new ArrayList<>();
    this.view = view;
    opponentBoard = new char[height][width];
    this.rand = rand;
  }



  /**
   * @return the opponents board from player perspective
   */
  public char [][] getOpponentBoard() {
    return opponentBoard;
  }

  /**
   * @return returns the players board
   */
  public char [][] getPlayerBoard() {
    return board;
  }

  /**
   * @param shots shots that the manual player hit
   * @return a updated  opponents board
   */
  public char [][] updateOpponentsBoard(List<Coord> shots) {
    for (Coord s : shots) {
      opponentBoard[s.getX()][s.getY()] = 'X';
    }
    return opponentBoard;
  }

  /**
   * @return the players board
   */
  public char[][] makeBoard() {
    board = new char [height][width];
    for (Ship s : playerShips) {
      for (int i = 0; i < s.getLocations().size(); i++) {
        if (s.getShipType() == ShipType.CARRIER) {
          board[s.getLocations().get(i).getX()][s.getLocations().get(i).getY()] = 'C';
        } else if (s.getShipType() == ShipType.BATTLESHIP) {
          board[s.getLocations().get(i).getX()][s.getLocations().get(i).getY()] = 'B';
        } else if (s.getShipType() == ShipType.DESTROYER) {
          board[s.getLocations().get(i).getX()][s.getLocations().get(i).getY()] = 'D';
        } else if (s.getShipType() == ShipType.SUBMARINE) {
          board[s.getLocations().get(i).getX()][s.getLocations().get(i).getY()] = 'S';
        }
      }

    }
    return board;
  }

  /**
   * @param height height of board
   * @param width width of baord
   * @param size size of ship
   * @return a list of locations for one ship
   */
  public ArrayList<Coord> createFleetPositions(int height, int width, int size) {
    //    Random rand = new Random();

    int vorh = rand.nextInt(2);
    ArrayList<Coord> localCoord = new ArrayList<>();
    if (vorh == 0) {
      //will be horizontal
      int startOfShip;
      int row = rand.nextInt(width);

      if (size == height) {
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(height - size);
      }
      int i = 0;
      while (i < size) {
        Coord c = new Coord(row, startOfShip);
        if (shipCoord.stream().anyMatch(n -> (n.getX() == c.getX() && (n.getY() == c.getY())))) {
          row = rand.nextInt(width);
          if (size == height) {
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
    } else {
      int column = rand.nextInt(height);
      int startOfShip;
      if (width == size) {
        startOfShip = 0;
      } else {
        startOfShip = rand.nextInt(width - size);
      }
      int i = 0;
      while (i < size) {
        Coord c = new Coord(startOfShip, column);
        if (shipCoord.stream().anyMatch(n -> (n.getX() == c.getX() && (n.getY() == c.getY())))) {
          column = rand.nextInt(height);
          if (width == size) {
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
   * returns all of a players ship coordinates
   */
  public void getAllCoord() {
    for (int i = 0; i < playerShips.size(); i++) {
      ArrayList<Coord> currentShip = playerShips.get(i).getLocations();
      playerCoord.addAll(currentShip);
    }
  }

  /**
   * @param shots shots taken by opponent
   * @return a new board with visual changes in player board
   */
  public char[][] showHitSpots(List<Coord> shots) {
    for (int i = 0; i < shots.size(); i++) {
      Coord c = shots.get(i);
      if (playerCoord.stream().anyMatch(n -> (n.getX() == c.getX() && (n.getY() == c.getY())))) {
        Coord currentCoord = shots.get(i);
        editBoard(currentCoord);
        playerCoord.remove(currentCoord);
      }
    }
    return board;
  }

  /**
   * @return all players remaining ships
   */
  public ArrayList<Ship> returnShips() {
    return playerShips;
  }


  /**
   * @param c changes visuals of coordinate given
   */
  public void editBoard(Coord c) {
    shotsThatHit.add(c);
    board[c.getX()][c.getY()] = 'X';
  }

  /**
   * @return players name
   */
  @Override
  public String name() {
    return "Eri";
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
          int vorh = rand.nextInt(2);
          Ship carrier = new Ship(ShipType.CARRIER, 6,
              createFleetPositions(height, width, 6), false, vorh);
          playerShips.add(carrier);
          numOfShips += 1;
        } else if (i == 1) {
          int vorh = rand.nextInt(2);
          Ship battleShip = new Ship(ShipType.BATTLESHIP, 5,
              createFleetPositions(height, width, 5), false, vorh);
          playerShips.add(battleShip);
          numOfShips += 1;
        } else if (i == 2) {
          int vorh = rand.nextInt(2);
          Ship destroyer = new Ship(ShipType.DESTROYER, 4,
              createFleetPositions(height, width, 4), false, vorh);
          playerShips.add(destroyer);
          numOfShips += 1;
        } else if (i == 3) {
          int vorh = rand.nextInt(2);
          Ship submarine = new Ship(ShipType.SUBMARINE, 3,
              createFleetPositions(height, width, 3), false, vorh);
          playerShips.add(submarine);
          numOfShips += 1;
        }
      }
    }
    return playerShips;
  }

  /**
   * @return the players shots
   */
  @Override
  public List<Coord> takeShots() {
    return this.view.shots(playerShips.size());

  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return the shots that hit the players ship
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> recievedShots = new ArrayList<>();
    for (Ship s : playerShips) {
      for (int i = 0; i < opponentShotsOnBoard.size(); i++) {
        Coord c = opponentShotsOnBoard.get(i);
        if (s.getLocations().stream().anyMatch(n -> (n.getX() == c.getX()
            && (n.getY() == c.getY())))
            && !alreadyShotAtPlayer.stream().anyMatch(n -> (n.getX() == c.getX()
                && (n.getY() == c.getY())))) {
          s.reduceSize();
          alreadyShotAtPlayer.add(c);
          recievedShots.add(c);
        }
      }
    }
    showHitSpots(recievedShots);
    return recievedShots;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    updateOpponentsBoard(shotsThatHitOpponentShips);
  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    return;
  }

}
