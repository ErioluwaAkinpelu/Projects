package cs3500.pa05.model;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The BujoFileReader class represents a file reader for reading data from a Bujo file.
 */
public class BujoFileReader {

  private final String inputPath;
  private final String outputPath;

  ArrayList<WeekDay> weekDays = new ArrayList<>();
  ArrayList<Entry> quotesAndNotes = new ArrayList<>();
  String title;
  String theme;
  String maxEvents;
  String maxTasks;
  ThemeDropdown themeBar;
  WeekModel weekModel;
  String nextLine;
  Scanner input;

  /**
   * Constructs a BujoFileReader object with the provided file path.
   *
   * @param inputPath  the path of the bullet journal file to be read
   * @param outputPath the path of the bullet journal file to be written
   */
  public BujoFileReader(String inputPath, String outputPath) {
    this.inputPath = inputPath;
    this.outputPath = outputPath;
  }

  /**
   * Reads from a file and creates a JournalController based on the file's contents.
   *
   * @return the JournalController instance created from the file's contents
   * @throws IllegalArgumentException if the file contents are invalid
   */
  public JournalController convertFileToBujo() {
    input = null;
    try {
      input = new Scanner(Path.of(inputPath + ".bujo"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    while (input.hasNextLine()) {
      processLines();
    }
    return new JournalController(title, themeBar, weekDays, outputPath,
        Integer.parseInt(maxEvents), Integer.parseInt(maxTasks), quotesAndNotes, weekModel);
  }

  private void processLines() {
    nextLine = input.nextLine().trim();
    if (nextLine.startsWith("\"title\"")) {
      title = getInput(nextLine);
    } else if (nextLine.startsWith("\"theme\"")) {
      theme = getInput(nextLine);
    } else if (nextLine.startsWith("\"custom-theme-param\"")) {
      themeBar = buildThemeDropdown();
    } else if (nextLine.startsWith("\"max-event\"")) {
      maxEvents = getInput(nextLine);
    } else if (nextLine.startsWith("\"max-task\"")) {
      maxTasks = getInput(nextLine);
    } else if (nextLine.startsWith("\"start-of-week\"")) {
      String weekStart = getInput(nextLine);
      weekModel = WeekModel.createWeekModel(DayOfTheWeek.valueOf(weekStart));
    } else if (nextLine.startsWith("\"days\"")) {
      boolean finishedWeekView = false;
      while (!finishedWeekView) {
        finishedWeekView = buildDays();
      }
    } else if (nextLine.startsWith("\"quotes-and-notes\"")) {
      boolean finishedQn = false;
      while (!finishedQn) {
        finishedQn = buildQuotesAndNotes();
      }
    }
  }

  private ThemeDropdown buildThemeDropdown() {
    nextLine = input.nextLine().trim();
    String bgColor = getInput(nextLine);
    nextLine = input.nextLine().trim();
    String textColor = getInput(nextLine);
    nextLine = input.nextLine().trim();
    String textFont = getInput(nextLine);
    return new ThemeDropdown(theme, bgColor, textColor, textFont);
  }


  private boolean buildDays() {
    nextLine = input.nextLine().trim();
    if (nextLine.equals("],")) {
      return true;
    } else if (nextLine.startsWith("\"day\"")) {
      String dayOfWeek = getInput(nextLine);
      boolean finishedEp = false;
      ArrayList<EssentialPart> parts = new ArrayList<>();
      while (!finishedEp) {
        finishedEp = buildEssentialParts(dayOfWeek, parts);
      }
      WeekDay weekDay = verifyWeekDay(dayOfWeek, parts, maxEvents, maxTasks);
      weekDays.add(weekDay);
    }
    return false;
  }

  private boolean buildEssentialParts(String dayOfWeek, ArrayList<EssentialPart> parts) {
    nextLine = input.nextLine().trim();
    if (nextLine.equals("]")) {
      return true;
    } else {
      if (nextLine.equals("{")) {
        nextLine = input.nextLine().trim();
        if (!nextLine.equals("},")) {
          String epTitle = getInput(nextLine);
          nextLine = input.nextLine().trim();
          String epDescription = getInput(nextLine);
          nextLine = input.nextLine().trim();
          String epCategory = getInput(nextLine);
          nextLine = input.nextLine().trim();
          String eventStartTime = "";
          String eventDuration = "";
          String taskCompletion = "";
          if (nextLine.startsWith("\"start-time\"")) {
            eventStartTime = getInput(nextLine);
            nextLine = input.nextLine().trim();
            eventDuration = getInput(nextLine);
          } else {
            taskCompletion = getInput(nextLine);
          }
          if (!taskCompletion.equals("")) {
            Task task =
                verifyTask(dayOfWeek, epTitle, epDescription, epCategory, taskCompletion);
            parts.add(task);
          } else {
            Event event =
                verifyEvent(dayOfWeek, epTitle, epDescription, epCategory, eventStartTime,
                    eventDuration);
            parts.add(event);
          }
        }
      }
    }
    return false;
  }

  private boolean buildQuotesAndNotes() {
    nextLine = input.nextLine().trim();
    if (nextLine.equals("]")) {
      return true;
    } else {
      if (nextLine.equals("{")) {
        String note = "";
        String quote = "";
        String author = "";
        nextLine = input.nextLine().trim();
        if (!nextLine.equals("},")) {
          if (nextLine.startsWith("\"quote\"")) {
            quote = getInput(nextLine);
            nextLine = input.nextLine().trim();
            author = getInput(nextLine);
          } else {
            note = getInput(nextLine);
          }
          if (note.isBlank()) {
            if (quote.isBlank()) {
              throw new IllegalArgumentException("Invalid contents");
            } else {
              quotesAndNotes.add(new Quote(quote, author));
            }
          } else {
            quotesAndNotes.add(new Note(note));
          }
        }
      }
    }
    return false;
  }




  private String getInput(String nextLine) {
    int end = 0;
    if (nextLine.endsWith("\",")) {
      end = 2;
    } else if (nextLine.endsWith(",") || nextLine.endsWith("\"")) {
      end = 1;
    }
    int start = 2;
    if (nextLine.contains(": \"")) {
      start = 3;
    }
    return nextLine.substring(nextLine.indexOf(":") + start, nextLine.length() - end);
  }

  /**
   * Verifies and creates a WeekDay object with the specified parameters.
   *
   * @param dayOfWeek the day of the week as a string
   * @param parts     the list of essential parts for the day
   * @param maxEvents the maximum number of events allowed for the day
   * @param maxTasks  the maximum number of tasks allowed for the day
   * @return a WeekDay object representing the verified and created week day
   * @throws IllegalArgumentException if the day of the week is invalid or if the maximum event or
   *                                  task count is not a valid integer
   */
  private WeekDay verifyWeekDay(String dayOfWeek, ArrayList<EssentialPart> parts, String maxEvents,
                                String maxTasks) {
    DayOfTheWeek dotw = verifyDayOfWeek(dayOfWeek);
    int eventMax;
    try {
      eventMax = Integer.parseInt(maxEvents);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid integer");
    }
    int taskMax;
    try {
      taskMax = Integer.parseInt(maxTasks);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid integer");
    }
    return new WeekDay(dotw, parts, eventMax, taskMax);
  }

  /**
   * Verifies and returns the DayOfTheWeek enum value based on the given day of the week string.
   *
   * @param dayOfWeek the day of the week string to verify
   * @return the corresponding DayOfTheWeek enum value
   * @throws IllegalArgumentException if the day of the week string is invalid
   */
  private DayOfTheWeek verifyDayOfWeek(String dayOfWeek) {
    try {
      return DayOfTheWeek.valueOf(dayOfWeek);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid day of week");
    }
  }

  /**
   * Verifies and returns the Category enum value based on the given category string.
   *
   * @param epCategory the category string to verify
   * @return the corresponding Category enum value
   * @throws IllegalArgumentException if the category string is invalid
   */
  private Category verifyCategory(String epCategory) {
    try {
      return Category.valueOf(epCategory);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid category");
    }
  }

  /**
   * Verifies and returns an Event object based on the given event details.
   *
   * @param dayOfWeek      the day of the week string for the event
   * @param epTitle        the title string of the event
   * @param epDescription  the description string of the event
   * @param epCategory     the category string of the event
   * @param eventStartTime the start time string of the event
   * @param eventDuration  the duration string of the event
   * @return the verified Event object
   * @throws IllegalArgumentException if any of the event details are invalid
   */
  private Event verifyEvent(String dayOfWeek, String epTitle, String epDescription,
                            String epCategory, String eventStartTime, String eventDuration) {
    DayOfTheWeek dotw = verifyDayOfWeek(dayOfWeek);
    Category category = verifyCategory(epCategory);
    int startTime;
    try {
      startTime = Integer.parseInt(eventStartTime);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid start time");
    }
    int duration;
    try {
      duration = Integer.parseInt(eventDuration);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid duration");
    }
    return new Event(dotw, epTitle, epDescription, category, startTime, duration);
  }

  /**
   * Verifies and returns a Task object based on the given task details.
   *
   * @param dayOfWeek      the day of the week string for the task
   * @param epTitle        the title string of the task
   * @param epDescription  the description string of the task
   * @param epCategory     the category string of the task
   * @param taskCompletion the task completion string
   * @return the verified Task object
   * @throws IllegalArgumentException if any of the task details are invalid
   */
  private Task verifyTask(String dayOfWeek, String epTitle, String epDescription, String epCategory,
                          String taskCompletion) {
    DayOfTheWeek dotw = verifyDayOfWeek(dayOfWeek);
    Category category = verifyCategory(epCategory);
    boolean complete;
    try {
      complete = Boolean.parseBoolean(taskCompletion);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid boolean");
    }

    return new Task(dotw, epTitle, epDescription, category, complete);
  }
}