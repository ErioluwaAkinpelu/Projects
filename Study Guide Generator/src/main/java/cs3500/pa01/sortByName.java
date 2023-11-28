package cs3500.pa01;

import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;

public class sortByName implements Comparator<Path> {

  public int compare(Path f1, Path f2) {
    return f1.getFileName().compareTo(f2.getFileName());


  }
}
