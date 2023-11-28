//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import javafx.scene.control.Label;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Background;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class BlueThemeApplierTest {
//  private BlueThemeApplier blueThemeApplier;
//
//  @BeforeEach
//  public void setUp() {
//    blueThemeApplier = new BlueThemeApplier();
//  }
//
//  @Test
//  public void testApplyTheme() {
//    ArrayList<Label> labels = new ArrayList<>();
//    Label label1 = new Label();
//    Label label2 = new Label();
//    labels.add(label1);
//    labels.add(label2);
//    ArrayList<WeekDay> weekDays = new ArrayList<>();
//    WeekDay weekDay1 = new WeekDay(DayOfTheWeek.MONDAY, new ArrayList<>(),
//        5, 3);
//    WeekDay weekDay2 = new WeekDay(DayOfTheWeek.TUESDAY, new ArrayList<>(),
//        5, 3);
//    weekDays.add(weekDay1);
//    weekDays.add(weekDay2);
//    TitledPane quotesAndNotes = new TitledPane();
//    AnchorPane background = new AnchorPane();
//    quotesAndNotes.setContent(new AnchorPane());
//    blueThemeApplier.applyTheme(background, labels, weekDays, quotesAndNotes);
//
//    Background expectedBackground = Background.fill(Color.NAVY);
//    Background actualBackground = background.getBackground();
//    assertEquals(expectedBackground, actualBackground);
//
//    Font expectedFont = Font.font("Comic Sans MS");
//    Font actualFont1 = label1.getFont();
//    Font actualFont2 = label2.getFont();
//    assertEquals(expectedFont, actualFont1);
//    assertEquals(expectedFont, actualFont2);
//
//    Color expectedFontColor = Color.GOLD;
//    Color actualFontColor1 = (Color) label1.getTextFill();
//    Color actualFontColor2 = (Color) label2.getTextFill();
//    assertEquals(expectedFontColor, actualFontColor1);
//    assertEquals(expectedFontColor, actualFontColor2);
//
//    Background expectedDayContentsBackground = Background.fill(Color.NAVY);
//    Background actualDayContentsBackground1 = weekDay1.getDayContents().getBackground();
//    Background actualDayContentsBackground2 = weekDay2.getDayContents().getBackground();
//    assertEquals(expectedDayContentsBackground, actualDayContentsBackground1);
//    assertEquals(expectedDayContentsBackground, actualDayContentsBackground2);
//
//    AnchorPane expectedQuestionAndNoteContentsBackground = new AnchorPane();
//    expectedQuestionAndNoteContentsBackground.setBackground(Background.fill(Color.NAVY));
//    AnchorPane actualQuestionAndNoteContentsBackground = (AnchorPane) quotesAndNotes.getContent();
//    assertEquals(expectedQuestionAndNoteContentsBackground.getBackground(),
//        actualQuestionAndNoteContentsBackground.getBackground());
//  }
//
//}
//
///*
//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//
//@ExtendWith(ApplicationExtension.class)
//class BlueThemeApplierTest {
//  private BlueThemeApplier blueThemeApplier;
//  private Background expectedBackground;
//  private Background actualBackground;
//
//
//  @Start
//  public void start(Stage stage) {
//    blueThemeApplier = new BlueThemeApplier();
//    ArrayList<Label> labels = new ArrayList<>();
//    Label label1 = new Label();
//    Label label2 = new Label();
//    labels.add(label1);
//    labels.add(label2);
//    ArrayList<WeekDay> weekDays = new ArrayList<>();
//    WeekDay weekDay1 = new WeekDay(DayOfTheWeek.MONDAY, new ArrayList<>(),
//        5, 3);
//    WeekDay weekDay2 = new WeekDay(DayOfTheWeek.TUESDAY, new ArrayList<>(),
//        5, 3);
//    weekDays.add(weekDay1);
//    weekDays.add(weekDay2);
//    TitledPane quotesAndNotes = new TitledPane();
//    AnchorPane background = new AnchorPane();
//    quotesAndNotes.setContent(new AnchorPane());
//    blueThemeApplier.applyTheme(background, labels, weekDays, quotesAndNotes);
//
//    expectedBackground = Background.fill(Color.NAVY);
//    actualBackground = background.getBackground();
//    stage.setScene(new Scene(new StackPane(background), 100, 100));
//    stage.show();
//  }
//
//  @Test
//  public void testApplyTheme() {
//
//    Assertions.assertEquals(expectedBackground, actualBackground);
//
////    Font expectedFont = Font.font("Comic Sans MS");
////    Font actualFont1 = label1.getFont();
////    Font actualFont2 = label2.getFont();
////    assertEquals(expectedFont, actualFont1);
////    assertEquals(expectedFont, actualFont2);
////
////    Color expectedFontColor = Color.GOLD;
////    Color actualFontColor1 = (Color) label1.getTextFill();
////    Color actualFontColor2 = (Color) label2.getTextFill();
////    assertEquals(expectedFontColor, actualFontColor1);
////    assertEquals(expectedFontColor, actualFontColor2);
////
////    Background expectedDayContentsBackground = Background.fill(Color.NAVY);
////    Background actualDayContentsBackground1 = weekDay1.getDayContents().getBackground();
////    Background actualDayContentsBackground2 = weekDay2.getDayContents().getBackground();
////    assertEquals(expectedDayContentsBackground, actualDayContentsBackground1);
////    assertEquals(expectedDayContentsBackground, actualDayContentsBackground2);
////
////    AnchorPane expectedQuestionAndNoteContentsBackground = new AnchorPane();
////    expectedQuestionAndNoteContentsBackground.setBackground(Background.fill(Color.NAVY));
////    AnchorPane actualQuestionAndNoteContentsBackground = (AnchorPane) quotesAndNotes.getContent();
////    assertEquals(expectedQuestionAndNoteContentsBackground.getBackground(),
////        actualQuestionAndNoteContentsBackground.getBackground());
//  }
//
//}
// */