package cs3500.pa05.model;

/**
 * The {@code WarningType} enum represents different types of warnings that can occur.
 * Each warning type has a title and description associated with it.
 */
public enum WarningType {
  /**
   * Indicates an event overload warning.
   * There are too many events.
   */
  EVENTOVERLOAD("Event Overload", "There are too many events."),

  /**
   * Indicates a character overload warning.
   * There are too many characters.
   */
  CHARACTEROVERLOAD("Character Overload", "There are too many characters."),

  /**
   * Indicates a blank field warning.
   * A required field is blank.
   */
  BLANKFIELD("Blank Field", "A required field is blank.");

  private final String title;
  private final String description;

  /**
   * Constructs a warning type with the specified title and description.
   *
   * @param title       the title of the warning type
   * @param description the description of the warning type
   */
  WarningType(String title, String description) {
    this.title = title;
    this.description = description;
  }

  /**
   * Returns the description associated with the warning type.
   *
   * @return the description of the warning type
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the title associated with the warning type.
   *
   * @return the title of the warning type
   */
  public String getTitle() {
    return title;
  }
}