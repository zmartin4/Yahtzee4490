package yahtzee;

import java.util.ArrayList;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

  LoginControl lc;
  CreateAccountControl cac;
  GameControl gc;
  MainMenuControl mmc;
  LeaderboardControl lbc;

  int id;
  String username;
  String currPlayer;



  public ChatClient() {
    super("localhost", 8300);

  }

  @Override
  public void handleMessageFromServer(Object arg0) {

    /////// Login and Create Account //////////
    if (arg0.equals("NAS")) { // If account created successfully
      cac.createAccountSuccess();
    } else if (arg0.equals("LS")) { // If account logged in successfully
      lc.loginSuccess();
    } else if (arg0.equals("LF")) {// If account login failed
      lc.displayError("Incorrect Username or Password");
    } else if (arg0.equals("ALI")) {// If account login failed
      lc.displayError("That user has already logged in");


      ////// Main Menu Redirection //////
    } else if (arg0.equals("New Lobby")) { // If the first person to select New Game
      mmc.newLobby();
    } else if (arg0.equals("New Lobby Redirect")) { // If game lobby is already made
      mmc.newLobbyRedirect();
    } else if (arg0.equals("Join Lobby")) { // If able to join game lobby
      mmc.joinLobby();
    } else if (arg0.equals("Join Lobby Redirect")) { // If game lobby is full
      mmc.joinLobbyRedirect();
    } else if (arg0.equals("No Lobby Redirect")) { // If game lobby has not been made
      mmc.noLobbyRedirect();


      ////// Yahtzee Game Information Setup //////
    } else if (arg0.toString().startsWith("uName,")) { // Sets clients Username in GameControl
      username = arg0.toString().substring(6);
      gc.setUsername(arg0.toString().substring(6));
    } else if (arg0 instanceof Integer) { // Sets the number of Opponents
      int numP = (int) arg0;
      gc.setNumOpps(numP);
    } else if (arg0 instanceof ArrayList) {// Dynamically sets Opponents names when they Join Lobby
      System.out.println("ArrayList");
      ArrayList<String> oppUsernames = (ArrayList<String>) arg0;
      oppUsernames.remove(username);
      gc.setOppUsernames(oppUsernames);
    } else if (arg0.equals("Game Over")) { // If the game is over
      gc.gameReset();


      ////// Leaderboard Information //////
    } else if (arg0 instanceof String[][]) { // Sets the Leaderboard Data
      lbc.setLeaderboardData(arg0);

      ////// Yahtzee Game Flow Control //////
    } else if (arg0.equals("Go")) { // Allows a client to interact with GamePanel
      gc.goTurn();
      // System.out.println("Go");
    } else if (arg0.equals("Wait")) { // Does not allow client interaction with GamePanel
      gc.waitTurn();
      // System.out.println("Wait");
    } else if (arg0.equals("Lobby Size Error")) { // Does not allow client interaction with
                                                  // GamePanel
      gc.lobbyMessage();
    } else if (arg0 instanceof GameData) { // Sends GameData to Client
      gc.setGameData(arg0);
    } else if (arg0 instanceof String) { // Sends the username of the GameData
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

  public void setMainMenuControl(MainMenuControl mmc) {
    this.mmc = mmc;
  }

  public void setLeaderboardControl(LeaderboardControl lbc) {
    this.lbc = lbc;

  }
}
