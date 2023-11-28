package cs3500.pa01;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

/**
 * ViewImpl that implements View
 */
public class ViewImpl implements View {
  private Scanner input;
  private Appendable appendable;

  public ViewImpl(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }


  /**
   * @param phrase the phrase that will be displayed to screen
   * @throws IOException throws exception if the phrase is not a string
   */
  @Override
  public void write(String phrase) throws IOException {
    appendable.append(phrase);
  }


  /**
   * @throws IOException throws Exception if the string cannot be appended
   */
  public void Welcome() throws IOException {
    appendable.append("Welcome to the Study Session \n" + "Enter the path to your file\n");
  }

  /**
   * @param question the question that will be shown to the User
   * @throws IOException throws exception if the String or question cannot be shown
   */
  public void showQuestion(Question question) throws IOException {
//    System.out.println(prompt);
    appendable.append(question.getAsk());
    appendable.append("\nEnter 0 if it was hard, Enter 1 if it was easy, and Enter 2 to show Answer\n");
  }

  /**
   * @param question question that we want to display the answer to
   * @throws IOException returns exception if question answer cannot be appended
   */
  public void showAns(Question question) throws IOException {
    appendable.append(question.getAns() + "\nEnter 3 to move on to the next question\n");
  }




}
