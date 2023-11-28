package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests questions class methods
 */
class QuestionTest {

  Question q1;
  Question q2;
  @BeforeEach

  public void beforeEach() {
    q1 = new Question("What is your name", "eri", Question.Diff.EASY);
    q2 = new Question("What is your name", "eri", Question.Diff.HARD);



  }

  /**
   * tests the getter functions in question class
   */
  @Test
  public void testQuestion() {
   assertEquals( "eri",q1.getAns());
   assertEquals("What is your name", q1.getAsk());
   assertEquals( Question.Diff.EASY,q1.getDiff());
   assertEquals( Question.Diff.HARD,q2.getDiff());

   q1.makeHard();
   assertEquals( Question.Diff.HARD,q1.getDiff());
   q1.makeHard();
   assertEquals( Question.Diff.HARD,q1.getDiff());
   q2.makeEasy();
   assertEquals( Question.Diff.EASY,q2.getDiff());
   q2.makeEasy();
   assertEquals( Question.Diff.EASY,q2.getDiff());
  }
}