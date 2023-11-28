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
public class CustomThemeApplier implements ThemeApplier {

  private final Color bgColor;
  private final Color textColor;
  private final Font textFont;

  /**
   * Constructs a CustomThemeApplier with the specified background color, text color, and text font.
   *
   * @param bgColor    the background color to be applied
   * @param textColor  the text color to be applied
   * @param textFont   the text font to be applied
   */
  public CustomThemeApplier(Color bgColor, Color textColor, Font textFont) {
    this.bgColor = bgColor;
    this.textColor = textColor;
    this.textFont = textFont;
  }

  /**
   * Applies the custom theme changes.
   *
   * @param background    the AnchorPane representing the background
   * @param labels        the list of Labels to apply the theme changes
   * @param weekDays      the list of WeekDay objects to apply the theme changes
   * @param quotesAndNotes the TitledPane representing the quotes and notes section
   */
  @Override
  public void applyTheme(AnchorPane background, ArrayList<Label> labels,
                         ArrayList<WeekDay> weekDays, TitledPane quotesAndNotes) {
    background.setBackground(Background.fill(bgColor));
    for (Label label : labels) {
      label.setFont(textFont);
      label.setTextFill(textColor);
    }
    for (WeekDay day : weekDays) {
      day.getDayContents().setBackground(Background.fill(bgColor));
    }
    AnchorPane quotesAndNotesContent = (AnchorPane) quotesAndNotes.getContent();
    quotesAndNotesContent.setBackground(Background.fill(bgColor));
  }

  /**
   * Converts the background color to a string representation.
   *
   * @return the string representation of the background color
   */
  public String bgColorToString() {
    return convertToHex(bgColor.getRed()) + convertToHex(bgColor.getGreen())
        + convertToHex(bgColor.getBlue());
  }

  /**
   * Converts the text color to a string representation.
   *
   * @return the string representation of the text color
   */
  public String textColorToString() {
    return convertToHex(textColor.getRed()) + convertToHex(textColor.getGreen())
        + convertToHex(textColor.getBlue());
  }

  /**
   * Converts a decimal value to a hexadecimal string.
   *
   * @param val the decimal value to be converted
   * @return the hexadecimal representation of the value
   */
  String convertToHex(double val) {
    String hex = Integer.toHexString((int) (val * 255));
    if (hex.length() == 1) {
      hex = "0" + hex;
    }
    return hex;
  }

  /**
   * Converts the font to a string representation.
   *
   * @return the string representation of the font
   */
  public String fontToString() {
    return textFont.getName();
  }
}