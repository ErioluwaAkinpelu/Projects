package cs3500.pa05.model;

import javafx.scene.control.ComboBox;

/**
 * The ThemeSelector interface represents a component that provides theme selection functionality.
 */
public interface ThemeSelector {

  /**
   * Retrieves the ComboBox control for selecting themes.
   *
   * @return the ComboBox control for selecting themes
   */
  ComboBox<Theme> getThemeComboBox();
}

