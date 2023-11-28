package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * represents a day with all tasks and events
 */
public class WeekDay extends TitledPane {
  private final DayOfTheWeek dayOfWeek;
  private final ArrayList<EssentialPart> essentialParts;
  int maxEventPerDay;
  int maxTaskPerDay;
  int numOfTasks;
  int numOfEvents;
  private VBox dayContents;
  private ArrayList<Label> labels;
  private boolean test;

  /**
   * Constructs a new WeekDay object with the specified day of the week, essential parts,
   * maximum events per day, and maximum tasks per day.
   *
   * @param dayOfWeek        the day of the week
   * @param essentialParts   the list of essential parts
   * @param maxEventPerDay   the maximum number of events allowed per day
   * @param maxTaskPerDay    the maximum number of tasks allowed per day
   */
  public WeekDay(DayOfTheWeek dayOfWeek, ArrayList<EssentialPart> essentialParts,
                 int maxEventPerDay, int maxTaskPerDay) {
    this.dayOfWeek = dayOfWeek;
    this.essentialParts = essentialParts;
    this.maxEventPerDay = maxEventPerDay;
    this.maxTaskPerDay = maxTaskPerDay;
    int numOfTasks = 0;
    int numOfEvents = 0;
    for (EssentialPart part : essentialParts) {
      if (part instanceof Task)  {
        numOfTasks++;
      } else {
        numOfEvents++;
      }
    }
    this.numOfEvents = numOfEvents;
    this.numOfTasks = numOfTasks;
    this.dayContents = new VBox();
    this.labels = new ArrayList<>();
    initPartsDisplayed();
  }

  /**
   * Initializes the display of the essential parts by setting the text and creating labels for
   * each part.
   */
  public void initPartsDisplayed() {
    this.setText(dayOfWeek.name());
    this.setExpanded(true);
    this.setPrefWidth(200);
    this.labels = new ArrayList<>();
    setPartsToDisplay(essentialParts);
  }

  /**
   * Returns the day of the week.
   *
   * @return the day of the week
   */
  public DayOfTheWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Adds an essential part to the WeekDay. Updates the count of tasks or events accordingly.
   *
   * @param part the essential part to add
   */
  public void addEssentialPart(EssentialPart part) {
    if (part instanceof Task) {
      numOfTasks++;
      essentialParts.add(part);
      if (this.tooManyTasks()) {
        DisplayOnlyPopUp dopu = new DisplayOnlyPopUp("Warning: You added more than your "
            + "maximum number of tasks",
            "Too Many Tasks", WarningType.EVENTOVERLOAD);
        if (test) {
          dopu.testPopUp();
        } else {
          dopu.displayPopup();
        }
      }
    } else {
      numOfEvents++;
      essentialParts.add(part);
      if (this.tooManyEvents()) {
        DisplayOnlyPopUp dopu = new DisplayOnlyPopUp("Warning: You added more than your "
            + "maximum number of events",
            "Too Many Tasks", WarningType.EVENTOVERLOAD);
        if (test) {
          dopu.testPopUp();
        } else {
          dopu.displayPopup();
        }
      }
    }
  }

  /**
   * Checks if the number of events exceeds the maximum allowed events per day.
   *
   * @return true if there are too many events, false otherwise
   */
  public boolean tooManyEvents() {
    return numOfEvents > maxEventPerDay && maxEventPerDay >= 0;
  }

  /**
   * Checks if the number of tasks exceeds the maximum allowed tasks per day.
   *
   * @return true if there are too many tasks, false otherwise
   */
  public boolean tooManyTasks() {
    return numOfTasks > maxTaskPerDay && maxTaskPerDay >= 0;
  }

  /**
   * Removes an essential part from the WeekDay.
   *
   * @param part the essential part to remove
   */
  public void removeEssentialPart(EssentialPart part) {
    essentialParts.remove(part);
    if (part instanceof Task) {
      numOfTasks--;
    } else if (part instanceof Event) {
      numOfEvents--;
    }
  }

  /**
   * Returns the list of essential parts.
   *
   * @return the list of essential parts
   */
  public ArrayList<EssentialPart> getEssentialParts() {
    return this.essentialParts;
  }

  /**
   * Sets the list of essential parts to be displayed.
   *
   * @param parts the list of essential parts
   */
  public void setPartsToDisplay(List<EssentialPart> parts) {

    dayContents = new VBox();

    for (EssentialPart part : parts) {
      Label label = new Label();
      label.setText(part.format());
      label.setWrapText(true);
      labels.add(label);
      EssentialPartDisplay row = new EssentialPartDisplay(part);
      row.getChildren().add(label);
      if (part instanceof Task) {
        CheckBox completed = new CheckBox();
        completed.setSelected(((Task) part).getCompletion());
        completed.setOnAction(e -> ((Task) part).changeCompletion());
        row.getChildren().add(completed);
      }
      dayContents.getChildren().add(row);
    }
    this.setContent(dayContents);
  }

  /**
   * Updates the maximum number of events per day based on the provided text.
   *
   * @param text the text representing the maximum number of events per day
   */
  public void updateMaxEvent(String text) {
    try {
      this.maxEventPerDay = Integer.parseInt(text);
    } catch (NumberFormatException e) {
      this.maxEventPerDay = -1;
    }
  }

  /**
   * Updates the maximum number of tasks per day based on the provided text.
   *
   * @param text the text representing the maximum number of tasks per day
   */
  public void updateMaxTask(String text) {
    try {
      this.maxTaskPerDay = Integer.parseInt(text);
    } catch (NumberFormatException e) {
      this.maxTaskPerDay = -1;
    }
  }

  /**
   * Returns the list of labels.
   *
   * @return the list of labels
   */
  public ArrayList<Label> getLabels() {
    return this.labels;
  }

  /**
   * Returns the VBox associated with the day's contents.
   *
   * @return the VBox associated with the day's contents
   */
  public VBox getDayContents() {
    return this.dayContents;
  }


  /**
   * Sets the WeekDay to print a message rather than display an Alert when an error is encountered
   * for testing purposes
   *
   * @param test - the boolean stating whether the WeekDay is for a test or not
   */
  public void setTest(boolean test) {
    this.test = test;
  }

}
