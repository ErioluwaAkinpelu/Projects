//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.ArrayList;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//
//class WeekDayTest {
//
//  private WeekDay weekDay;
//
//  @BeforeEach
//  public void setUp() {
//    DayOfTheWeek dayOfWeek = DayOfTheWeek.MONDAY;
//    ArrayList<EssentialPart> essentialParts = new ArrayList<>();
//    int maxEventPerDay = 5;
//    int maxTaskPerDay = 3;
//    weekDay = new WeekDay(dayOfWeek, essentialParts, maxEventPerDay, maxTaskPerDay);
//    weekDay.setTest(true);
//  }
//
//  @Test
//  public void testGetDayOfWeek() {
//    DayOfTheWeek expectedDayOfWeek = DayOfTheWeek.MONDAY;
//    DayOfTheWeek actualDayOfWeek = weekDay.getDayOfWeek();
//    assertEquals(expectedDayOfWeek, actualDayOfWeek);
//  }
//
//  @Test
//  public void testAddEssentialPart() {
//    Task task = new Task(DayOfTheWeek.MONDAY, "Task 1", "Task 1 description",
//        Category.SOCIAL, false);
//    weekDay.addEssentialPart(task);
//    ArrayList<EssentialPart> expectedEssentialParts = new ArrayList<>();
//    expectedEssentialParts.add(task);
//    ArrayList<EssentialPart> actualEssentialParts = weekDay.getEssentialParts();
//    assertEquals(expectedEssentialParts, actualEssentialParts);
//  }
//
//  @Test
//  public void testRemoveEssentialPart() {
//    Task task1 = new Task(DayOfTheWeek.MONDAY, "Task", "Task description",
//        Category.WORK, false);
//    Event event1 = new Event(DayOfTheWeek.MONDAY, "Event", "Event description",
//        Category.SOCIAL, 1234, 12);
//    weekDay.addEssentialPart(task1);
//    weekDay.addEssentialPart(event1);
//    weekDay.removeEssentialPart(task1);
//    ArrayList<EssentialPart> expectedEssentialParts = new ArrayList<>();
//    expectedEssentialParts.add(event1);
//    ArrayList<EssentialPart> actualEssentialParts = weekDay.getEssentialParts();
//    assertEquals(expectedEssentialParts, actualEssentialParts);
//    weekDay.removeEssentialPart(event1);
//    actualEssentialParts = weekDay.getEssentialParts();
//    assertEquals(new ArrayList<>(), actualEssentialParts);
//  }
//
//  @Test
//  public void testSetPartsToDisplay() {
//    Task task1 = new Task(DayOfTheWeek.MONDAY, "Task 1", "Task 1 description",
//        Category.NONE, false);
//    Task task2 = new Task(DayOfTheWeek.MONDAY, "Task 2", "Task 2 description",
//        Category.NONE, true);
//    ArrayList<EssentialPart> essentialParts = new ArrayList<>();
//    essentialParts.add(task1);
//    essentialParts.add(task2);
//    weekDay.setPartsToDisplay(essentialParts);
//    ArrayList<Label> expectedLabels = new ArrayList<>();
//    Label label1 = new Label(task1.format());
//    expectedLabels.add(label1);
//    Label label2 = new Label(task2.format());
//    expectedLabels.add(label2);
//    ArrayList<Label> actualLabels = weekDay.getLabels();
//    assertEquals(expectedLabels.get(0).getText(), actualLabels.get(0).getText());
//    assertEquals(expectedLabels.get(1).getText(), actualLabels.get(1).getText());
//  }
//
//  @Test
//  public void testTooManyEvents() {
//    weekDay.updateMaxEvent("2");
//    Event event1 = new Event(DayOfTheWeek.MONDAY, "Event 1", "Event 1 description",
//        Category.SOCIAL, 1234, 12);
//    Event event2 = new Event(DayOfTheWeek.MONDAY, "Event 2", "Event 2 description",
//        Category.WORK, 1234, 12);
//    weekDay.addEssentialPart(event1);
//    weekDay.addEssentialPart(event2);
//    boolean actualResult = weekDay.tooManyEvents();
//    assertFalse(actualResult);
//
//    Event event3 = new Event(DayOfTheWeek.MONDAY, "Event 3", "Event 3 description",
//        Category.NONE, 1234, 12);
//    weekDay.addEssentialPart(event3);
//    actualResult = weekDay.tooManyEvents();
//    assertTrue(actualResult);
//  }
//
//  @Test
//  public void testTooManyTasks() {
//    weekDay.updateMaxTask("1");
//    Task task1 = new Task(DayOfTheWeek.MONDAY, "Task 1", "Task 1 description",
//        Category.NONE, false);
//    Task task2 = new Task(DayOfTheWeek.MONDAY, "Task 2", "Task 2 description",
//        Category.SCHOOL, false);
//    weekDay.addEssentialPart(task1);
//    weekDay.addEssentialPart(task2);
//    boolean actualResult = weekDay.tooManyTasks();
//    assertTrue(actualResult);
//  }
//
//  @Test
//  public void testUpdateMaxEvent() {
//    weekDay.updateMaxEvent("3");
//    int expectedMaxEventPerDay = 3;
//    int actualMaxEventPerDay = weekDay.maxEventPerDay;
//    assertEquals(expectedMaxEventPerDay, actualMaxEventPerDay);
//  }
//
//  @Test
//  public void testUpdateMaxTask() {
//    weekDay.updateMaxTask("2");
//    int expectedMaxTaskPerDay = 2;
//    int actualMaxTaskPerDay = weekDay.maxTaskPerDay;
//    assertEquals(expectedMaxTaskPerDay, actualMaxTaskPerDay);
//  }
//
//  @Test
//  public void testGetDayContents() {
//    VBox expectedDayContents = new VBox();
//    VBox actualDayContents = weekDay.getDayContents();
//    assertEquals(expectedDayContents.getChildren(), actualDayContents.getChildren());
//  }
//}