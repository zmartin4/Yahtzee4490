package yahtzee;

import java.util.ArrayList;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

  LoginControl lc;
  CreateAccountControl cac;
  GameControl gc;
  MainMenuControl mmc;

  int id;
  String username;
  String currPlayer;



  public ChatClient() {
    super("localhost", 8300);

  }

  @Override
  public void handleMessageFromServer(Object arg0) {


    if (arg0.equals("NAS")) {
      // System.out.println("ChatClient - New Account");
      cac.createAccountSuccess();

    } else if (arg0.equals("LS")) {
      // System.out.println("ChatClient - Login");
      lc.loginSuccess();

    } else if (arg0.equals("LF")) {
      // System.out.println("ChatClient - Login Failed");
      lc.displayError("Incorrect Username or Password");

    } else if (arg0.toString().startsWith("uName,")) {
      System.out.println("ChatClient - Get Username");
      username = arg0.toString().substring(6);
      gc.setUsername(arg0.toString().substring(6));

      // TODO
    } else if (arg0.equals("Make Lobby")) { // TODO
      mmc.setLobby();
    } else if (arg0.equals("Join Lobby")) { // TODO
      mmc.setLobby();

      // Initial Scoreboard Setup | Dynamically sets Opponetes names when they 'New Game'
    } else if (arg0 instanceof ArrayList) {
      ArrayList<String> oppUsernames = (ArrayList<String>) arg0;
      oppUsernames.remove(username);
      gc.setOppUsernames(oppUsernames);


    } else if (arg0.equals("Go")) {
      gc.goTurn();
      System.out.println("GO");
    } else if (arg0.equals("Wait")) {
      System.out.println("WAIT");
      gc.waitTurn();

    } else if (arg0 instanceof GameData) {
      gc.setGameData(arg0);
    } else if (arg0 instanceof String) {
      gc.setWhosData(arg0);
    }



  }


  public void connectionException(Exception exception) {
    exception.printStackTrace();
  }

  public void connectionEstablished() {

  }



  public void setLoginControl(LoginControl lc) {
    this.lc = lc;
  }

  public void setCreateAccountControl(CreateAccountControl cac) {
    this.cac = cac;
  }

  public void setGameControl(GameControl gc) {
    this.gc = gc;
  }
}
