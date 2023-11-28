package cs3500.pa05.model;

/**
 * represents a task or event
 */
public abstract class EssentialPart {
  DayOfTheWeek day;
  String title;
  String description;
  Category category;

  EssentialPart(DayOfTheWeek day, String title, String description, Category category) {
    this.day = day;
    this.title = title;
    this.description = description;
    this.category = category;
  }

  /**
   * Returns the title.
   *
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns the category.
   *
   * @return the category
   */
  public Category getCategory() {
    return this.category;
  }


  /**
   * Formats the object's data into a specific format.
   *
   * @return the formatted string
   */
  public String format() {
    String categoryString = "";
    if (category != Category.NONE) {
      categoryString = " (" + category.name() + ")";
    }
    return "* " + title + categoryString + ": " + description;
  }
}
