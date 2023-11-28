package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ViewImpl tests
 */
class ViewImplTest {
  StringBuilder actualOutput;
  ViewImpl view;

  Question q1;
@BeforeEach
  public void beforeEach() {
   actualOutput = new StringBuilder();
  // use stringbuilder for testing, use system.out for the actual program
  view = new ViewImpl(actualOutput);

  q1 = new Question("How was your morning", "It was good", Question.Diff.HARD);
}

  /**
   * tests the write function
   */
  @Test
  public void testWrite() {
    try {
      view.write("Test");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("Test", actualOutput.toString());
  }

  /**
   * @throws IOException throws exception if Welcome cannot be shown
   */
  @Test
  public void testWelcome() throws IOException {

    view.Welcome();

    assertEquals("Welcome to the Study Session \n" + "Enter the path to your file\n"
        , actualOutput.toString());
  }

  /**
   * @throws IOException throws exception if the question cannot be shown
   */
  @Test
  public void testShowQ() throws IOException {


  view.showQuestion(q1);

    assertEquals("How was your morning" +
        "\nEnter 0 if it was hard, Enter 1 if it was easy, and Enter 2 to show Answer\n"
        , actualOutput.toString());
  }

  /**
   * @throws IOException throws exception if answer cannot be shown
   */
  @Test
  public void testShowAns() throws IOException {


    view.showAns(q1);

    assertEquals("It was good" + "\nEnter 3 to move on to the next question\n"
        , actualOutput.toString());
  }

}