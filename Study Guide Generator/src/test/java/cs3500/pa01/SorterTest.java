package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the sorter class
 */
class SorterTest {
  ArrayList<Path> afirst;

  ArrayList<Path> oneFile;
  Path arrayPath1;
  Path vectorPath1;
  ArrayList<Path> vfirst;
  Path arrayPath;
  Path vectorPath;

  /**
   * creates lists of paths to be sorted
   */

  @BeforeEach
  public void beforeEach() {
    // sorter tests by filename
    afirst = new ArrayList<>();
    arrayPath1 = Path.of("src/Testfolder/Array.md");
    vectorPath1 = Path.of("src/Testfolder/Vector.md");
    afirst.add(arrayPath1);
    afirst.add(vectorPath1);

    oneFile = new ArrayList<>();
    oneFile.add(arrayPath1);


    vfirst = new ArrayList<>();
    arrayPath = Path.of("src/Testfolder/Array.md");
    vectorPath = Path.of("src/Testfolder/Vector.md");
    vfirst.add(vectorPath);
    vfirst.add(arrayPath);


  }

  /**
   * tests sort by filename
   */
  @Test
  public void sort1() {

    // sorter tests by filename



    Sorter s1 = new Sorter(afirst, "filename");
    assertEquals(afirst, s1.sortbythis());




    Sorter s2 = new Sorter(vfirst, "filename");
    assertEquals(afirst, s2.sortbythis());

    ArrayList<Path> empty = new ArrayList<>();
    Sorter s3 = new Sorter(empty, "filename");
    assertEquals(empty, s3.sortbythis());

    Sorter s4 = new Sorter(oneFile, "filename");
    assertEquals(oneFile, s4.sortbythis());

    // tests for sort by created



    Sorter s5 = new Sorter(vfirst, "created");
    assertEquals(afirst, s5.sortbythis());

    Sorter s6 = new Sorter(afirst, "created");
    assertEquals(afirst, s6.sortbythis());


    Sorter s7 = new Sorter(empty, "created");
    assertEquals(empty, s7.sortbythis());

    Sorter s8 = new Sorter(oneFile, "created");
    assertEquals(oneFile, s8.sortbythis());

    //tests for last modified


    Sorter s9 = new Sorter(vfirst, "modified");
    assertEquals(vfirst, s9.sortbythis());

    Sorter s10 = new Sorter(afirst, "modified");
    assertEquals(vfirst, s10.sortbythis());


    Sorter s11 = new Sorter(empty, "modified");
    assertEquals(empty, s11.sortbythis());

    Sorter s12 = new Sorter(oneFile, "modified");
    assertEquals(oneFile, s12.sortbythis());

    // tests exc

    Sorter exc = new Sorter(afirst, "crazy stuff");
    assertThrows(IllegalArgumentException.class, () ->  exc.sortbythis());


  }


}