package cs3500.pa05.model;

import static java.nio.file.FileVisitResult.CONTINUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuJoObtainerTest {

  private BuJoObtainer bjo;
  private Path failedPath;

  @BeforeEach
  void setup() throws IOException {
    bjo = new BuJoObtainer();
    Files.walkFileTree(Path.of("src/main/resources"), bjo);
    failedPath = Files.createTempDirectory("failedFile.bujo");
  }


  /**
   * Tests getBujoFilesPaths()
   */
  @Test
  void testGetBujoFilePaths() {
    ArrayList<String> files = bjo.getBujoFilePaths();
    assertEquals(files.size(), 5);
  }

  /**
   * Tests the inherited visitFileFailed() method to make sure it returns CONTINUE
   */
  @Test
  void testVisitFileFailed() {
    assertEquals(bjo.visitFileFailed(failedPath, new IOException()), CONTINUE);
  }

}