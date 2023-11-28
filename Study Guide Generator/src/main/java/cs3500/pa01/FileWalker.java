package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * creates a list of only markdown files
 */
public class FileWalker implements FileVisitor<Path> {
  ArrayList<Path> files;

  public FileWalker() {
    this.files = new ArrayList<>();
  }

  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws
      IOException {
    return CONTINUE;
  }

  /**
   * visitFile method
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

    if (file.toString().endsWith(".md")) {


      this.files.add(file);
      // gets file names


    }
    return CONTINUE;//skips it
  }

  /**
   * tests for visitFileFailed
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    System.err.println("Nothing here");
    return CONTINUE;

  }


  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {


    return CONTINUE;
  }

  public ArrayList<Path> getFiles(){
    return this.files;
  }
}
