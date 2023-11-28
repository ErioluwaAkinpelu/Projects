package cs3500.pa05.view;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayOfTheWeek;
import cs3500.pa05.model.EssentialPart;
import cs3500.pa05.model.Task;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Grid Pane for tasks
 */
public class TaskPane extends EssentialPane {

  private final CheckBox  completeCheckBox;

  /**
   * Constructs a TaskPane object.
   * Initializes the completeCheckBox with the label "Complete".
   * Initializes the TaskPane layout.
   */
  public TaskPane() {
    super();

    this.completeCheckBox = new CheckBox("Complete");

    initTaskPane();
  }

  /**
   * Creates a Task part based on the input provided in the pane.
   *
   * @return The created Task part.
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
    return new Task(
        DayOfTheWeek.valueOf(this.dayComboBox.getSelectionModel().getSelectedItem().toUpperCase()),
        nameTextField.getText(),
        descriptionTextField.getText(),
        category,
        completeCheckBox.isSelected());
  }

  /**
   * Initializes the TaskPane layout by setting padding, gaps, and adding labels, input fields,
   * and checkboxes.
   */
  private void initTaskPane() {
    this.setPadding(new Insets(10));
    this.setVgap(10);
    this.setHgap(10);
    this.addRow(0, new Label("Name:"), nameTextField);
    this.addRow(1, new Label("Day of the Week:"), dayComboBox);
    this.addRow(2, new Label("Description:"), descriptionTextField);
    this.addRow(3, new Label("Category:"), categoryComboBox);
    this.addRow(4, new Label("Completed: "), completeCheckBox);
  }
}