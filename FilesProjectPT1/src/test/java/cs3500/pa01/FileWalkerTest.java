package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for file walker class
 */
class FileWalkerTest {


  FileWalker fw;


  Path startingDir;
  Path wrongdir;

  ArrayList<Path> mdOnly;

  @BeforeEach

  public void beforeEach() {

    fw = new FileWalker();


    startingDir = Path.of("src/test/Folder1");

    wrongdir = Path.of("Does not exist");
    try {
      Files.walkFileTree(startingDir, fw);
    } catch (IOException e) {
      System.out.println("Nothing inside");
    }
    mdOnly = new ArrayList<Path>();
    mdOnly.add(Path.of("src/test/Folder1/file1.md"));
    mdOnly.add(Path.of("src/test/Folder1/file3.md"));



  }

  @Test
  public void fwTests() {


    System.out.println(fw.getFiles().size());
    assertEquals(mdOnly, fw.getFiles());
  }



}