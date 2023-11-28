package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.CustomThemeApplier;
import cs3500.pa05.model.Entry;
import cs3500.pa05.model.EssentialPart;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Note;
import cs3500.pa05.model.Quote;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.WeekDay;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import javafx.scene.Node;

/**
 * The BujoFileWriter class is responsible for writing data to a bullet journal file.
 * It provides methods to write the contents of a bullet journal to a file in a specific format.
 */
public class BujoFileWriter {

  private int indentation;
  private Appendable fileContents;
  private JournalController controller;

  /**
   * Converts a JournalController into contents for a file, which can later be loaded
   *
   * @param controller - The JournalController to be converted into a file
   * @return - The JournalController's contents formatted as a string for a file
   * @throws IOException - Can throw an exception
   */
  public String convertToFile(JournalController controller) throws IOException {
    this.fileContents = new StringBuilder();
    this.indentation = 0;
    this.controller = controller;

    fileContents.append("{").append("\n");
    indentation++;
    writeBasicInfo();
    fileContents.append(indent(indentation));
    fileContents.append("\"days\": [\n");
    indentation++;
    for (Node day : controller.getDays()) {
      writeDay(day);
    }
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("],\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"quotes-and-notes\": [\n");
    indentation++;
    for (Entry entry : controller.getQuotesAndNotesList()) {
      writeEntry(entry);
    }
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("]\n");
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("}\n");
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("}");

    return fileContents.toString();

  }

  private void writeBasicInfo() throws IOException {
    fileContents.append(indent(indentation));
    fileContents.append("\"journal\": {\n");
    indentation++;
    fileContents.append(indent(indentation));
    fileContents.append("\"title\": \"").append(controller.getWeekName().getText()).append("\",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"theme\": ").append(
        controller.getThemeDropdown().getThemeComboBox().getValue().toString()).append(",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"custom-theme-param\": {\n");
    indentation++;
    fileContents.append(indent(indentation));
    String bgColor = "000000";
    String textColor = "ffffff";
    String textFont = "Arial";
    if (controller.getThemeDropdown().getThemeApplier() instanceof CustomThemeApplier cta) {
      bgColor = cta.bgColorToString();
      textColor = cta.textColorToString();
      textFont = cta.fontToString();
    }
    fileContents.append("\"bgColor\": \"").append(bgColor).append("\",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"textColor\": \"").append(textColor).append("\",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"textFont\": \"").append(textFont).append("\"\n");
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("},\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"max-event\": ").append(String.valueOf(controller.getMaxEventPerDay()))
        .append(",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"max-task\": ").append(String.valueOf(controller.getMaxTaskPerDay()))
        .append(",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"start-of-week\": ").append(String.valueOf(controller.getWeekModel()
        .getStartOfWeek())).append(",\n");
  }



  private void writeDay(Node day) throws IOException {
    fileContents.append(indent(indentation));
    fileContents.append("{\n");
    indentation++;
    fileContents.append(indent(indentation));
    WeekDay weekDay = (WeekDay) day;
    fileContents.append("\"day\": ").append(weekDay.getDayOfWeek().name()).append(",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"essential-parts\": [\n");
    indentation++;
    for (EssentialPart essentialPart : weekDay.getEssentialParts()) {
      writeEssentialPart(essentialPart);
    }
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("]\n");
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("},\n");
  }

  private void writeEssentialPart(EssentialPart essentialPart) throws IOException {
    fileContents.append(indent(indentation));
    fileContents.append("{\n");
    indentation++;
    fileContents.append(indent(indentation));
    fileContents.append("\"title\": \"").append(
        essentialPart.getTitle()).append("\",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"description\": \"").append(
        essentialPart.getDescription()).append("\",\n");
    fileContents.append(indent(indentation));
    fileContents.append("\"category\": ").append(
        essentialPart.getCategory().name()).append(",\n");
    fileContents.append(indent(indentation));
    if (essentialPart instanceof Event event) {
      fileContents.append("\"start-time\": ").append(
          String.valueOf(event.getStartTime())).append(",\n");
      fileContents.append(indent(indentation));
      fileContents.append("\"duration\": ").append(
          String.valueOf(event.getDuration())).append("\n");
    } else if (essentialPart instanceof Task task) {
      fileContents.append("\"completed\": ").append(
          String.valueOf(task.getCompletion())).append("\n");
    }
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("},\n");
  }

  private void writeEntry(Entry entry) throws IOException {
    fileContents.append(indent(indentation));
    fileContents.append("{\n");
    indentation++;
    if (entry instanceof Quote quote) {
      fileContents.append(indent(indentation));
      fileContents.append("\"quote\": \"").append(quote.getText()).append("\",\n");
      fileContents.append(indent(indentation));
      fileContents.append("\"author\": \"").append(quote.getAuthor()).append("\"\n");
    } else if (entry instanceof Note note) {
      fileContents.append(indent(indentation));
      fileContents.append("\"note\": \"").append(note.getText()).append("\"\n");
    }
    indentation--;
    fileContents.append(indent(indentation));
    fileContents.append("},\n");
  }



  /**
   * Creates a file containing contents representing a JournalController
   *
   * @param fileContents - The contents of a JournalController as a string
   * @param filePath - The path which the file will be written to
   * @throws IOException - Can throw an exception
   */
  public void writeToFile(String fileContents, Path filePath) throws IOException {
    File bujoFile = new File(filePath + ".bujo");
    FileWriter fw = new FileWriter(bujoFile);
    fw.write(fileContents);
    fw.close();
  }

  /**
   * Generates an indentation string based on the specified indentation level.
   *
   * @param indentation The level of indentation to generate the string for.
   * @return The indentation string consisting of tabs.
   */
  private String indent(int indentation) {
    return "\t".repeat(Math.max(0, indentation));
  }
}