package cs3500.pa05.model;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * A class that inherits FileVisitor, visiting the file system and compiling an ArrayList of the
 * bujo files in the resource folder
 */
public class BuJoObtainer implements FileVisitor<Path> {

  private final ArrayList<String> bujoFilePaths;

  /**
   * A constructor for a MarkDownObtainer which takes in nothing and initializes markdownFiles,
   * an ArrayList of FileInfo, to an empty list
   */
  public BuJoObtainer() {
    this.bujoFilePaths = new ArrayList<>();
  }

  /**
   * An inherited method from the FileVisitor interface which is called before a directory
   * is visited
   *
   * @param dir
   *          a reference to the directory
   * @param attrs
   *          the directory's basic attributes
   *
   * @return - Returns CONTINUE to continue walking the directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * An inherited method from the FileVisitor interface which is called when a file is visited,
   * creating a new FileInfo and adding it to bujoFilePaths if the visited file is a bujo file
   *
   * @param file
   *          a reference to the file
   * @param attrs
   *          the file's basic attributes
   *
   * @return - Returns CONTINUE to continue walking the directory
   *
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (attrs.isRegularFile() && file.getFileName().toString().endsWith(".bujo")) {
      String fileName = file.getFileName().toString();
      bujoFilePaths.add(fileName.substring(0, fileName.length() - 5));
    }
    return CONTINUE;
  }

  /**
   * An inherited method from the FileVisitor interface which is called when a file is visited and
   * the visit fail
   *
   * @param file
   *          a reference to the file
   * @param exc
   *          the I/O exception that prevented the file from being visited
   *
   * @return - Returns CONTINUE to continue walking the directory
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    return CONTINUE;
  }

  /**
   * An inherited method from the FileVisitor interface which is called after a directory
   * is visited
   *
   * @param dir
   *          a reference to the directory
   * @param exc
   *          {@code null} if the iteration of the directory completes without
   *          an error; otherwise the I/O exception that caused the iteration
   *          of the directory to complete prematurely
   *
   * @return - Returns CONTINUE to continue walking the directory
   *
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc)  {
    return CONTINUE;
  }

  /**
   * A getter method used to get the BujoFileObtainer's bujoFilePaths
   *
   * @return - Returns the BujoFileObtainer's bujoFilePaths
   */
  public ArrayList<String> getBujoFilePaths() {
    return this.bujoFilePaths;
  }
}
