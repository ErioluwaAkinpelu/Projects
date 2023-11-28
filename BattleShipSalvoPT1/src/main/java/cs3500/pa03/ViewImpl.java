package cs3500.pa03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.text.View;

/**
 * creates visuals of program
 */
public class ViewImpl {
private Appendable appendable;


  /**
   * @param appendable what is shown to the screen
   */
public ViewImpl(Appendable appendable) {
  this.appendable = Objects.requireNonNull(appendable);

}

  /**
   * @param phrase phrase to be written to screen
   * @throws IOException when the phrase cannot be written to screen
   */
public void write(String phrase) throws IOException {
  appendable.append(phrase);
}

  /**
   * @throws IOException when welcome cannot be written to screen
   */
public void Welcome() throws IOException {
  appendable.append("Welcome ot the OOD BattleSalvo Game !\n"+"Please enter a height and width between the 6-15\n");
}

  /**
   * @throws IOException when correctUser cannot be written to screen
   */
public void CorrectUser() throws IOException {
  appendable.append("The height and width must be between 6 and 15 inclusive, please enter new values\n");
}

  /**
   * @throws IOException When choose fleet cannot be written to screen
   */
  public void ChooseFleet() throws IOException {
    appendable.append("Please enter your fleet in the order Carrier[6], BattleShip[5], Destroyer[4], Submarine[3]\n");
  }


  /**
   * @param height height of board
   * @param width width of board
   * @param board board in 2D array version
   * @throws IOException when the board cannot be made
   */
public void drawBoard(int height, int width, char[][] board) throws IOException {
  for(int i = 0; i < height; i++) {

    for (int j = 0; j < width; j++) {

      appendable.append(board[i][j] + " ");
    }
    appendable.append("\n");
  }
  }

  /**
   * @param shots number of shots player can have
   * @throws IOException when promptShot can not be written to screen
   */
public void promptShots(int shots) throws IOException {
  appendable.append("Please provide " + shots + " coordinates\n");
}

  /**
   * @param shots the number of shots a player
   * @return the list of shots the player chose
   */
public List<Coord> shots(int shots) {
  ArrayList<Coord> AllCoord = new ArrayList<>();
  Scanner scan = new Scanner(System.in);
  for(int i = 0; i < shots; i++){
    int x = scan.nextInt();
    int y = scan.nextInt();
    Coord c = new Coord(x,y);
    AllCoord.add(c);
  }
  return AllCoord;
}


}

