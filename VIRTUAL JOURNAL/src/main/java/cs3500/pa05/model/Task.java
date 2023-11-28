package cs3500.pa05.model;

/**
 * represents a task
 */
public class Task extends EssentialPart {
  private boolean complete;

  /**
   * Constructs a new Task object with the specified day of the week, title, description, category,
   * and completion status.
   *
   * @param day          the day of the week
   * @param title        the title of the task
   * @param description  the description of the task
   * @param category     the category of the task
   * @param complete     the completion status of the task
   */
  public Task(DayOfTheWeek day, String title, String description, Category category,
              boolean complete) {
    super(day, title, description, category);
    this.complete = complete;
  }

  /**
   * Changes the completion status of the Task.
   */
  public void changeCompletion() {
    complete = !complete;
  }

  /**
   * Returns the completion status of the Task.
   *
   * @return true if the task is complete, false if it is incomplete
   */
  public boolean getCompletion() {

    return complete;
  }

  /**
   * Returns the day of the week associated with the Task.
   *
   * @return the day of the week
   */
  public DayOfTheWeek getDay() {
    return day;
  }
}