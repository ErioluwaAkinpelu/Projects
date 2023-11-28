package cs3500.pa05.controller;

import cs3500.pa05.model.DisplayOnlyPopUp;
import cs3500.pa05.model.SecureBulletJournalModel;
import cs3500.pa05.model.WarningType;
import cs3500.pa05.view.StartScreen;
import javafx.stage.Stage;

/**
 *The StartScreenController class controls the behavior of the start screen of the application.
 * It handles user interactions and initiates actions based on those interactions.
 */
public class StartScreenController {
  private final StartScreen startScreen;

  private final SecureBulletJournalModel secureBulletJournalModel;

  private final Stage stage;

  /**
   * Constructs a StartScreenController object.
   *
   * @param startScreen the start screen view
   * @param stage the JavaFX stage
   */
  public StartScreenController(StartScreen startScreen, Stage stage) {
    this.startScreen = startScreen;
    this.secureBulletJournalModel = new SecureBulletJournalModel("WeLoveOOD123");
    this.stage = stage;
    initButtons();
  }

  /**
   * Runs the start screen by displaying the splash screen.
   */
  public void run() {
    startScreen.startSplashScreen();
  }

  /**
   * Initializes the buttons and their event handlers on the start screen.
   */
  private void initButtons() {
    this.startScreen.getRoot().setOnMouseClicked(e -> this.startScreen.startPasswordScreen());
    this.startScreen.getPasswordButton().setOnAction(e -> attemptPassword());
    this.startScreen.getOpenWeekButton().setOnAction(e -> attemptToOpenWeek());
    this.startScreen.getCreateNewWeekButton().setOnAction(e -> attemptToCreateNewWeek());
  }

  /**
   * Attempts to authenticate the user with the entered password.
   * If successful, starts the create scene on the start screen.
   * Otherwise, displays an error message indicating the incorrect password.
   */
  private void attemptPassword() {
    if (this.secureBulletJournalModel.authenticate(this.startScreen.getPasswordAttempt())) {
      this.startScreen.startCreateScene();
    } else {
      new DisplayOnlyPopUp("Incorrect Password. Try again", "Incorrect Password",
          WarningType.BLANKFIELD).displayPopup();
    }
  }

  /**
   * Attempts to open the selected week file.
   * If no file is selected, displays an error message.
   * Otherwise, initializes and runs the JournalInitializer with the selected file and closes the
   * start screen.
   */
  private void attemptToOpenWeek() {
    if (this.startScreen.getSelectedFile() == null) {
      new DisplayOnlyPopUp("No File Selected. Try Again.", "No File Selected",
          WarningType.BLANKFIELD).displayPopup();
    } else {
      new JournalInitializer(this.stage,
          "src/main/resources/" + this.startScreen.getSelectedFile(),
          "src/main/resources/" + this.startScreen.getSelectedFile()).run();
      this.startScreen.close();
    }
  }

  /**
   * Attempts to create a new week file.
   * If no name is given, displays an error message.
   * If the name is already taken, displays an error message.
   * Otherwise, initializes and runs the JournalInitializer with the new week name and
   * closes the start screen.
   */
  private void attemptToCreateNewWeek() {
    if (this.startScreen.getNewWeekName().isBlank()) {
      new DisplayOnlyPopUp("No Name Given. Try Again.", "No Name Given",
          WarningType.BLANKFIELD).displayPopup();
    } else if (this.startScreen.getBujoFiles().contains(this.startScreen.getNewWeekName())) {
      new DisplayOnlyPopUp("Name Taken. Try Again.", "Name Taken",
          WarningType.BLANKFIELD).displayPopup();
    } else {
      new JournalInitializer(this.stage,
          "src/main/java/cs3500/pa05/template/template",
          "src/main/resources/" + this.startScreen.getNewWeekName()).run();
      this.startScreen.close();
    }
  }
}