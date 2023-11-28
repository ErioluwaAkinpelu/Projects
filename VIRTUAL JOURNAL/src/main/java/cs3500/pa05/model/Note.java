package cs3500.pa05.model;

/**
 * The Notes class represents a note entry in a journal.
 * It extends the Entry class and provides functionality to save the note to a file.
 */
public class Note extends Entry {

  /**
   * Constructs a Notes object with the specified text.
   *
   * @param text      the text content of the note
   */
  public Note(String text) {
    super(text);
  }

  /**
   * Formats the item into a string representation.
   *
   * @return the formatted string representation of the item
   */
  @Override
  public String format() {
    return "* " + this.text;
  }
}