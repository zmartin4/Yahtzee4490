package yahtzee;

import java.io.Serializable;

public class LoginData implements Serializable {
  // Private data fields for the username and password.
  private String username;
  private String password;
  private String id;

  // Getters for the username and password.
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getId() {
    return id;
  }

  // Setters for the username and password.
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(String id) {
    this.id = id;
  }

  // Constructor that initializes the username and password.
  public LoginData(String username, String password) {
    setUsername(username);
    setPassword(password);

  }
}
