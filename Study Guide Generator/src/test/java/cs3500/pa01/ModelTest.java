package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelTest {
  Model m;

  Model m1;
  ArrayList<Question> questions;
  ArrayList<Question> questions1;


  Question q1;
  Question q2;
  Question q3;
  Question q4;


  @BeforeEach
  public void beforeEach() {
    q1 = new Question("How are you", "good", Question.Diff.EASY);
    q2 = new Question("What is the capital of New Jersey", "Trenton", Question.Diff.HARD);
    q3 = new Question("What it your last name", "Akinpelu", Question.Diff.HARD);

    questions = new ArrayList<Question>();
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);
    m = new Model(questions);


    q4 = new Question("What it your last name", "Akinpelu", Question.Diff.HARD);

    questions1 = new ArrayList<Question>();
    questions1.add(q4);
    m1 = new Model(questions1);
  }

  /**
   * tests the functions of the model class
   */
  @Test
  public void testAll(){
    assertEquals("You answered " + 0 + " questions\n" +
        0 + " questions went from easy to hard\n" +
        0 + " questions went from hard to easy\n" +
        "There are " + 0 + " hard questions\n" +
        "There are " + 0 + " easy questions\n",m.allStats());

    m.answered();
    m.answered();

    m.EtoH(q1);
    m.HtoE(q3);
    m.HtoE(q2);
    m.TotalHorE();


    assertEquals("You answered " + 2 + " questions\n" +
        1 + " questions went from easy to hard\n" +
        2 + " questions went from hard to easy\n" +
        "There are " + 1 + " hard questions\n" +
        "There are " + 2 + " easy questions\n",m.allStats());

    assertEquals(q1,m.getNextQ());
    m.EtoH(q2);
    assertEquals(q2,m.getNextQ());

    assertEquals(q3, m.getNextQ());

    assertEquals(q4,m1.getNextQ());

    assertEquals(q4, m1.getNextQ());

//    assertEquals(q2,m.getNextQ());
    // assertEquals(q2,m.getNextQ());

  }

  @Test
  public void QuestionToStringMethod(){
assertEquals("Question: How are you\n" +"Answer: good\n"+ "Easy\n"+
    "Question: What is the capital of New Jersey\n"+ "Answer: Trenton\n"+ "Hard\n"+
        "Question: What it your last name\n"+ "Answer: Akinpelu\n" + "Hard\n"
    ,m.QuestionsToString());

  }

  @Test
  public void saveFileMethod() throws Exception{
    File fs = new File("src/test/Folder1/questions.sr");
    Scanner scan = new Scanner(fs);
    m.saveFile("src/test/Folder1/questions.sr");
    assertEquals("Question: How are you",scan.nextLine());

  }
}