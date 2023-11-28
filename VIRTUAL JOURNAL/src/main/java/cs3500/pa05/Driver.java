package cs3500.pa05;


import cs3500.pa05.controller.StartScreenController;
import cs3500.pa05.view.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Driver class serves as the entry point for the application and initializes the GUI.
 */
public class Driver extends Application {

  /**
   * The entry point for the JavaFX application.
   *
   * @param stage the primary stage for the application
   * @throws Exception if an error occurs during the initialization of the GUI
   */
  @Override
  public void start(Stage stage) throws Exception {
    StartScreenController scc = new StartScreenController(new StartScreen(), stage);
    scc.run();


  }

  /**
   * The main method, which launches the JavaFX application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}