package cs3500.pa05.model;

/**
 * Concrete implementation of the WeekModel class with Monday as the start of the week.
 */
public class DefaultWeekModel extends WeekModel {
  /**
   * Constructs a DefaultWeekModel instance with Monday as the start of the week.
   */
  public DefaultWeekModel() {
    startOfWeek = DayOfTheWeek.MONDAY;
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
