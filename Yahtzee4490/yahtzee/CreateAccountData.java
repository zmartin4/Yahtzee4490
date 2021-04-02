package yahtzee;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
  // Private data fields for the username and password.
  private String username;
  private String password;
  private String verifyPassword;

  // Getters for the username and password.
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getVerifyPassword() {
    return verifyPassword;
  }

  // Setters for the username and password.
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setVerifyPassword(String verifyPassword) {
    this.verifyPassword = verifyPassword;
  }

  // Constructor that initializes the username and password.
  public CreateAccountData(String username, String password, String verifyPassword) {
    setUsername(username);
    setPassword(password);
    setVerifyPassword(verifyPassword);
  }
}

