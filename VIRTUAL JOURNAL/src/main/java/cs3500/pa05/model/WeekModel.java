package cs3500.pa05.model;


/**
 * Abstract class representing a week model. Allows users to set and retrieve the starting day of
 * the week.
 */
public abstract class WeekModel {
  protected DayOfTheWeek startOfWeek;

  /**
   * Retrieves the starting day of the week.
   *
   * @return the starting day of the week
   */
  public abstract DayOfTheWeek getStartOfWeek();

  /**
   * Creates a WeekModel instance based on the specified starting day of the week.
   *
   * @param startOfWeek the starting day of the week
   * @return a WeekModel instance corresponding to the specified starting day of the week
   */
  public static WeekModel createWeekModel(DayOfTheWeek startOfWeek) {
    if (startOfWeek == DayOfTheWeek.SUNDAY) {
      return new SundayStartWeekModel();
    } else {
      return new DefaultWeekModel();
    }
  }
}