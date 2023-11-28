package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortByCreationTest {
  SortByCreation s;

  @BeforeEach

  public void beforeEach() {
    s = new SortByCreation();




  }
//This builds locally but not in Github
//  @Test
//  public void tbc() {
//    assertEquals(-1,
//        s.compare(Path.of("src/Testfolder/Array.md"),
//            Path.of("src/Testfolder/Vector.md")));
//    assertThrows(RuntimeException.class,
//        () -> s.compare(Path.of("Nothing"), Path.of("Nothing")));
//    // NOT SURE HOW TO TEST TRY CATCH
//  }

}