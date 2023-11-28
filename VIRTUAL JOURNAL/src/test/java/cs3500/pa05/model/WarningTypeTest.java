package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarningTypeTest  {
  private WarningType warningType;

  @BeforeEach
  public void setUp() {
    warningType = WarningType.EVENTOVERLOAD;
  }

  @Test
  public void getDescription_ReturnsCorrectDescription() {
    String expectedDescription = "There are too many events.";
    String actualDescription = warningType.getDescription();
    assertEquals(expectedDescription, actualDescription);
  }

  @Test
  public void getTitle_ReturnsCorrectTitle() {
    String expectedTitle = "Event Overload";
    String actualTitle = warningType.getTitle();
    assertEquals(expectedTitle, actualTitle);
  }

}