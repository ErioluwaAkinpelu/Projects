package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class sortByModi implements Comparator<Path> {
  public int compare(Path f1, Path f2) {

    BasicFileAttributes attr1 = null;
    BasicFileAttributes attr2 = null;
    try {
      attr1 = Files.readAttributes(f1, BasicFileAttributes.class);
      attr2 = Files.readAttributes(f2,BasicFileAttributes.class);

    } catch (IOException e) {
//      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }

    return attr1.lastModifiedTime().compareTo(attr2.lastModifiedTime());


  }
}
