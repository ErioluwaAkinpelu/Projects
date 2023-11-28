package cs3500.pa03;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class
 */
public class Controller {


  private ViewImpl view;

  private AIPlayer aiPlayer;

  private Readable input;

  private PlayerImpl player1;

  private int height;

  private int width;

  private AIPlayer player2;
//  private final Readable input;
//  private final Appendable output;

  /**
   * @param view  the class responsible for displaying the program
   * @param input the class responsible for reading User input
   */
  public Controller(ViewImpl view, Readable input) {
    this.view = view;
    this.input = input;

  }

  /**
   * @throws IOException when the game cannot be prepared
   */
  public void beforeGame() throws IOException {
    view.Welcome();
    boolean InvalidValue = true;
    Scanner scan = new Scanner(input);
    while (InvalidValue) {
      height = scan.nextInt();
      width = scan.nextInt();
      if (!(height >= 6 && height <= 15) && !(width >= 6 && width <= 15)) {
        view.CorrectUser();
        continue;
      } else {
        InvalidValue = false;
      }
      view.ChooseFleet();
      int maxSize = Math.min(height, width);
      view.write("Your fleet may not exceed size " + maxSize + "\n");
    }


  }

  /**
   * @throws IOException when the board cannot be made
   */
  public void prepareGame() throws IOException {
    Scanner scan = new Scanner(input);
    boolean inValidFleet = true;
    while (inValidFleet) {
      ArrayList<Integer> totalFleet = new ArrayList<>();
      int numOfCarrier = scan.nextInt();
      int numOfBs = scan.nextInt();
      int numOfDes = scan.nextInt();
      int numOfSm = scan.nextInt();
      totalFleet.add(numOfCarrier);
      totalFleet.add(numOfBs);
      totalFleet.add(numOfDes);
      totalFleet.add(numOfSm);
      int maxSize = Math.min(height, width);
      if (numOfCarrier + numOfBs + numOfDes + numOfSm > maxSize) {
        view.write("Your fleet size exceeds the max\n");
      } else if (numOfCarrier == 0 || numOfBs == 0 || numOfDes == 0 || numOfSm == 0) {
        view.write("You must have at least one of each fleet\n");
      } else {
        HashMap<ShipType, Integer> specifications = new HashMap<ShipType, Integer>();
        specifications.put(ShipType.CARRIER, numOfCarrier);
        specifications.put(ShipType.BATTLESHIP, numOfBs);
        specifications.put(ShipType.DESTROYER, numOfDes);
        specifications.put(ShipType.SUBMARINE, numOfSm);
        aiPlayer = new AIPlayer(height, width);
        player1 = new PlayerImpl(height, width, view);
        player1.setup(height, width, specifications);
        aiPlayer.setup(height, width, specifications);
        char[][] playerBoard = player1.makeBoard();
        view.drawBoard(height, width, player1.getOpponentBoard());
        view.write("\n");
        view.drawBoard(height, width, playerBoard);
        inValidFleet = false;
      }
    }
  }

  /**
   * @throws IOException when the game cannot continue
   */
     public void gameInPlay() throws IOException {
      boolean GameNotOver = true;
      while (GameNotOver) {

        view.promptShots(player1.numOfShips);
        player1.getAllCoord();
        List<Coord> aiShots = aiPlayer.takeShots();
        List<Coord> manualShots = player1.takeShots();
        player1.successfulHits(aiPlayer.reportDamage(manualShots));
        aiPlayer.successfulHits(player1.reportDamage(aiShots));
        view.drawBoard(height, width, player1.getOpponentBoard());
        view.write("\n");
        view.drawBoard(height, width, player1.getPlayerBoard());
        GameResult GR = new GameResult();
        GameResult.result result = GR.returnResult(player1.returnShips(), aiPlayer.returnShips());
        if (result.equals(GameResult.result.INPLAY)) {
          continue;
        } else {
          player1.endGame(GR, "Some reason");
          aiPlayer.endGame(GR, "some reason");
          GameNotOver = false;
          System.out.println("The game is over");

        }

      }
    }


}
