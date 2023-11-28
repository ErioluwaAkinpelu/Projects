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
//class LightThemeApplierTest {
//  private LightThemeApplier themeApplier;
//
//  @BeforeEach
//  public void setUp() {
//    // Create the theme applier
//    themeApplier = new LightThemeApplier();
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
//      assertEquals(Font.font("Times"), label.getFont());
//      assertEquals(Color.BLACK, label.getTextFill());
//    }
//    for (WeekDay day : weekDays) {
//      assertNotNull(day.getDayContents().getBackground());
//    }
//    AnchorPane quotesAndNotesContents = (AnchorPane) quotesAndNotes.getContent();
//    assertNotNull(quotesAndNotesContents.getBackground());
//  }
//}