//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.ArrayList;
//import javafx.scene.control.Label;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//
//
//class ThemeDropdownTest {
//  private ThemeDropdown themeDropdown;
//  private ThemeDropdown themeDropdownDark;
//  private ThemeDropdown themeDropdownBlue;
//  private AnchorPane background;
//  private Label label;
//
//  @BeforeEach
//  void setUp() {
//
//
//    // Create the objects for testing
//    themeDropdown = new ThemeDropdown(Theme.LIGHT.toString(), "BLACK", "BLACK", "Comic Sans MS");
//    themeDropdownDark = new ThemeDropdown(Theme.DARK.toString(), "BLACK", "BLACK", "Comic Sans MS");
//    themeDropdownBlue = new ThemeDropdown(Theme.BLUE.toString(), "BLACK", "BLACK", "Comic Sans MS");
//    background = new AnchorPane();
//    label = new Label("Test Label");
//  }
//
//  @Test
//  void testChangeTheme() {
//    // Change the theme to DARK
//    // update to the actual one
//    TitledPane quotesAndNotes = new TitledPane();
//    quotesAndNotes.setContent(new AnchorPane());
//    themeDropdown.changeTheme(Theme.DARK, background, new ArrayList<>(), new ArrayList<>(),
//        quotesAndNotes, null);
//
//    // Verify that the theme has been applied
//    assertEquals(background.getBackground().getFills().get(0).getFill().toString(),
//        "0x000000ff");
//
//    // update to the actual one
//    // Change the theme to BLUE
//    themeDropdown.changeTheme(Theme.BLUE, background, new ArrayList<>(), new ArrayList<>(),
//        quotesAndNotes, null);
//
//    // Verify that the theme has been applied
//    assertEquals(background.getBackground().getFills().get(0).getFill().toString(), "0x000080ff");
//
//    // update to the actual one
//    // Change the theme to LIGHT
//    themeDropdown.changeTheme(Theme.LIGHT, background, new ArrayList<>(), new ArrayList<>(),
//        quotesAndNotes, null);
//
//    // Verify that the theme has been applied
//    assertEquals(background.getBackground().getFills().get(0).getFill().toString(), "0xffffffff");
//  }
//
//  @Test
//  void testChangeCustomTheme() {
//    // Change the theme to CUSTOM with custom colors and font
//    // update to the actual one
//    TitledPane quotesAndNotes = new TitledPane();
//    quotesAndNotes.setContent(new AnchorPane());
//    ArrayList<Label> labels = new ArrayList<>();
//    labels.add(label);
//    themeDropdown.changeCustomTheme(Color.RED, Color.WHITE, Font.font("Arial"), background,
//        labels, new ArrayList<>(), quotesAndNotes);
//
//
//    // Verify that the custom theme has been applied
//    assertEquals(background.getBackground().getFills().get(0).getFill().toString(),
//        "0xff0000ff");
//
//    // Verify that the label's text color and font have been changed
//    assertEquals(label.getTextFill(), Color.WHITE);
//    assertEquals(label.getFont().getFamily(), "Arial");
//  }
//
//  @Test
//  void testGetComboBox() {
//    Theme theme = themeDropdown.getThemeComboBox().getSelectionModel().getSelectedItem();
//    assertEquals(theme, Theme.LIGHT);
//    theme = themeDropdownDark.getThemeComboBox().getSelectionModel().getSelectedItem();
//    assertEquals(theme, Theme.DARK);
//    theme = themeDropdownBlue.getThemeComboBox().getSelectionModel().getSelectedItem();
//    assertEquals(theme, Theme.BLUE);
//  }
//
//  @Test
//  void testGetThemeApplier() {
//    assertTrue(themeDropdown.getThemeApplier() instanceof LightThemeApplier);
//    assertTrue(themeDropdownDark.getThemeApplier() instanceof DarkThemeApplier);
//    assertTrue(themeDropdownBlue.getThemeApplier() instanceof BlueThemeApplier);
//  }
//}