package cs3500.pa03;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    ViewImpl view = new ViewImpl(System.out);
    Controller control = new Controller(view,new InputStreamReader(System.in));
    try {
      control.beforeGame();
      control.prepareGame();
      control.gameInPlay();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}