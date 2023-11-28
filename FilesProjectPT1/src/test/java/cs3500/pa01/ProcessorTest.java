package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Processor Test class
 */
class ProcessorTest {
  ArrayList<Path> afirst;

  Path arrayPath1;
  Path vectorPath1;
  ArrayList<Path> vfirst;
  Path arrayPath;
  Path vectorPath;

  Processor p;
  ArrayList<String> content;

  ArrayList<Path> badFiles;
  File f;

  /**
   * Creates two list of paths that need to be sorted
   */
  @BeforeEach
  public void beforeEach() {
    // sorter tests by filename
    afirst = new ArrayList<>();
    arrayPath1 = Path.of("src/Testfolder/Array.md");
    vectorPath1 = Path.of("src/Testfolder/Vector.md");
    afirst.add(arrayPath1);
    afirst.add(vectorPath1);

    vfirst = new ArrayList<>();
    arrayPath = Path.of("src/Testfolder/Array.md");
    vectorPath = Path.of("src/Testfolder/Vector.md");
    vfirst.add(vectorPath);
    vfirst.add(arrayPath);


    content = new ArrayList<>();
    content.add("# Heading");
    content.add("Important");

    p = new Processor();
    try {
      f = p.fileWriter(content, Path.of("src/main/java/cs3500/pa01/test.txt").toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    badFiles = new ArrayList<>();
    badFiles.add(Path.of("Nothing inside"));


  }

  /**
   * test for filter method
   */
//  @Test
//
//  public void testfilter() {
//
//
//    ArrayList<String> filtered = new ArrayList<>();
//    filtered.add("# Heading");
//    filtered.add("Important");
//    filtered.add("stuff");
//    filtered.add("Important");
//    filtered.add("# Vectors");
//    filtered.add("Vectors act like resizable arrays");
//
//
//    Processor p = new Processor();
//
//    assertEquals(filtered, p.filter(afirst));
//
//
//    ArrayList<String> filtered2 = new ArrayList<>();
//    filtered2.add("# Vectors");
//    filtered2.add("Vectors act like resizable arrays");
//    filtered2.add("# Heading");
//    filtered2.add("Important");
//    filtered2.add("stuff");
//    filtered2.add("Important");
//
//
//    Processor p1 = new Processor();
//
//    assertEquals(filtered2, p1.filter(vfirst));
//
//    // how to test catch
//    ArrayList<Path> badFiles = new ArrayList<Path>();
//    badFiles.add(Path.of("Nothing"));
//    //    assertThrows(FileNotFoundException.class,p1.filter(badFiles));
//  }
//



  /**
   * test for fil writer method
   */
//  @Test
//  public void testfileWriter() throws IOException {
//
//
//
//    Scanner myReader = new Scanner(f);
//
//    assertEquals("# Heading", myReader.nextLine());
//
//
//  }

}


