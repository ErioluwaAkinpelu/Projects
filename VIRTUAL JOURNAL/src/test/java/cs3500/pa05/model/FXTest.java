package cs3500.pa05.model;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

public abstract class FXTest {
  @BeforeAll
  static void initJfxRuntime() {
    Platform.startup(() -> {
      // This block will be executed on JavaFX Thread
    });
  }

}
