package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import javax.swing.text.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Controller tests
 */
class ControllerTest {
Controller control;
ViewImpl view;
Readable actualInput;
StringBuilder actualOutput;


  /**
   * before Each
   */
@BeforeEach
public void beforeEach() {


}

  /**
   * @throws IOException if before game cannot be done
   */
@Test
  public void testBeforeGame() throws IOException {
  actualOutput = new StringBuilder();
  actualInput = new StringReader("8\n" + "8\n" + "1\n" + "1\n" + "1\n" + "1\n");
  view = new ViewImpl(actualOutput);
  control = new Controller(view,actualInput);
 control.beforeGame();
  assertEquals("Welcome ot the OOD BattleSalvo Game !\n"+"Please enter a height and width between the 6-15\n"
      + "Please enter your fleet in the order Carrier[6], BattleShip[5], Destroyer[4], Submarine[3]\n"
      + "Your fleet may not exceed size 8\n"
      , actualOutput.toString());
}

  /**
   * @throws IOException if prepare game cannot be done
   */
  @Test
  public void testprepareGame() throws IOException {

//    actualInput = new StringReader("1 " + "1 " + "1 " + "1 " );
//    actualOutput = new StringBuilder();
//    view = new ViewImpl(actualOutput);
//
//    control = new Controller(view,actualInput);
//    control.prepareGame();
//    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"+"Please enter a height and width between the 6-15\n"
//            + "Please enter your fleet in the order Carrier[6], BattleShip[5], Destroyer[4], Submarine[3]\n"
//            + "Your fleet may not exceed size 8\n"
//        , actualOutput.toString());
  }

  /**
   * @throws IOException if gameInplay cannot be done
   */
  @Test
  public void testgameInPlay() throws IOException {
    actualOutput = new StringBuilder();
//    actualInput = new StringReader("1\n" + "1\n" + "1\n" + "1\n" );
//    view = new ViewImpl(actualOutput);
//    control = new Controller(view,actualInput);
//    control.gameInPlay();
//    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"+"Please enter a height and width between the 6-15\n"
//            + "Please enter your fleet in the order Carrier[6], BattleShip[5], Destroyer[4], Submarine[3]\n"
//            + "Your fleet may not exceed size 8\n"
//        , actualOutput.toString());
  }
}