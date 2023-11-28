package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SecureBulletJournalModelTest {

  @Test
  void authenticate() {
    SecureBulletJournalModel sbjm = new SecureBulletJournalModel("password123");
    assertTrue(sbjm.authenticate("password123"));
    assertFalse(sbjm.authenticate("wrongPassword"));
  }
}