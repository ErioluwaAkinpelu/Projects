package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;

/**
 * interface to create Panes
 */
public interface CreationPane {
  /**
   * Checks if it is possible to create a new part in the journal.
   *
   * @return true if a new part can be created, false otherwise.
   */
  boolean canCreatePart();

  /**
   * Performs an error diagnosis and provides information about the error.
   *
   * @param error the error message or description to be diagnosed.
   */
  void diagnoseError(String error);

  /**
   * Creates a new part in the journal using the provided controller.
   *
   * @param controller the JournalController object used for creating the part.
   */
  void createPart(JournalController controller);
}