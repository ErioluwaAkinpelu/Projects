package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.DisplayOnlyPopUp;
import cs3500.pa05.model.Note;
import cs3500.pa05.model.WarningType;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * representation of GridPane for Notes
 */
public class NotePane extends GridPane implements CreationPane {

  private final TextArea noteTextField;
  private final ArrayList<Label> labels;

  /**
   * Constructs a NotePane object.
   * Initializes the note text area and sets its prompt text and preferred width.
   * Sets the labels field with the provided list of labels.
   * Initializes the NotePane layout.
   *
   * @param labels List of labels to be shown.
   */
  public NotePane(ArrayList<Label> labels) {
    this.noteTextField = new TextArea();
    this.noteTextField.setPromptText("Enter note");
    this.noteTextField.setPrefWidth(300);
    this.labels = labels;
    initNotePane();
  }

  /**
   * Initializes the NotePane layout by setting padding, gaps, and adding labels and input fields.
   */
  private void initNotePane() {
    this.setPadding(new Insets(10));
    this.setVgap(10);
    this.setHgap(10);
    this.addRow(0, new Label("Note:"), noteTextField);
  }

  /**
   * Checks if the Note part can be created.
   *
   * @return true if the part can be created, false otherwise.
   */
  @Override
  public boolean canCreatePart() {
    if (noteTextField.getText().isEmpty()) {
      diagnoseError("Must enter a note");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Displays an error message for the Note creation.
   *
   * @param error The error message.
   */
  @Override
  public void diagnoseError(String error) {
    new DisplayOnlyPopUp(error, "Creation Error", WarningType.BLANKFIELD).displayPopup();
  }

  /**
   * Creates a Note part based on the input provided in the pane and adds it to the journal
   * controller.
   *
   * @param controller The journal controller.
   */
  @Override
  public void createPart(JournalController controller) {
    AnchorPane anchorPane = (AnchorPane) controller.getQuotesAndNotes().getContent();
    VBox quoteNoteDisplay = (VBox) anchorPane.getChildren().get(0);
    Note note = new Note(noteTextField.getText());
    Label noteLabel = new Label(note.format());
    noteLabel.setWrapText(true);
    labels.add(noteLabel);
    quoteNoteDisplay.getChildren().add(noteLabel);
    controller.getQuotesAndNotesList().add(note);
  }
}
