package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;


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

    if (args.length == 0) {
      Processor p = new Processor();


      ViewImpl view = new ViewImpl(System.out);

      ControllerImpl controller = new ControllerImpl(view, new InputStreamReader(System.in));
      try {
        controller.run();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      File endfile = new File(args[2]);
      Path startingDir = Path.of(args[0]);

      FileWalker walker = new FileWalker();
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
  }









