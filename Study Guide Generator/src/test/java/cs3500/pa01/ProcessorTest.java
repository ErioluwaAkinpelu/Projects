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
    content.add("Important Again");
    content.add("- ");

    p = new Processor();
    try {
      f = p.fileWriter(content, Path.of("src/main/java/cs3500/pa01/test.txt").toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    badFiles = new ArrayList<>();
    badFiles.add(Path.of("Nothing inside"));



  }
@Test
public void testfilter() throws IOException {

    assertEquals(content,p.filter(afirst));


}

  @Test
  public void testfilterQ() {
    ArrayList<Question> qList = new ArrayList<>();
    Question q1 = new Question("What is your name ", "eri", Question.Diff.HARD);
    Question q2 = new Question("How old are you ", "19", Question.Diff.HARD);
    Question q3 = new Question("How are you doing ", "I am doing good", Question.Diff.HARD);
    qList.add(q1);
    qList.add(q2);
    qList.add(q3);
    File file = new File("src/test/Folder1/questions2.sr");
    int correctSize = 0;
    try {
      correctSize = p.filterQ(file.toPath()).size();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(2, correctSize);
assertEquals(" What is your name?",p.getQ().get(0).getAsk());
assertEquals(" eri",p.getQ().get(0).getAns());


  }
  @Test
  public void testfileWriter() throws IOException {



    Scanner myReader = new Scanner(f);

    assertEquals("# Heading", myReader.nextLine());


  }

}


