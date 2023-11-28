package cs3500.pa03;

import java.util.Arrays;

// In test > ... > MockRandom.java

/**
 * Class that mocks the random interface
 */
public class MockRandom implements Randomable {
  private int index = 0;
  private final int[] numbers = {0, 2, 4, 6, 8, 10, 8, 9, 10, 3, 12, 8, 2, 8, 2, 6};
  // you choose these

  public MockRandom() {

  }

  // Implement interface's method in Mock as well
  @Override
  public int nextInt() {
    /* Retrieve */ int next = numbers[index];
    /* Iterate */ this.index = this.index < numbers.length + 1 ? this.index + 1 : 0;
    // `this.index += 1;` if looping not needed
    /* Return */ return next;
  }

  @Override
  public int nextInt(int bound) {
    /* Retrieve */ int next = numbers[index];
    /* Iterate */ this.index = this.index < numbers.length + 1 ? this.index + 1 : 0;
    // `this.index += 1;` if looping not needed
    /* Return */ return next;
  }
}