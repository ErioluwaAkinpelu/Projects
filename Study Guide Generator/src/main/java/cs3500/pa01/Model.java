package cs3500.pa01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


/**
 * Model class that keeps data about the game
 */
public class Model {

  private ArrayList<Question> allProblems;

  private ArrayList<Question> hardQ;
  private ArrayList<Question> easyQ;


  private int questionsAns;

  private int EasytoHard;

  private int HardtoEasy;

  private int numofHard;

  private int numofEasy;

  private ArrayList<Question> shownQuestions;

  /**
   * @param allProblems total questions that user can be shown
   */
  public Model(ArrayList<Question> allProblems) {
    this.allProblems = allProblems ;
    hardQ = new ArrayList<Question>();
    easyQ = new ArrayList<Question>();
    shownQuestions = new ArrayList<Question>();

  }


  /**
   * updates the total number answered questions
   */
  public void answered() {
    questionsAns += 1;
  }

  /**
   * @param question takes in the question that needs to be changed to Hard
   */
  public void EtoH(Question question) {
    if (question.getDiff().equals(Question.Diff.EASY)) {
      question.makeHard();
      EasytoHard += 1;
      easyQ.remove(question);
      hardQ.add(question);

    }
  }

  /**
   * @param question takes in the questions that needs to be changed to Easy
   */
  public void HtoE(Question question) {
    if (question.getDiff().equals(Question.Diff.HARD)) {
      question.makeEasy();
      HardtoEasy += 1;
      hardQ.remove(question);
      easyQ.add(question);

    }
  }

  /**
   * determines the number of problems that are hard or Easy
   */
  public void TotalHorE() {
    easyQ.clear();
    hardQ.clear();
    numofEasy = 0;
    numofHard = 0;
    for (Question p : allProblems) {
      if (p.getDiff().equals(Question.Diff.EASY)) {
        easyQ.add(p);
        numofEasy += 1;
      } else {
        hardQ.add(p);
        numofHard += 1;
      }

    }
  }

  /**
   * @return returns the all the stats at the end of game
   */
  public String allStats() {
    return "You answered " + questionsAns + " questions\n" +
        EasytoHard + " questions went from easy to hard\n" +
        HardtoEasy + " questions went from hard to easy\n" +
        "There are " + numofHard + " hard questions\n" +
        "There are " + numofEasy + " easy questions\n";
  }

  /**
   * @return returns the next random question
   */
  public Question getNextQ() {
    Random rand = new Random();

    if (!hardQ.isEmpty()) {
      int intRandom = rand.nextInt(hardQ.size());
      Question currentQ = hardQ.get(intRandom);
      hardQ.remove(currentQ);
      shownQuestions.add(currentQ);
      return currentQ;
    }
    else if (hardQ.isEmpty() && !easyQ.isEmpty()) {
      int intRandom = rand.nextInt(easyQ.size());
      Question currentQ = easyQ.get(intRandom);
      easyQ.remove(currentQ);
      shownQuestions.add(currentQ);
      return currentQ;
    }
    else  {
      int intRandom = rand.nextInt(allProblems.size());
      Question currentQ = allProblems.get(intRandom);
      return currentQ;
    }

  }

  public void saveFile(String fileName) throws IOException {
  try{
    File myFoo = new File(fileName);

    FileWriter writer = new FileWriter(myFoo, false);

  writer.write(QuestionsToString());
  writer.flush();
  writer.close();}
  catch(IOException exc) {
    exc.printStackTrace();
    }
  }

  public String QuestionsToString(){
    String totalFile = "";
    for(Question q : allProblems ){
      String fullQ = "";
      if(q.getDiff().equals(Question.Diff.EASY) ) {
      String diff = "Easy";
        fullQ += "Question: " + q.getAsk() + "\n" + "Answer: " + q.getAns() + "\n" + diff + "\n" ;
      }
      else {
        String diff = "Hard";
        fullQ += "Question: " + q.getAsk() + "\n" + "Answer: " + q.getAns() + "\n" + diff + "\n" ;
      }

      totalFile += fullQ;

    }
    return totalFile ;
  }

}