package cs3500.pa05.controller;

import cs3500.pa05.model.BujoFileReader;
import cs3500.pa05.view.JournalView;
import javafx.stage.Stage;


/**
 * The JournalInitializer class initializes the journal application by loading the necessary
 * components
 * and displaying the journal view on the specified stage.
 */
public class JournalInitializer {

  private final Stage stage;
  private final String inputPath;
  private final String outputPath;

  /**
   * Constructs a JournalInitializer object.
   *
   * @param stage the JavaFX stage on which the journal view will be displayed
   * @param inputPath the path to the input file
   * @param outputPath the path to the output file
   */
  public JournalInitializer(Stage stage, String inputPath, String outputPath) {
    this.stage = stage;
    this.inputPath = inputPath;
    this.outputPath = outputPath;
  }


  /**
   * Runs the journal application by initializing the necessary components, loading the journal
   * view, and displaying it on the specified stage.
   */
  public void run() {

    BujoFileReader bfr = new BujoFileReader(inputPath, outputPath);
    JournalController jc = bfr.convertFileToBujo();
    JournalView jv = new JournalView(jc);

    try {
      //  Load and place the view's scene onto the stage
      stage.setScene(jv.load());

      jc.run();

      // Render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

}
