package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WeekModelTest {

  @Test
  void getStartOfWeek() {
    assertEquals(new DefaultWeekModel().getStartOfWeek(), DayOfTheWeek.MONDAY);
    assertEquals(new SundayStartWeekModel().getStartOfWeek(), DayOfTheWeek.SUNDAY);
  }

  @Test
  void createWeekModel() {
    assertTrue(WeekModel.createWeekModel(DayOfTheWeek.MONDAY) instanceof DefaultWeekModel);
    assertTrue(WeekModel.createWeekModel(DayOfTheWeek.SUNDAY) instanceof SundayStartWeekModel);
  }
}