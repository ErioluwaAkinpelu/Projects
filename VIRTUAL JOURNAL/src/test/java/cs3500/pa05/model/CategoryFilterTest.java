package cs3500.pa05.model;

import static cs3500.pa05.model.Category.NONE;
import static cs3500.pa05.model.Category.SOCIAL;
import static cs3500.pa05.model.Category.WORK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryFilterTest {

  private CategoryFilter cf;

  @BeforeEach
  void setup() {
    cf = new CategoryFilter();
    cf.setSelectedCategory(NONE);
  }

  @Test
  void getSelectedCategory() {
    assertEquals(cf.getSelectedCategory(), NONE);
  }

  @Test
  void setSelectedCategory() {
    cf.setSelectedCategory(Category.WORK);
    assertEquals(cf.getSelectedCategory(), Category.WORK);
    cf.setSelectedCategory(SOCIAL);
    assertEquals(cf.getSelectedCategory(), SOCIAL);
    cf.setSelectedCategory(Category.SCHOOL);
    assertEquals(cf.getSelectedCategory(), Category.SCHOOL);
  }

  @Test
  void filterPartsByCategory() {
    ArrayList<EssentialPart> essentialParts = new ArrayList<>();
    essentialParts.add(new Task(DayOfTheWeek.MONDAY, "Task 1", "Social task",
        SOCIAL, false));
    essentialParts.add(new Task(DayOfTheWeek.MONDAY, "Task 2", "Work task",
        WORK, true));
    essentialParts.add(new Event(DayOfTheWeek.MONDAY, "Event 1", "Social event",
        SOCIAL, 1230, 12));
    essentialParts.add(new Event(DayOfTheWeek.MONDAY, "Event 2", "No category event",
        NONE, 453, 15));
    List<EssentialPart> socialParts = cf.filterPartsByCategory(essentialParts, SOCIAL);
    assertEquals(socialParts.size(), 2);
    assertEquals(socialParts.get(0).getCategory(), SOCIAL);
    assertEquals(socialParts.get(1).getCategory(), SOCIAL);

    List<EssentialPart> allParts = cf.filterPartsByCategory(essentialParts, NONE);
    assertEquals(allParts.size(), 4);
  }
}