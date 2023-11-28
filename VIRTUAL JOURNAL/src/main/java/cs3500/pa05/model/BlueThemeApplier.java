package cs3500.pa05.model;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * BlueThemeApplier is an implementation of the ThemeApplier interface that applies the blue
 * theme changes.
 * It sets the background color to Light Blue, the font color to Navy, and the font family to
 * Calibri.
 */
public class BlueThemeApplier implements ThemeApplier {

  /**
   * Applies the blue theme changes.
   * It sets the background color to Light Blue, the font color to Navy, and the font family to
   * Calibri.
   */
  @Override
  public void applyTheme(AnchorPane background, ArrayList<Label> labels, ArrayList<WeekDay>
      weekDays, TitledPane quotesAndNotes) {
    background.setBackground(Background.fill(Color.NAVY));
    for (Label label : labels) {
      label.setFont(Font.font("Comic Sans MS"));
      label.setTextFill(Color.GOLD);
    }
    for (WeekDay day : weekDays) {
      day.getDayContents().setBackground(Background.fill(Color.NAVY));
    }
    AnchorPane quotesAndNoteContents = (AnchorPane) quotesAndNotes.getContent();
    quotesAndNoteContents.setBackground(Background.fill(Color.NAVY));
  }
}
