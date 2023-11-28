package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test view class
 */
class ViewImplTest {
  StringBuilder actualOutput;
  ViewImpl view;

  Readable actualInput;

  /**
   * before each
   */
  @BeforeEach
  public void beforeEach() {
    actualOutput = new StringBuilder();
    view = new ViewImpl(actualOutput);

  }

  /**
   * @throws IOException if write cannot be done
   */
  @Test
  public void testWrite() throws IOException {
    view.write("Test");

    assertEquals("Test", actualOutput.toString());
  }

  /**
   * @throws IOException if welcome cannot be done
   */
  @Test
  public void testWelcome() throws IOException {
    view.Welcome();

    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"+"Please enter a height and width between the 6-15\n"
        , actualOutput.toString());
  }

  /**
   * @throws IOException if correct user cannot be done
   */
  @Test

  public void testCorrectUser() throws IOException {
    view.CorrectUser();

    assertEquals("The height and width must be between 6 and 15 inclusive, please enter new values\n"
        , actualOutput.toString());
  }

  /**
   * @throws IOException if choosefleet cannot be done
   */
  @Test
  public void testChooseFleet() throws IOException {
    view.ChooseFleet();

    assertEquals("Please enter your fleet in the order Carrier[6], BattleShip[5], Destroyer[4], Submarine[3]\n",
        actualOutput.toString());
  }

  /**
   * @throws IOException if draw board cannot be done
   */
  @Test
  public void drawBoard() throws IOException {
    char[][] board = new char[3][2];
    view.drawBoard(3,2,board);

    assertEquals("\u0000 \u0000 \n\u0000 \u0000 \n\u0000 \u0000 \n", actualOutput.toString());
  }

  /**
   * @throws IOException if send shots cannot be done
   */
  @Test
  public void testSendShots() throws IOException {
  view.promptShots(4);

    assertEquals("Please provide " + 4 + " coordinates\n", actualOutput.toString());
  }

  /**
   * @throws IOException if tests shots cannot be done
   */
  @Test
  public void testShots() throws IOException {
    ArrayList<Coord> cList = new ArrayList<>();

  Coord c = new Coord(2,2);
  Coord c1 = new Coord(4, 4);
    cList.add(c);
    cList.add(c1);
    actualInput = new StringReader("2 " + "2 " + "4 "+ "4 ");
//    assertEquals(2,view.shots(2).size());
  }


}