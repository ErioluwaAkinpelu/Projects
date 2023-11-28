package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.w3c.dom.ls.LSOutput;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    // File endfile = args[2]
  Path startingDir = Path.of(args[0]);

  FileWalker walker = new FileWalker();

  // walk through the file tree once and takes all the files
    try {
      Files.walkFileTree(startingDir, walker);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Sorter sortbygiven = new Sorter(walker.getFiles(), args[1]);
    Processor p = new Processor();

    File f1 = new File(args[2]);
    try {
      File StudyGuide = p.fileWriter(p.filter(sortbygiven.sortbythis()),f1);
    } catch (IOException e) {
      System.err.println("Could not write file");
    }


  }

}



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.






