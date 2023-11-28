package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerImplTest {

  ViewImpl view;

  Model model;

  ArrayList<Question> questions;

  ControllerImpl controller;
  Readable actualInput;

  StringBuilder actualOutput;
  @BeforeEach
  public void beforeEach() {
    actualOutput = new StringBuilder();
    actualInput = new StringReader("src/test/Folder1/questions2.sr\n"+ "1\n"+ "1\n" +"2\n" + "3\n"); // INPUT WHAT USER WOULD ENTER
    view = new ViewImpl(actualOutput);


    controller = new ControllerImpl(view, actualInput);


  }

  @Test
  public void testrun() throws IOException {
    controller.run();
    assertEquals("Welcome to the Study Session \n" +
            "Enter the path to your file\n" + "How many questions would you like to answer\n" +
        " How old are you\n" + "Enter 0 if it was hard, Enter 1 if it was easy, and Enter 2 to show Answer\n"+
            " nineteen\n" + "Enter 3 to move on to the next question\n"+  "You answered 1 questions\n"+
        "0 questions went from easy to hard\n"+ "1 questions went from hard to easy\n"+
        "There are 1 hard questions\n"+ "There are 1 easy questions\n"
        ,actualOutput.toString());
//    assertEquals("Welcome to the Study Session\n Enter the path to your file \n",actualOutput.toString());
//  assertEquals("",actualInput.toString());
  }
// can the .sr file passed be any format we want
  // can I delete the classes that were necessary for pa01 but not pao2
  // controller.run error
//  StringBuilder actualOutput = new StringBuilder();
//
//  ControllerImpl c = new ControllerImpl();

  // test if it creates view correctly and model correctly

  // check that it doesn't build if given incorrect things

}