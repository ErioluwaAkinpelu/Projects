package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Popup;
import cs3500.pa05.model.ThemeDropdown;
import cs3500.pa05.model.WeekDay;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A custom implementation of the Popup interface for displaying a custom color pop-up.
 * This class provides functionality to select a custom color and apply it.
 */
public class CustomThemePopUp implements Popup {

  private final Slider redBgSlider;
  private final Slider greenBgSlider;
  private final Slider blueBgSlider;
  private final Slider redTextSlider;
  private final Slider greenTextSlider;
  private final Slider blueTextSlider;
  private final ComboBox<String> fontSelector;
  private VBox root;
  private ThemeDropdown dropdown;
  private Scene scene;
  private Stage stage;

  /**
   * Constructs a CustomColorPopUp object.
   *
   * @param background    The AnchorPane representing the background.
   * @param labels        The list of labels to be shown.
   * @param weekDays      The weekdays to be shown.
   * @param quotesAndNotes The TitledPane representing quotes and notes.
   */
  public CustomThemePopUp(AnchorPane background, ArrayList<Label> labels,
                          ArrayList<WeekDay> weekDays, TitledPane quotesAndNotes) {
    redBgSlider = new Slider();
    redBgSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    greenBgSlider = new Slider();
    greenBgSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    blueBgSlider = new Slider();
    blueBgSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    redTextSlider = new Slider();
    redTextSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    greenTextSlider = new Slider();
    greenTextSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    blueTextSlider = new Slider();
    blueTextSlider.valueProperty().addListener(
        e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(), getTextFont(),
            background, labels, weekDays, quotesAndNotes));

    fontSelector = new ComboBox<>(FXCollections.observableArrayList(Font.getFamilies()));
    fontSelector.setPromptText("Choose a font: ");
    fontSelector.getSelectionModel().select("Arial");
    fontSelector.setOnAction(e -> dropdown.changeCustomTheme(getBgColor(), getTextColor(),
        getTextFont(), background, labels, weekDays, quotesAndNotes));

  }

  /**
   * Starts the custom color pop-up.
   *
   * @param primaryStage The primary stage of the application.
   * @param controller   The journal controller.
   * @param dropdown     The dropdown menu to be shown.
   */
  public void start(Stage primaryStage, JournalController controller, ThemeDropdown dropdown) {
    root = new VBox();
    stage = primaryStage;
    this.dropdown = dropdown;
    createUi(controller);

    // Create the buttons layout
    HBox buttonsLayout = new HBox(10);

    // Create the root layout
    root.setPadding(new Insets(10));
    root.setSpacing(10);
    root.getChildren().addAll(buttonsLayout);

    // Create the scene and stage
    scene = new Scene(root, 400, 330);
    displayPopup();
  }

  /**
   * Creates the user interface for the custom color pop-up.
   *
   * @param controller The journal controller.
   */
  private void createUi(JournalController controller) {

    // Create the Event tab
    GridPane bgColorPane = createColorPane(redBgSlider, greenBgSlider, blueBgSlider);
    Tab bgTab = new Tab("Background", bgColorPane);
    bgTab.setClosable(false);

    GridPane textColorPane = createColorPane(redTextSlider, greenTextSlider, blueTextSlider);
    textColorPane.addRow(3, new Label("Font: "), fontSelector);
    Tab textTab = new Tab("Text", textColorPane);
    textTab.setClosable(false);

    TabPane tabPane = new TabPane();
    tabPane.getTabs().addAll(bgTab, textTab);


    root.getChildren().add(tabPane);

  }

  /**
   * Creates the GridPane containing the color selection pane.
   *
   * @param redSlider   The red slider.
   * @param greenSlider The green slider.
   * @param blueSlider  The blue slider.
   * @return The created GridPane.
   */
  private GridPane createColorPane(Slider redSlider, Slider greenSlider, Slider blueSlider) {
    GridPane colorPane = new GridPane();
    colorPane.setPadding(new Insets(10));
    colorPane.setVgap(10);
    colorPane.setHgap(10);
    redSlider.setMin(0);
    redSlider.setMax(255);
    redSlider.setBlockIncrement(1);
    greenSlider.setMin(0);
    greenSlider.setMax(255);
    greenSlider.setBlockIncrement(1);
    blueSlider.setMin(0);
    blueSlider.setMax(255);
    blueSlider.setBlockIncrement(1);
    colorPane.addRow(0, new Label("Red: "), redSlider);
    colorPane.addRow(1, new Label("Green: "), greenSlider);
    colorPane.addRow(2, new Label("Blue: "), blueSlider);

    return colorPane;
  }

  /**
   * Displays the custom color pop-up.
   */
  @Override
  public void displayPopup() {
    stage.setScene(scene);
    stage.setTitle("Custom Color Popup");
    stage.show();
  }



  /**
   * Retrieves the background color selected by the user.
   *
   * @return The background color.
   */
  private Color getBgColor() {
    return Color.color(redBgSlider.getValue() / 255,
        greenBgSlider.getValue() / 255, blueBgSlider.getValue() / 255);
  }

  /**
   * Retrieves the text color selected by the user.
   *
   * @return The text color.
   */
  private Color getTextColor() {
    return Color.color(redTextSlider.getValue() / 255,
        greenTextSlider.getValue() / 255, blueTextSlider.getValue() / 255);
  }

  /**
   * Retrieves the text font selected by the user.
   *
   * @return The text font.
   */
  private Font getTextFont() {
    return Font.font(this.fontSelector.getValue());
  }
}