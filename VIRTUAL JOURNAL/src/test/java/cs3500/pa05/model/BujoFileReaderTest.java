//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//
//class BujoFileReaderTest {
//
//  @Test
//  void convertFileToBujo() {
//    assertDoesNotThrow(() -> new BujoFileReader("src/test/java/testFiles/sample",
//            "src/main/resources/sample").convertFileToBujo());
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidCategory",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid category");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidContents",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid contents");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidDayOfWeek",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid day of week");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidDuration",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid duration");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidMaxEvent",
//        "src/main/resources/sample").convertFileToBujo(),
//        "Invalid integer");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidMaxTask",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid integer");
//    assertThrows(IllegalArgumentException.class,
//        () -> new BujoFileReader("src/test/java/testFiles/sampleInvalidStartTime",
//            "src/main/resources/sample").convertFileToBujo(),
//        "Invalid start time");
//  }
//}