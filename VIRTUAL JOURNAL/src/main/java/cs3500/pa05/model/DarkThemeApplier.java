package cs3500.pa05.model;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The DarkThemeApplier class implements the ThemeApplier interface and represents
 * a theme applier for the dark theme.
 */
public class DarkThemeApplier implements ThemeApplier {

  /**
   * Applies the dark theme changes.
   * This method prints the changes applied for the dark theme to the console.
   * It sets the background color to black, font color to white, and font family to Verdana.
   */
  @Override
  public void applyTheme(AnchorPane background, ArrayList<Label> labels, ArrayList<WeekDay>
      weekDays, TitledPane quotesAndNotes) {
    background.setBackground(Background.fill(Color.BLACK));
    for (Label label : labels) {
      label.setFont(Font.font("Arial"));
      label.setTextFill(Color.WHITE);
    }
    for (WeekDay day : weekDays) {
      day.getDayContents().setBackground(Background.fill(Color.BLACK));
    }
    AnchorPane quotesAndNotesContent = (AnchorPane) quotesAndNotes.getContent();
    quotesAndNotesContent.setBackground(Background.fill(Color.BLACK));
  }
}
