package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.DayOfTheWeek;
import cs3500.pa05.model.DisplayOnlyPopUp;
import cs3500.pa05.model.EssentialPart;
import cs3500.pa05.model.WarningType;
import cs3500.pa05.model.WeekDay;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Representation of Grid Pane for Essential parts
 */
public abstract class EssentialPane extends GridPane implements CreationPane {

  TextField nameTextField;
  ComboBox<String> dayComboBox;
  ComboBox<String> categoryComboBox;
  TextField descriptionTextField;

  /**
   * Constructs an EssentialPane object.
   * Initializes the text fields, combo boxes, and sets their prompt texts.
   */
  public EssentialPane() {
    this.nameTextField = new TextField();
    this.nameTextField.setPromptText("Enter name");

    this.dayComboBox = new ComboBox<>();
    this.dayComboBox.getItems()
        .addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday");
    this.dayComboBox.setPromptText("Select a day of the week");

    this.descriptionTextField = new TextField();
    this.descriptionTextField.setPromptText("Enter description (optional)");

    this.categoryComboBox = new ComboBox<>();
    this.categoryComboBox.getItems().addAll("Social", "Work", "School", "None");
    this.categoryComboBox.setPromptText("Select a category (optional)");
  }

  /**
   * Checks if the Essential part can be created.
   *
   * @return true if the part can be created, false otherwise.
   */
  public boolean canCreatePart() {
    if (nameTextField.getText().isEmpty()) {
      this.diagnoseError("Name field can't be blank");
      return false;
    } else if (dayComboBox.getSelectionModel().getSelectedItem() == null) {
      this.diagnoseError("Must select a day");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Creates the Essential part using the provided controller.
   *
   * @param controller The journal controller.
   */
  public void createPart(JournalController controller) {
    for (Node day : controller.getDays()) {
      WeekDay weekDay = (WeekDay) day;
      if (weekDay.getDayOfWeek().equals(
          DayOfTheWeek.valueOf(
              this.dayComboBox.getSelectionModel().getSelectedItem().toUpperCase()))) {
        weekDay.addEssentialPart(buildPart());
        weekDay.initPartsDisplayed();
      }
    }
  }

  /**
   * Diagnoses the provided error by displaying a pop-up.
   *
   * @param error The error message.
   */
  public void diagnoseError(String error) {
    new DisplayOnlyPopUp(error, "Creation Error", WarningType.BLANKFIELD).displayPopup();
  }

  /**
   * Builds an EssentialPart object based on the input provided in the pane.
   *
   * @return The built EssentialPart.
   */
  public abstract EssentialPart buildPart();
}