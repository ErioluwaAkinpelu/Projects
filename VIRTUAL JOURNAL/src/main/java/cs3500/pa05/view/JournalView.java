package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The JournalView class represents the view for the journal, responsible for loading the layout
 * and creating the scene.
 */
public class JournalView {

  private final FXMLLoader loader;

  /**
   * Constructs a new JournalView object with the specified controller.
   *
   * @param controller the journal controller
   */
  public JournalView(JournalController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("board.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads the journal layout and returns the corresponding scene.
   *
   * @return the loaded scene
   * @throws IllegalStateException if the layout cannot be loaded
   */
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Can't load layout.");
    }
  }
}

