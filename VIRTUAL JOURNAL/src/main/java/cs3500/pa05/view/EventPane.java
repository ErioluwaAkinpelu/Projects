package cs3500.pa05.view;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayOfTheWeek;
import cs3500.pa05.model.EssentialPart;
import cs3500.pa05.model.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Representation of a GridPane for creating Event parts.
 */
public class EventPane extends EssentialPane {

  TextField startTimeTextField;
  TextField durationTextField;

  /**
   * Constructs an EventPane object.
   * Initializes the start time and duration text fields and sets their prompt texts.
   * Initializes the EventPane layout.
   */
  public EventPane() {
    super();

    this.startTimeTextField = new TextField();
    this.startTimeTextField.setPromptText("Enter start time");

    this.durationTextField = new TextField();
    this.durationTextField.setPromptText("Enter duration");

    initEventPane();
  }

  /**
   * Initializes the EventPane layout by setting padding, gaps, and adding labels and input fields.
   */
  private void initEventPane() {
    this.setPadding(new Insets(10));
    this.setVgap(10);
    this.setHgap(10);
    this.addRow(0, new Label("Name:"), nameTextField);
    this.addRow(1, new Label("Day of the Week:"), dayComboBox);
    this.addRow(2, new Label("Start Time:"), startTimeTextField);
    this.addRow(3, new Label("Duration:"), durationTextField);
    this.addRow(4, new Label("Description:"), descriptionTextField);
    this.addRow(5, new Label("Category:"), categoryComboBox);
  }

  /**
   * Checks if the Event part can be created.
   *
   * @return true if the part can be created, false otherwise.
   */
  @Override
  public boolean canCreatePart() {
    if (super.canCreatePart()) {
      int startTime;
      try {
        startTime = Integer.parseInt(startTimeTextField.getText());
      } catch (NumberFormatException e) {
        this.diagnoseError("Start time must be a valid time written as a 3- or 4-digit number. "
            + "Ex: 230 for 2:30 or 1759 for 17:59/5:59 PM");
        return false;
      }
      if (startTime < 0 || startTime % 100 > 59 || startTime > 2359) {
        this.diagnoseError("Start time must be a valid time written as a 3- or 4-digit number. "
            + "Ex: 230 for 2:30 or 1759 for 17:59/5:59 PM");
        return false;
      }

      int durationTime;
      try {
        durationTime = Integer.parseInt(durationTextField.getText());
      } catch (NumberFormatException e) {
        this.diagnoseError("Duration must be a positive integer representing the duration of "
            + "an event in minutes");
        return false;
      }
      if (durationTime < 0) {
        this.diagnoseError("Duration must be a positive integer representing the duration of "
            + "an event in minutes");
        return false;
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Builds an Event object based on the input provided in the pane.
   *
   * @return The built EventPart.
   */
  @Override
  public EssentialPart buildPart() {
    Category category;
    String s = categoryComboBox.getSelectionModel().getSelectedItem();
    try {
      category = Category.valueOf(s.toUpperCase());
    } catch (NullPointerException e) {
      category = Category.NONE;
    }
    return new Event(
        DayOfTheWeek.valueOf(this.dayComboBox.getSelectionModel().getSelectedItem().toUpperCase()),
        nameTextField.getText(),
        descriptionTextField.getText(),
        category,
        Integer.parseInt(startTimeTextField.getText()),
        Integer.parseInt(durationTextField.getText()));
  }
}