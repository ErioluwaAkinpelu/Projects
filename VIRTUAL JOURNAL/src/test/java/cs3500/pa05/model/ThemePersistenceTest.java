//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class ThemePersistenceTest {
//  private ThemePersistence themePersistence;
//
//  @BeforeEach
//  void setUp() {
//    themePersistence = new ThemePersistence();
//  }
//
//  @Test
//  void testLoadTheme() throws IOException {
//    // Test when theme file does not exist
//    Theme defaultTheme = themePersistence.loadTheme();
//    assertEquals(Theme.LIGHT, defaultTheme);
//
//    // Test when theme file exists
//    Theme expectedTheme = Theme.DARK;
//    createThemeFile(expectedTheme.toString());
//    Theme loadedTheme = themePersistence.loadTheme();
//    assertEquals(expectedTheme, loadedTheme);
//  }
//
//  @Test
//  void testSaveTheme() throws IOException {
//    // Test saving a theme
//    Theme themeToSave = Theme.BLUE;
//    themePersistence.saveTheme(themeToSave);
//
//    // Verify that the theme file has been created
//    File themeFile = new File(ThemePersistence.THEME_FILE_PATH);
//    assertTrue(themeFile.exists());
//
//    // Verify the contents of the saved theme file
//    String savedTheme = readThemeFromFile(themeFile);
//    assertEquals(themeToSave.toString(), savedTheme);
//  }
//
//  private void createThemeFile(String theme) throws IOException {
//    try (FileWriter fileWriter = new FileWriter(ThemePersistence.THEME_FILE_PATH)) {
//      fileWriter.write(theme);
//    }
//  }
//
//  private String readThemeFromFile(File file) throws IOException {
//    StringBuilder themeBuilder = new StringBuilder();
//    try (FileReader fileReader = new FileReader(file)) {
//      int ch;
//      while ((ch = fileReader.read()) != -1) {
//        themeBuilder.append((char) ch);
//      }
//    }
//    return themeBuilder.toString().trim();
//  }
//
//
//}