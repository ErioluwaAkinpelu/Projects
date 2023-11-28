package cs3500.pa01;

/**
 * question class
 */
public class Question {
  private String ask;
  private String ans;

   private Diff diff;

  Question(String ask, String ans, Diff diff) {
    this.ask = ask;
    this.ans = ans;
    this.diff = diff;

  }

  enum Diff {
    EASY,
    HARD
  }

  /**
   * @return the question asked
   */
  public String getAsk() {
    return this.ask;
  }

  /**
   * @return returns the ans to the question
   */
  public String getAns() {
    return this.ans;
  }

  /**
   * @return returns the difficulty of the question
   */
  public Diff getDiff() {
    return this.diff;
  }

  /**
   * makes a question easy
   */
  public void makeEasy() {
    if(this.diff == Diff.HARD){
    this.diff = Diff.EASY;
    }
  }

  /**
   * makes the question hard
   */
  public void makeHard() {
    if(this.diff == Diff.EASY) {
      this.diff = Diff.HARD;
    }
  }


}
