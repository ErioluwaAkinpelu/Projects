package cs3500.pa05.model;

/**
 * The SecureBulletJournalModel class represents a secure bullet journal with authentication.
 * It allows managing the bullet journal contents and enforcing access control based on
 * authentication status.
 */
public class SecureBulletJournalModel {
  private final String password;

  /**
   * Constructs a SecureBulletJournalModel object with the provided password.
   *
   * @param password the password required for authentication
   */
  public SecureBulletJournalModel(String password) {
    this.password = password;
  }

  /**
   * Authenticates the user by comparing the provided password with the stored password.
   *
   * @param inputPassword the password provided by the user
   * @return true if the password is correct, false otherwise
   */
  public boolean authenticate(String inputPassword) {
    return password.equals(inputPassword);
  }

}