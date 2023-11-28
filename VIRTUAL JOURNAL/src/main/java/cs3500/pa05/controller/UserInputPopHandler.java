package cs3500.pa05.controller;

import cs3500.pa05.view.CreationPane;
import cs3500.pa05.view.EventPane;
import cs3500.pa05.view.NotePane;
import cs3500.pa05.view.QuotePane;
import cs3500.pa05.view.TaskPane;
import javafx.scene.control.Tab;

/**
 * class the represents the inputPopHandler
 */
public class UserInputPopHandler {

  /**
   * @param currentTab the current tab
   * @param controller the journal controller
   */
  public static void handleCreateButton(Tab currentTab, JournalController controller) {
    CreationPane currentPane;

    if (currentTab.getText().equals("Event")) {
      currentPane = (EventPane) currentTab.getContent();
    } else if (currentTab.getText().equals("Task")) {
      currentPane = (TaskPane) currentTab.getContent();
    } else if (currentTab.getText().equals("Quote")) {
      currentPane = (QuotePane) currentTab.getContent();
    } else if (currentTab.getText().equals("Note")) {
      currentPane = (NotePane) currentTab.getContent();
    } else {
      throw new IllegalArgumentException("Error: unrecognized creation tab");
    }

    if (currentPane.canCreatePart()) {
      currentPane.createPart(controller);
      controller.addDeleteButton();
      controller.initLabels();
      controller.initTheme();
    }
  }

}
