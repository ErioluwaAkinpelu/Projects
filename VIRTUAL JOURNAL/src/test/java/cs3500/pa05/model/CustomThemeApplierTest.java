//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
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
//class CustomThemeApplierTest {
//
//  private CustomThemeApplier themeApplier;
//  private Color textColor;
//  private Font textFont;
//
//  @BeforeEach
//  public void setUp() {
//    Color bgColor = Color.WHITE;
//    textColor = Color.BLACK;
//    textFont = Font.font("Arial", 12);
//    themeApplier = new CustomThemeApplier(bgColor, textColor, textFont);
//  }
//
//  @Test
//  public void testApplyTheme() {
//    ArrayList<Label> labels = new ArrayList<>();
//    labels.add(new Label("Label"));
//    ArrayList<WeekDay> weekDays = new ArrayList<>();
//    weekDays.add(new WeekDay(DayOfTheWeek.MONDAY, new ArrayList<>(), 3, 3));
//    TitledPane quotesAndNotes = new TitledPane();
//    quotesAndNotes.setContent(new AnchorPane());
//    AnchorPane background = new AnchorPane();
//    themeApplier.applyTheme(background, labels, weekDays, quotesAndNotes);
//
//    assertNotNull(background.getBackground());
//    for (Label label : labels) {
//      assertEquals(textFont, label.getFont());
//      assertEquals(textColor, label.getTextFill());
//    }
//    for (WeekDay day : weekDays) {
//      assertNotNull(day.getDayContents().getBackground());
//    }
//    AnchorPane questionAndNotesContents = (AnchorPane) quotesAndNotes.getContent();
//    assertNotNull(questionAndNotesContents.getBackground());
//  }
//
//  @Test
//  public void testBgColorToString() {
//    String expected = "ffffff";
//    String actual = themeApplier.bgColorToString();
//    assertEquals(expected, actual);
//  }
//
//  @Test
//  public void testTextColorToString() {
//    String expected = "000000";
//    String actual = themeApplier.textColorToString();
//    assertEquals(expected, actual);
//  }
//
//  @Test
//  public void testConvertToHex() {
//    double val = 0.5;
//    String expected = "7f";
//    String actual = themeApplier.convertToHex(val);
//    assertEquals(expected, actual);
//  }
//
//  @Test
//  public void testFontToString() {
//    String expected = "Arial";
//    String actual = themeApplier.fontToString();
//    assertEquals(expected, actual);
//  }
//
//}