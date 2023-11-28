package cs3500.pa05.model;

/**
 * Concrete implementation of the WeekModel class with Sunday as the start of the week.
 */
public class SundayStartWeekModel extends WeekModel {
  /**
   * Constructs a SundayStartWeekModel instance with Sunday as the start of the week.
   */
  public SundayStartWeekModel() {
    startOfWeek = DayOfTheWeek.SUNDAY;
  }

  /**
   * Retrieves the starting day of the week.
   *
   * @return the starting day of the week
   */
  @Override
  public DayOfTheWeek getStartOfWeek() {
    return startOfWeek;
  }
}
