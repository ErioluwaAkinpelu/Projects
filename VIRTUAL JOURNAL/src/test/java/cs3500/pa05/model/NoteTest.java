package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoteTest {
  private Note note;

  @BeforeEach
  public void setUp() {
    String text = "This is a note.";
    note = new Note(text);
  }

  @Test
  public void testFormat() {
    String expected = "* This is a note.";
    String formatted = note.format();
    assertEquals(expected, formatted);
  }


}