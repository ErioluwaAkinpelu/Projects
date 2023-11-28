//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//
//class DisplayOnlyPopUpTest {
//  private DisplayOnlyPopUp popUp;
//
//  @BeforeEach
//  public void setUp() {
//    String content = "This is the content";
//    String title = "Popup Title";
//    WarningType type = WarningType.EVENTOVERLOAD;
//    popUp = new DisplayOnlyPopUp(content, title, type);
//  }
//
//  @Test
//  public void displayPopup_PrintsCorrectInformation() {
//
//    // Redirect standard output to capture console output
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    PrintStream originalOut = System.out;
//    System.setOut(new PrintStream(outputStream));
//
//    popUp.testPopUp();
//
//    // Reset standard output
//    System.setOut(originalOut);
//
//    String expectedOutput = "Popup Title: This is the content" + System.lineSeparator();
//
//    assertEquals(expectedOutput, outputStream.toString());
//  }
//
//}