package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.DisplayOnlyPopUp;
import cs3500.pa05.model.Quote;
import cs3500.pa05.model.WarningType;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * creates Grid Pane for Quotes
 */
public class QuotePane extends GridPane implements CreationPane {

  private final TextArea quoteTextField;
  private final TextField authorTextField;
  private final ArrayList<Label> labels;

  /**
   * Constructs a QuotePane object.
   * Initializes the quote and author text fields and sets their prompt texts and preferred widths.
   * Sets the labels field with the provided list of labels.
   * Initializes the QuotePane layout.
   *
   * @param labels List of labels to be shown.
   */
  public QuotePane(ArrayList<Label> labels) {
    this.quoteTextField = new TextArea();
    this.quoteTextField.setPromptText("Enter quote");
    this.quoteTextField.setPrefWidth(300);

    this.authorTextField = new TextField();
    this.authorTextField.setPromptText("Enter author");
    this.labels = labels;

    initQuotePane();
  }

  /**
   * Initializes the QuotePane layout by setting padding, gaps, and adding labels and input fields.
   */
  private void initQuotePane() {
    this.setPadding(new Insets(10));
    this.setVgap(10);
    this.setHgap(10);
    this.addRow(0, new Label("Quote:"), quoteTextField);
    this.addRow(1, new Label("Author:"), authorTextField);
  }

  /**
   * Checks if the Quote part can be created.
   *
   * @return true if the part can be created, false otherwise.
   */
  @Override
  public boolean canCreatePart() {
    if (quoteTextField.getText().isEmpty()) {
      diagnoseError("Must enter a quote");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Displays an error message for the Quote creation.
   *
   * @param error The error message.
   */
  @Override
  public void diagnoseError(String error) {
    new DisplayOnlyPopUp(error, "Creation Error", WarningType.BLANKFIELD).displayPopup();
  }

  /**
   * Creates a Quote part based on the input provided in the pane and adds it to the journal
   * controller.
   *
   * @param controller The journal controller.
   */
  @Override
  public void createPart(JournalController controller) {
    AnchorPane anchorPane = (AnchorPane) controller.getQuotesAndNotes().getContent();
    VBox quoteNoteDisplay = (VBox) anchorPane.getChildren().get(0);
    Quote quote = new Quote(quoteTextField.getText(), authorTextField.getText());
    Label quoteLabel = new Label(quote.format());
    quoteLabel.setWrapText(true);
    labels.add(quoteLabel);
    quoteNoteDisplay.getChildren().add(quoteLabel);
    controller.getQuotesAndNotesList().add(quote);
  }
}

