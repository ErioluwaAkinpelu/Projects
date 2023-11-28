package cs3500.pa05.model;

/**
 * The Entry class represents an entry in a journal.
 * It is an abstract class that provides common functionality for different types of entries.
 */
public abstract class Entry {

  /**
   * The TextArea component that holds the text content of the entry.
   */
  String text;

  /**
   * Constructs an Entry object with the specified text.
   *
   * @param text the text content of the entry
   */
  public Entry(String text) {
    this.text = text;
  }

  /**
   * Returns the text content of the entry.
   *
   * @return the text content of the entry
   */
  public String getText() {
    return text;
  }


  /**
   * Formats the object into a string representation.
   *
   * @return the formatted string representation of the object
   */
  public abstract String format();
}