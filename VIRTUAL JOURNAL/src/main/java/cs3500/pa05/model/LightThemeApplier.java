package cs3500.pa05.model;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The LightThemeApplier class implements the ThemeApplier interface and represents
 * a theme applier for the light theme.
 */
public class LightThemeApplier implements ThemeApplier {

  /**
   * Applies the light theme changes.
   * This method prints the changes applied for the light theme to the console.
   * It sets the background color to white, font color to black, and font family to Arial.
   */
  @Override
  public void applyTheme(AnchorPane background, ArrayList<Label> labels, ArrayList<WeekDay>
      weekDays, TitledPane quotesAndNotes) {
    background.setBackground(Background.fill(Color.WHITE));
    for (Label label : labels) {
      label.setFont(Font.font("Times"));
      label.setTextFill(Color.BLACK);
    }
    for (WeekDay day : weekDays) {
      day.getDayContents().setBackground(Background.fill(Color.WHITE));
    }
    AnchorPane quotesAndNotContents = (AnchorPane) quotesAndNotes.getContent();
    quotesAndNotContents.setBackground(Background.fill(Color.WHITE));
  }
}
