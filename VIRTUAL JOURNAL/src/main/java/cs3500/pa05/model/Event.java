package cs3500.pa05.model;

/**
 * represents an event
 */
public class Event extends EssentialPart {

  private final int startTime;

  private final int duration;

  /**
   * Constructs a new Event object with the specified day, title, description, category, start time,
   * and duration.
   *
   * @param day         the day of the week
   * @param title       the title of the event
   * @param description the description of the event
   * @param category    the category of the event
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   */
  public Event(DayOfTheWeek day, String title, String description, Category category, int startTime,
               int duration) {
    super(day, title, description, category);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Returns a formatted string representation of the Event object.
   *
   * @return the formatted string
   */
  @Override
  public String format() {
    return super.format() + " [Start time: "
        + ((startTime - startTime % 100) / 100) + ":" + (startTime % 100)
        + ", Duration: " + duration + " mins]";
  }

  /**
   * Retrieves the start time of an event.
   *
   * @return the start time of the event
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Retrieves the duration of an event.
   *
   * @return the duration of the event
   */
  public int getDuration() {
    return this.duration;
  }
}