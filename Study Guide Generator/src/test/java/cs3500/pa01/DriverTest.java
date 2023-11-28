package cs3500.pa01;




import org.junit.jupiter.api.Test;

class DriverTest {
  @Test
  public void testDriver() {
    String[] args = new String[3];
    args[0] = "src/test/Folder1/file3.md";
    args[1] = "modified";
    args[2] = "file.txt";

    Driver.main(args);
  }



}