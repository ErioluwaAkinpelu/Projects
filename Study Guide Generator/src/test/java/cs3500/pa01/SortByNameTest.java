package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortByNameTest {
  sortByName s;

  @BeforeEach

  public void beforeEach() {
    s = new sortByName();




  }

  @Test
    public void tbn() {
    assertEquals(-21,
          s.compare(Path.of("src/Testfolder/Array.md"),
              Path.of("src/Testfolder/Vector.md")));

  }

}
