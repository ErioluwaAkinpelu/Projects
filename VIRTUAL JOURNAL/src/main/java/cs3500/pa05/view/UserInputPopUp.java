package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.UserInputPopHandler;
import cs3500.pa05.model.Popup;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a user input popup window.
 * Implements the Popup interface.
 */
public class UserInputPopUp implements Popup {
  private Stage primaryStage;
  private Scene scene;
  private Button createButton;
  private TabPane tabPane;
  private VBox root;

  /**
   * Constructs a UserInputPopUp object.
   *
   * @param primaryStage The primary stage to display the popup.
   * @param controller   The journal controller.
   */
  public void start(Stage primaryStage, JournalController controller) {
    this.primaryStage = primaryStage;
    root = new VBox();
    createUi(controller);

    // Create the buttons layout
    HBox buttonsLayout = new HBox(10);
    buttonsLayout.getChildren().addAll(createButton);

    // Create the root layout

    root.setPadding(new Insets(10));
    root.setSpacing(10);
    root.getChildren().addAll(buttonsLayout);

    // Create the scene and stage
    scene = new Scene(root, 400, 330);
    displayPopup();
  }

  /**
   * Creates the user interface elements for the popup.
   *
   * @param controller The journal controller.
   */
  private void createUi(JournalController controller) {
    // Create ComboBox for item selection
    createButton = new Button("Create");

    createButton.setOnAction(e -> UserInputPopHandler.handleCreateButton(
        tabPane.getSelectionModel().getSelectedItem(), controller));

    // Create the Event tab
    GridPane eventPane = new EventPane();
    Tab eventTab = new Tab("Event", eventPane);
    eventTab.setClosable(false);

    // Create the Task tab
    GridPane taskPane = new TaskPane();
    Tab taskTab = new Tab("Task", taskPane);
    taskTab.setClosable(false);

    GridPane quotePane = new QuotePane(controller.getLabels());
    Tab quoteTab = new Tab("Quote", quotePane);
    quoteTab.setClosable(false);

    GridPane notePane = new NotePane(controller.getLabels());
    Tab noteTab = new Tab("Note", notePane);
    noteTab.setClosable(false);

    // Create the TabPane
    tabPane = new TabPane();
    tabPane.getTabs().addAll(eventTab, taskTab, quoteTab, noteTab);

    // Create the main layout using VBox
    root.getChildren().add(tabPane);
  }


  /**
   * Displays the popup.
   */
  @Override
  public void displayPopup() {
    primaryStage.setScene(scene);
    primaryStage.setTitle("User Input Popup");
    primaryStage.show();
  }


}