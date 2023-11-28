
package cs3500.pa05.controller;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.CategoryFilter;
import cs3500.pa05.model.DayOfTheWeek;
import cs3500.pa05.model.DefaultWeekModel;
import cs3500.pa05.model.Entry;
import cs3500.pa05.model.EssentialPart;
import cs3500.pa05.model.EssentialPartDisplay;
import cs3500.pa05.model.SundayStartWeekModel;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.ThemeDropdown;
import cs3500.pa05.model.WeekDay;
import cs3500.pa05.model.WeekModel;
import cs3500.pa05.view.BujoFileWriter;
import cs3500.pa05.view.UserInputPopUp;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Journal controller class
 */
public class JournalController {

  /**
   * The dropdown theme bar taken sent in to the constructor. It isn't originally
   * in the board.fxml scene, but its themeComboBox is added in with initDropdown()
   */
  private final ThemeDropdown themeBar;

  /**
   * The label at the top of the screen with the name of the week. Its text is set
   * with initLabel()
   */
  @FXML
  private TextField weekName;
  /**
   * The string which is set as the text for weekName in initLabel()
   */
  private final String name;
  /**
   * The HBox at the top containing the weekName (contained within another HBox), saveButton,
   * addButton, and theme dropdown (after initDropdown() is called)
   */
  @FXML
  private HBox topRow;
  /**
   * The HBox containing the 7 WeekDays
   */
  @FXML
  private HBox weekView;
  /**
   * The list of WeekDays to be added to the weekView
   */
  private final ArrayList<WeekDay> weekDays;
  /**
   * The save button, which saves the bullet journal to a file
   */
  @FXML
  private Button saveButton;
  /**
   * The add button, which lets the user add a task or event
   */
  @FXML
  private Button addButton;
  @FXML
  private Button changeWeekStartButton;
  /**
   * The writer for saving the bullet journal to a file
   */
  private final BujoFileWriter writer;
  /**
   * The path that the file is saved to
   */
  private final String filePath;
  private CategoryFilter categoryFilter;
  @FXML
  private TitledPane quotesAndNotes;
  private final ArrayList<Entry> quotesAndNotesList;
  @FXML
  private AnchorPane background;
  private final ArrayList<Label> labels;
  private int maxEventPerDay;
  private int maxTaskPerDay;
  private WeekModel weekModel;

  /**
   * Constructs a JournalController object with the specified parameters.
   *
   * @param name     the name of the journal
   * @param themeBar the theme dropdown component
   * @param weekDays the list of week days
   * @param filePath the file path for writing the journal

   * @param maxEventPerDay the maximum amount of events per day
   * @param maxTaskPerDay the maximum amount of tasks per day
   * @param quotesAndNotesList the list of quotes and notes
   * @param weekModel the week model for the journal
   */
  public JournalController(String name, ThemeDropdown themeBar, ArrayList<WeekDay> weekDays,
                           String filePath, int maxEventPerDay, int maxTaskPerDay,
                           ArrayList<Entry> quotesAndNotesList, WeekModel weekModel) {
    this.themeBar = themeBar;
    this.name = name;
    this.weekDays = weekDays;
    this.writer = new BujoFileWriter();
    this.filePath = filePath;
    this.labels = new ArrayList<>();
    this.maxEventPerDay = maxEventPerDay;
    this.maxTaskPerDay = maxTaskPerDay;
    this.quotesAndNotesList = quotesAndNotesList;
    this.weekModel = weekModel;

  }




  /**
   * Runs the journal application by initializing UI elements.
   */
  public void run() {
    initTopRow();
    initName();
    initDropdown();
    initWeekView();
    initQuotesAndNotes();
    initLabels();
    initTheme();
  }

  private void initQuotesAndNotes() {
    AnchorPane anchorPane = (AnchorPane) quotesAndNotes.getContent();
    VBox quoteNoteDisplay = (VBox) anchorPane.getChildren().get(0);
    for (Entry quotesAndNotes : quotesAndNotesList) {
      Label quoteNoteLabel = new Label(quotesAndNotes.format());
      quoteNoteLabel.setWrapText(true);
      labels.add(quoteNoteLabel);
      // need to figure out why this is an issue
      quoteNoteDisplay.getChildren().add(quoteNoteLabel);
    }
  }

  /**
   *
   */
  public void initLabels() {
    for (WeekDay day : weekDays) {
      labels.addAll(day.getLabels());
    }
  }

  /**
   * shows the theme
   */
  public void initTheme() {
    this.themeBar.getThemeApplier().applyTheme(background, labels, weekDays, quotesAndNotes);
  }

  /**
   * Initializes the week view by adding week days to the UI.
   */
  private void initWeekView() {
    weekView.getChildren().removeAll(weekView.getChildren());
    boolean endsWithSunday = false;
    WeekDay sunday = null;
    for (WeekDay day : weekDays) {
      if (weekModel.getStartOfWeek().equals(day.getDayOfWeek())) {
        weekView.getChildren().add(0, day);
      } else if (!weekModel.getStartOfWeek().equals(day.getDayOfWeek())
          && day.getDayOfWeek() == DayOfTheWeek.SUNDAY) {
        sunday = day;
        endsWithSunday = true;
      } else {
        weekView.getChildren().add(day);
      }
      
    }
    if (endsWithSunday) {
      weekView.getChildren().add(sunday);
    }
    addDeleteButton();
  }

  /**
   *
   */
  public void addDeleteButton() {
    for (WeekDay day : weekDays) {
      VBox parts = (VBox) day.getContent();
      for (Node part : parts.getChildren()) {
        if (part instanceof EssentialPartDisplay row) {
          if (!(row.getChildren().get(row.getChildren().size() - 1) instanceof Button)) {
            Button deleteButton = new Button();
            deleteButton.setText("X");
            deleteButton.setOnAction(e -> removeEssentialPart(row, day, parts));
            row.getChildren().add(deleteButton);
          }
        }
      }
    }
  }


  /**
   * @param row the row of the essential part
   * @param day the day the essential part is
   * @param parts the parts of the vBox
   */
  @FXML
  private void removeEssentialPart(EssentialPartDisplay row, WeekDay day, VBox parts) {
    day.removeEssentialPart(row.getEssentialPart());
    parts.getChildren().remove(row);
    initLabels();
    initTheme();
  }


  /**
   * Initializes the label with the journal name.
   */
  private void initName() {
    this.weekName.setText(this.name);
  }

  /**
   * Initializes the dropdown component by adding it to the UI.
   */
  private void initDropdown() {
    ComboBox<Theme> themeComboBox = this.themeBar.getThemeComboBox();
    themeComboBox.setOnAction(e -> themeBar.changeTheme(themeComboBox.getValue(), background,
        labels, weekDays, quotesAndNotes, this));
    topRow.getChildren().add(1, this.themeBar.getThemeComboBox());


    categoryFilter = new CategoryFilter();
    // Create ComboBox for category selection
    ComboBox<Category> categoryComboBox = new ComboBox<>();
    categoryComboBox.setItems(FXCollections.observableArrayList(Category.values()));
    categoryComboBox.setPromptText("Select a category");
    categoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
      categoryFilter.setSelectedCategory(newValue);
      // Call method to update UI with filtered events and tasks
      updateUi();
      addDeleteButton();
    });

    topRow.getChildren().add(1, categoryComboBox);
  }

  /**
   * updates the UI data
   */
  private void updateUi() {
    Category selectedCategory = categoryFilter.getSelectedCategory();
    // Call methods on the CategoryFilter to filter events and tasks
    for (Node day : weekView.getChildren()) {
      WeekDay weekDay = (WeekDay) day;
      List<EssentialPart> filteredEp = categoryFilter.filterPartsByCategory(
          weekDay.getEssentialParts(), selectedCategory);
      weekDay.setPartsToDisplay(filteredEp);
    }
    initLabels();
    initTheme();
  }

  /**
   * Initializes the buttons and sets their actions.
   */
  private void initTopRow() {
    saveButton.setOnAction(e -> {
      try {
        saveFile();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    addButton.setOnAction(e -> addEssentialPart());
    changeWeekStartButton.setOnAction(e -> changeWeekStart());

    TextField maxEvents = new TextField();
    maxEvents.setText(String.valueOf(this.maxEventPerDay));
    maxEvents.setOnAction(e -> updateMaxEvent(maxEvents.getText()));
    maxEvents.setMaxWidth(30);
    HBox eventField = new HBox();
    Label meLabel = new Label("Max Events: ");
    eventField.getChildren().add(meLabel);
    labels.add(meLabel);
    eventField.getChildren().add(maxEvents);
    topRow.getChildren().add(eventField);

    TextField maxTasks = new TextField();
    maxTasks.setText(String.valueOf(this.maxTaskPerDay));
    maxTasks.setOnAction(e -> updateMaxTask(maxTasks.getText()));
    maxTasks.setMaxWidth(30);
    HBox taskField = new HBox();
    Label mtLabel = new Label("Max Tasks: ");
    taskField.getChildren().add(mtLabel);
    labels.add(mtLabel);
    taskField.getChildren().add(maxTasks);
    topRow.getChildren().add(taskField);
  }

  private void changeWeekStart() {
    if (this.weekModel instanceof DefaultWeekModel) {
      this.weekModel = new SundayStartWeekModel();
    } else if (this.weekModel instanceof SundayStartWeekModel) {
      this.weekModel = new DefaultWeekModel();
    }
    initWeekView();
  }

  private void updateMaxEvent(String text) {
    try {
      this.maxEventPerDay = Integer.parseInt(text);
      for (WeekDay day : weekDays) {
        day.updateMaxEvent(text);
      }
    } catch (NumberFormatException e) {
      this.maxEventPerDay = -1;
    }
  }

  private void updateMaxTask(String text) {
    try {
      this.maxTaskPerDay = Integer.parseInt(text);
      for (WeekDay day : weekDays) {
        day.updateMaxTask(text);
      }
    } catch (NumberFormatException e) {
      this.maxTaskPerDay = -1;
    }
  }

  /**
   * adds the essential part
   */
  private void addEssentialPart() {
    UserInputPopUp uipu = new UserInputPopUp();
    uipu.start(new Stage(), this);
  }

  /**
   * Saves the journal to a file.
   *
   * @throws IOException if an I/O error occurs during the writing process
   */
  @FXML
  private void saveFile() throws IOException {
    this.writer.writeToFile(this.writer.convertToFile(this), Path.of(filePath));
  }

  /**
   * Retrieves the list of UI elements representing the week days.
   *
   * @return the list of UI elements representing the week days
   */
  public List<Node> getDays() {
    return this.weekView.getChildren();
  }

  /**
   * Retrieves the label displaying the week name.
   *
   * @return the label displaying the week name
   */
  public TextField getWeekName() {
    return this.weekName;
  }

  /**
   * Retrieves the dropdown component for selecting themes.
   *
   * @return the dropdown component for selecting themes
   */
  public ThemeDropdown getThemeDropdown() {
    return this.themeBar;
  }

  /**
   * Returns the TitledPane containing quotes and notes.
   *
   * @return The TitledPane containing quotes and notes.
   */
  public TitledPane getQuotesAndNotes() {
    return this.quotesAndNotes;
  }

  /**
   * Returns the list of labels.
   *
   * @return The list of labels.
   */
  public ArrayList<Label> getLabels() {
    return this.labels;
  }

  /**
   * Returns the maximum number of events per day.
   *
   * @return The maximum number of events per day.
   */
  public int getMaxEventPerDay() {
    return this.maxEventPerDay;
  }

  /**
   * Returns the maximum number of tasks per day.
   *
   * @return The maximum number of tasks per day.
   */
  public int getMaxTaskPerDay() {
    return this.maxTaskPerDay;
  }

  /**
   * Returns the list of quotes and notes.
   *
   * @return The list of quotes and notes.
   */
  public ArrayList<Entry> getQuotesAndNotesList() {
    return this.quotesAndNotesList;
  }

  /**
   * Retrieves the WeekModel associated with the JournalController.
   *
   * @return the WeekModel object
   */
  public WeekModel getWeekModel() {
    return this.weekModel;
  }

}