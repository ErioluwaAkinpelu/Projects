package cs3500.pa05.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


/**
 * Represents a display-only pop-up that implements the Popup interface.
 */
public class DisplayOnlyPopUp implements Popup {
  private final String content;
  private final String title;
  private final WarningType type;
  private final Button closeButton;


  /**
   * Constructs a DisplayOnlyPopUp object with the specified content, title, and warning type.
   *
   * @param content the content of the pop-up
   * @param title   the title of the pop-up
   * @param type    the warning type of the pop-up
   */
  public DisplayOnlyPopUp(String content, String title, WarningType type) {
    this.content = content;
    this.title = title;
    this.type = type;
    this.closeButton = new Button("Close"); // Creating a close button
  }

  /**
   * Displays the pop-up.
   */
  @Override
  public void displayPopup() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.getButtonTypes().setAll(ButtonType.CLOSE);
    alert.showAndWait();
  }

  /**
   * Prints a message when testing errors rather than display an Alert
   */
  public void testPopUp() {
    System.out.println(title + ": " + content);
  }

}
