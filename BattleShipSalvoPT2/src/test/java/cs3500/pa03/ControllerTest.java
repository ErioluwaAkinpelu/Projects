package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Scanner;
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
  private ByteArrayOutputStream show;
  private ByteArrayInputStream read;


  /**
   * @throws IOException if before game cannot be done
   */
  @Test
  public void testBeforeGame() throws IOException {
    actualOutput = new StringBuilder();
    actualInput = new StringReader("12\n" + "12\n" + "1\n" + "1\n" + "1\n" + "1\n");
    view = new ViewImpl(actualOutput);
    control = new Controller(view, actualInput);
    control.beforeGame();
    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"
            + "Please enter a height and width between the 6-15\n"
        + "Please enter your fleet in the order Carrier[6], "
        + "BattleShip[5], Destroyer[4], Submarine[3]\n"
        + "Your fleet may not exceed size 12\n", actualOutput.toString());
  }


  @BeforeEach
  public void giveOutput() {
    this.show = new ByteArrayOutputStream();
    System.setOut(new PrintStream(this.show));
  }


  //  /**
  //   * @throws IOException if prepare game cannot be done
  //   */
  //  @Test
  //  public void testPrepareGame() throws IOException {
  //    actualOutput = new StringBuilder();
  //    actualInput = new StringReader("8\n" + "8\n" + "1\n" + "1\n" + "1\n" + "1\n"
  //                                     + "1" + "1" + "1" + "1");
  //    view = new ViewImpl(actualOutput);
  //    control = new Controller(view, actualInput, new MockRandom());
  //    control.beforeGame();
  //    control.prepareGame();
  //    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"
  //            + "Please enter a height and width between the 6-15\n"
  //            + "Please enter your fleet in the order Carrier[6], BattleShip[5],"
  //            + "Destroyer[4], Submarine[3]\n"
  //            + "Your fleet may not exceed size 15\n"
  //            + "\u0000 ", actualOutput.toString());
  //  }
  //
  //  /**
  //   * @throws IOException if gameInplay cannot be done
  //   */
  //  @Test
  //  public void testGameInPlay() throws IOException {
  //    actualOutput = new StringBuilder();
  //    actualInput = new StringReader("1\n" + "1\n" + "1\n" + "1\n");
  //    //        view = new ViewImpl(actualOutput);
  //    //        control = new Controller(view,actualInput);
  //    control.gameInPlay();
  //    assertEquals("Welcome ot the OOD BattleSalvo Game !\n"
  //            + "Please enter a height and width between the 6-15\n"
  //            + "Please enter your fleet in the order Carrier[6], BattleShip[5],"
  //            + "Destroyer[4], Submarine[3]\n"
  //            + "Your fleet may not exceed size 8\n", actualOutput.toString());
  //  }
}