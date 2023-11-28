package cs3500.pa05.model;

/**
 * The Quotes class represents a quote entry in a journal.
 * It extends the Entry class and provides functionality to save the quote to a file.
 */
public class Quote extends Entry {

  private final String author;

  /**
   * Constructs a Quotes object with the specified text.
   *
   * @param quote the text content of the quote
   * @param author the person who said the quote
   */
  public Quote(String quote, String author) {
    super(quote);
    this.author = author;
  }

  /**
   * Formats the item into a string representation.
   *
   * @return the formatted string representation of the item
   */
  @Override
  public String format() {
    String authorText = "";
    if (!this.author.isEmpty()) {
      authorText = " - " + author;
    }
    return "* \"" + this.text + "\"" + authorText;
  }

  /**
   * Retrieves the author of the quote.
   *
   * @return the author of the quote
   */
  public String getAuthor() {
    return this.author;
  }

}


