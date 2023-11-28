package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest  {
  private Task task;

  @BeforeEach
  public void setUp() {
    DayOfTheWeek day = DayOfTheWeek.MONDAY;
    String title = "Task Title";
    String description = "Task Description";
    Category category = Category.NONE;
    boolean complete = false;
    task = new Task(day, title, description, category, complete);
  }

  @Test
  public void testChangeCompletion() {
    assertFalse(task.getCompletion());

    task.changeCompletion();
    assertTrue(task.getCompletion());

    task.changeCompletion();
    assertFalse(task.getCompletion());
  }

  @Test
  public void testGetCompletion() {
    assertFalse(task.getCompletion());
  }

  @Test
  public void testGetDay() {
    DayOfTheWeek expectedDay = DayOfTheWeek.MONDAY;
    DayOfTheWeek actualDay = task.getDay();
    assertEquals(expectedDay, actualDay);
  }
}