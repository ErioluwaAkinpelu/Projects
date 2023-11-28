package cs3500.pa05.view;

import cs3500.pa05.model.BuJoObtainer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The StartScreen class represents the start screen of the journal application.
 * It provides methods to display different scenes and retrieve user input.
 */
public class StartScreen {

  private final Stage stage;
  private VBox titleDisplay;
  private TextField passwordEntry;
  private final Button passwordButton;
  private final Button createNewWeekButton;
  private final Button openWeekButton;
  private TextField newWeekName;
  private ComboBox<String> fileSelect;
  private VBox passwordDisplay;
  private final HBox hbox;
  private final Scene root;
  private final BuJoObtainer bjo;

  /**
   * Constructs a StartScreen object.
   *
   * @throws IOException if an I/O error occurs during file traversal
   */
  public StartScreen() throws IOException {
    this.stage = new Stage();
    this.hbox = new HBox();
    VBox vbox = new VBox();
    this.hbox.setAlignment(Pos.CENTER);
    vbox.getChildren().add(hbox);
    vbox.setAlignment(Pos.CENTER);
    vbox.setBackground(Background.fill(Color.NAVY));
    this.passwordButton = new Button("Enter");
    this.openWeekButton = new Button("Open Week");
    this.createNewWeekButton = new Button("Create New Week");
    this.root = new Scene(vbox, 600, 600);
    this.bjo = new BuJoObtainer();
    Files.walkFileTree(Path.of("src/main/resources"), bjo);
  }

  /**
   * Sets the specified scene as the current scene of the stage and displays it.
   *
   * @param root the scene to be displayed
   */
  private void showScene(Scene root) {
    stage.setScene(root);
    stage.setTitle("D.E.A Journal");
    stage.show();
  }

  /**
   * Displays the start screen's root scene with the splash screen
   */
  public void startSplashScreen() {
    Label title = new Label("D.E.A Journal");
    title.setFont(Font.font("Bauhaus 93", 80));
    title.setTextFill(Color.GOLD);

    Label tip = new Label("Click anywhere to begin");
    tip.setFont(Font.font("Bauhaus 93", 36));
    tip.setTextFill(Color.GOLD);

    titleDisplay = new VBox(title, tip);

    hbox.getChildren().add(titleDisplay);

    showScene(root);
  }

  /**
   * Displays the start screen's root scene with the password screen.
   */
  public void startPasswordScreen() {
    root.setOnMouseClicked(null);
    passwordEntry = new TextField();
    passwordEntry.setPromptText("Password");
    passwordEntry.setMinWidth(200);

    passwordDisplay = createTextFieldDisplay("Type password in here: ",
        passwordEntry, passwordButton);

    hbox.getChildren().remove(titleDisplay);
    hbox.getChildren().add(passwordDisplay);

  }

  /**
   * Displays the start screen's root scene with the create scene.
   */
  public void startCreateScene() {
    newWeekName = new TextField();
    newWeekName.setPromptText("Week Name");

    VBox newWeekNameDisplay = createTextFieldDisplay("Type new week name : ",
        newWeekName, createNewWeekButton);

    fileSelect = new ComboBox<>(FXCollections.observableArrayList(getBujoFiles()));
    fileSelect.setPromptText("Choose a file");

    VBox openFileDisplay = createTextFieldDisplay("Open an existing file : ",
        fileSelect, openWeekButton);

    VBox createSceneDisplay = new VBox(newWeekNameDisplay, openFileDisplay);
    createSceneDisplay.setSpacing(100);

    hbox.getChildren().remove(passwordDisplay);
    hbox.getChildren().add(createSceneDisplay);

  }

  /**
   * Retrieves the list of available BuJo files.
   *
   * @return the list of BuJo file paths
   */
  public ArrayList<String> getBujoFiles() {
    return bjo.getBujoFilePaths();
  }

  /**
   * Creates a display VBox for a text field input with a label and button.
   *
   * @param contents the label text
   * @param field    the text field node
   * @param button   the button node
   * @return the created VBox display
   */
  private VBox createTextFieldDisplay(String contents, Node field, Button button) {
    Label label = new Label(contents);
    label.setTextFill(Color.GOLD);
    label.setWrapText(true);
    label.setFont(Font.font("Bauhaus 93", 18));

    HBox topDisplay = new HBox(label, field);

    VBox display = new VBox(topDisplay, button);
    display.setBackground(Background.fill(Color.NAVY));
    display.setAlignment(Pos.CENTER);
    display.setSpacing(10);

    return display;
  }

  /**
   * Retrieves the password button.
   *
   * @return the password button
   */
  public Button getPasswordButton() {
    return this.passwordButton;
  }

  /**
   * Retrieves the open week button.
   *
   * @return the open week button
   */
  public Button getOpenWeekButton() {
    return this.openWeekButton;
  }

  /**
   * Retrieves the create new week button.
   *
   * @return the create new week button
   */
  public Button getCreateNewWeekButton() {
    return this.createNewWeekButton;
  }

  /**
   * Retrieves the root scene of the start screen.
   *
   * @return the root scene
   */
  public Scene getRoot() {
    return this.root;
  }

  /**
   * Retrieves the password attempt entered by the user.
   *
   * @return the password attempt
   */
  public String getPasswordAttempt() {
    return this.passwordEntry.getText();
  }

  /**
   * Retrieves the new week name entered by the user.
   *
   * @return the new week name
   */
  public String getNewWeekName() {
    return this.newWeekName.getText();
  }

  /**
   * Retrieves the selected file from the file select combo box.
   *
   * @return the selected file
   */
  public String getSelectedFile() {
    return this.fileSelect.getSelectionModel().getSelectedItem();
  }

  /**
   * Closes the start screen.
   */
  public void close() {
    this.stage.close();
  }
}