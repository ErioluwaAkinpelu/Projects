package cs3500.pa01;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;


/**
 * controller class
 */
public class ControllerImpl  {


  private ViewImpl view;

  private Model model;

  private Readable input;
//  private final Readable input;
//  private final Appendable output;

  /**
   * @param view the class responsible for displaying the program
   * @param input the class responsible for reading User input
   */
  public ControllerImpl(ViewImpl view, Readable input) {
//    this.allProblems = Objects.requireNonNull(input);

    this.view = view;
    this.input = input;
  }


  /**
   * @throws IOException throws exception if view or input cannot be instantiated
   */
  public void run() throws IOException {

    view.Welcome();
    Scanner scan = new Scanner(input);

    String firstUserInput = scan.nextLine();
    File srFile = new File(firstUserInput);
    Processor p = new Processor();
    model = new Model(p.filterQ(srFile.toPath()));
    model.TotalHorE();

    view.write("How many questions would you like to answer\n");

      int totalQuestions = scan.nextInt();

      for(int i = 0; i < totalQuestions; i++) {
        Question currentQ = model.getNextQ();
        view.showQuestion(currentQ);
        while(!scan.hasNext()) {
          view.showQuestion(currentQ);
        }
        boolean firstR = true;
        boolean secondR = true;
        while(firstR || secondR) {
          int currentLine = scan.nextInt();
          if (currentLine == 0) {
            model.EtoH(currentQ);
            firstR = false;
          }
          else if (currentLine == 1) {
            model.HtoE(currentQ);
            firstR = false;
          }
          else if (currentLine == 2) {
            view.showAns(currentQ);
            model.answered();
            secondR = false;
          } else {
            System.out.println("That is not a valid Input");

          }

        }

        boolean thirdR = true;
        while(thirdR) {
          int currentLine = scan.nextInt();
          if (currentLine == 3) {
            thirdR = false;
          }

        }
      }

      view.write(model.allStats());
      model.saveFile(srFile.getName());




  }
}
