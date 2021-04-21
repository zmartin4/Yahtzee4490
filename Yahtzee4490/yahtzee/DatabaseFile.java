package yahtzee;


import java.util.ArrayList;
import java.util.Random;

public class DatabaseFile {

  private ArrayList<String[]> loginInfo = new ArrayList<String[]>();

  protected void printLogin() {
    System.out.println();
    for (String login[] : loginInfo) {
      System.out.println(login[0] + " " + login[1] + " " + login[2]);
    }
  }

  protected boolean verifyLogin(String username, String password) {
    for (String login[] : loginInfo) {
      if (username.equals(login[0]) && password.equals(login[1])) {
        return true;
      }
    }
    return false;
  }

  protected boolean addLogin(String username, String password) {
    int id = -1;

    if (!duplicateLogin(username)) {
      while (id == -1) {
        id = createID();
      }
      String login[] = {username, password, String.valueOf(id)};
      loginInfo.add(login);
      return true;
    }
    return false;
  }

  private int createID() {

    Random rand = new Random();
    int rand_int = rand.nextInt(1000);
    for (String login[] : loginInfo) {
      if (rand_int == Integer.parseInt(login[2])) {
        return -1;
      }
    }
    return rand_int;

  }

  public String getID(String username) {
    for (String login[] : loginInfo) {
      if (username.equals(login[0]))
        return login[2];
    }
    return null;
  }

  protected boolean duplicateLogin(String username) {
    for (String login[] : loginInfo) {
      if (username.equals(login[0])) {
        return true;
      }
    }
    return false;

  }
}
