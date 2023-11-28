package cs3500.pa05.model;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * The ThemeApplier interface represents a theme applier.
 * Implementing classes must provide the functionality to apply a theme.
 */
public interface ThemeApplier {

  /**
   * Applies the theme changes.
   * Implementing classes should define the specific changes to be applied for the theme.
   *
   * @param background   the background pane to apply the theme to
   * @param labels       the labels to apply the theme to
   * @param weekDays     the week days to apply the theme to
   * @param quotesAndNotes the quotes and notes section to apply the theme to
   */
  void applyTheme(AnchorPane background, ArrayList<Label> labels, ArrayList<WeekDay> weekDays,
                  TitledPane quotesAndNotes);
}

