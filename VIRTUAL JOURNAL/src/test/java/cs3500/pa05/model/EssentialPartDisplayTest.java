package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EssentialPartDisplayTest {
  private EssentialPartDisplay essentialPartDisplay;
  private EssentialPart essentialPart;

  @BeforeEach
  public void setUp() {
    essentialPart = new Task(DayOfTheWeek.MONDAY, "Task 1", "Description 1",
        Category.NONE, false);
    essentialPartDisplay = new EssentialPartDisplay(essentialPart);
  }

  @Test
  public void testGetEssentialPart() {
    EssentialPart actualEssentialPart = essentialPartDisplay.getEssentialPart();
    assertEquals(essentialPart, actualEssentialPart);
  }


}