package cs3500.pa05.model;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.view.CustomThemePopUp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The ThemeDropdown class implements the ThemeSelector interface and provides functionality for
 * selecting and applying themes.
 * It contains a ComboBox for selecting themes and relies on a ThemePersistence object for loading
 * and saving the selected theme.
 * The class also uses ThemeApplier implementations to apply the selected theme's changes to the
 * user interface.
 */
public class ThemeDropdown implements ThemeSelector {

  private ThemeApplier themeApplier;
  private final ComboBox<Theme> themeComboBox;

  /**
   * @param theme type of theme
   * @param bgColor background color
   * @param textColor color of text
   * @param textFont font of text
   */
  public ThemeDropdown(String theme, String bgColor, String textColor, String textFont) {
    themeComboBox = new ComboBox<>(FXCollections.observableArrayList(Theme.values()));
    switch (theme) {
      case "LIGHT" -> {
        themeComboBox.setValue(Theme.LIGHT);
        themeApplier = new LightThemeApplier();
      }
      case "DARK" -> {
        themeComboBox.setValue(Theme.DARK);
        themeApplier = new DarkThemeApplier();
      }
      case "BLUE" -> {
        themeComboBox.setValue(Theme.BLUE);
        themeApplier = new BlueThemeApplier();
      }
      case "CUSTOM" -> {
        themeComboBox.setValue(Theme.CUSTOM);
        themeApplier = new CustomThemeApplier(Color.valueOf(bgColor),
            Color.valueOf(textColor), Font.font(textFont));
      }
      default -> throw new IllegalArgumentException("Invalid theme.");
    }
  }

  /**
   * Retrieves the themeComboBox used for selecting themes.
   *
   * @return the ComboBox object for theme selection
   */
  @Override
  public ComboBox<Theme> getThemeComboBox() {
    return themeComboBox;
  }

  /**
   * Applies the specified theme by creating the corresponding ThemeApplier object and calling its
   * applyTheme() method.
   *
   * @param theme         the theme to be applied
   * @param background    the background AnchorPane to apply the theme to
   * @param labels        the list of labels to apply the theme to
   * @param weekDays      the list of WeekDay objects to apply the theme to
   * @param quotesAndNotes the TitledPane containing quotes and notes to apply the theme to
   * @param controller    the JournalController instance
   */
  public void changeTheme(Theme theme, AnchorPane background, ArrayList<Label> labels,
                          ArrayList<WeekDay> weekDays, TitledPane quotesAndNotes,
                          JournalController controller) {
    switch (theme) {
      case LIGHT -> themeApplier = new LightThemeApplier();
      case DARK -> themeApplier = new DarkThemeApplier();
      case BLUE -> themeApplier = new BlueThemeApplier();
      case CUSTOM -> {
        themeApplier = new DarkThemeApplier();
        CustomThemePopUp ccpu = new CustomThemePopUp(background, labels, weekDays, quotesAndNotes);
        Stage s = new Stage();
        ccpu.start(s, controller, this);
      }
    }

    themeApplier.applyTheme(background, labels, weekDays, quotesAndNotes);
  }

  /**
   * @param bgColor background color
   * @param textColor text color
   * @param textFont font of text
   * @param background background
   * @param labels labels
   * @param weekDays day of weeks
   * @param quotesAndNotes quotes and Notes
   */
  public void changeCustomTheme(Color bgColor, Color textColor, Font textFont,
                                AnchorPane background, ArrayList<Label> labels,
                                ArrayList<WeekDay> weekDays, TitledPane quotesAndNotes) {
    themeApplier = new CustomThemeApplier(bgColor, textColor, textFont);
    themeApplier.applyTheme(background, labels, weekDays, quotesAndNotes);
  }

  /**
   * Retrieves the ThemeApplier associated with this ThemeDropdown.
   *
   * @return the ThemeApplier object
   */
  public ThemeApplier getThemeApplier() {
    return this.themeApplier;
  }
}
