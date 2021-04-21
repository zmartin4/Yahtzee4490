package yahtzee;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class YahtzeeServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;
  private ArrayList<ConnectionToClient> clients;
  private DatabaseFile db = new DatabaseFile();
  private ArrayList<ConnectionToClient> clientsID;
  // Our dice values will be kept up with on the server
  // When a player rolls the dice, these values will update and be sent to all players

  // private int[] diceValues = {0,0,0,0,0};
  // private int[] diceValues = {0,0,0,0,0};
  private Integer[] playerScore = new Integer[16];
  private Integer[] diceValues = new Integer[5];
  private Boolean[] rollable = new Boolean[5];

  // private Database db = new Database();
  // I actually have the solution for the Database lab, if you need it


  public YahtzeeServer() {
    super(12345);
  }

  public YahtzeeServer(int port) {
    super(port);
  }

  public void setLog(JTextArea log) {
    this.log = log;
  }

  public JTextArea getLog() {
    return log;
  }

  public void setStatus(JLabel status) {
    this.status = status;
  }

  public JLabel getStatus() {
    return status;
  }



  @Override
  protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
    // TODO Auto-generated method stub
    System.out.println("Message from Client" + arg0.toString() + " ||||  " + arg1.toString());



    if (arg0 instanceof LoginData) {
      LoginData loginData = (LoginData) arg0;


      // LoginControl loginControl = (LoginControl) arg0;
      if (db.verifyLogin(loginData.getUsername(), loginData.getPassword())) {

        try {
          arg1.sendToClient("LS");
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else {
        try {
          arg1.sendToClient("LF");
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.out.println("Login Failed");
      }
    }

    if (arg0 instanceof CreateAccountData) {

      CreateAccountData createAccountData = (CreateAccountData) arg0;

      System.out.println("Username/Password" + createAccountData.getUsername() + " "
          + createAccountData.getPassword());
      if (!db.addLogin(createAccountData.getUsername(), createAccountData.getPassword())) {
        System.out.println("Account Failed");
        try {
          arg1.sendToClient("NAF");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {

        System.out.println("New Account");
        try {
          arg1.sendToClient("NAS");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    if (arg0 instanceof GameData) {

      GameData gameData = (GameData) arg0;
      this.diceValues = gameData.getDiceValues();
      this.rollable = gameData.getRollable();
      this.playerScore = gameData.getPlayerScore();


      List<Object> sections = new ArrayList<Object>();
      sections.add(diceValues);
      sections.add(rollable);
      sections.add(playerScore);

      // Set server diceValues to whatever we have received from gameData



      // Update all clients with the diceValues
      sendToAllClients(sections);
    }

    // We should also have the "categories" from all players here as well

    // Sample command to get card values from gameData to send to all players

    // Essentially, what the server is doing is it will take values from gameData to transmit to all
    // other players
    // gameData.getCardValues();

    // ^ Not sure what else to do with gameData but I'll implement more later


  }

  // if(arg0 instanceof JoinServerData)
  // ^ We might not be doing JoinServer



  protected void listeningException(Throwable exception) {
    // Display info about the exception
    System.out.println("Listening Exception:" + exception);
    exception.printStackTrace();
    System.out.println(exception.getMessage());

    /*
     * if (this.isListening()) { log.append("Server not Listening\n");
     * status.setText("Not Connected"); status.setForeground(Color.RED); }
     */

  }

  protected void serverStarted() {
    System.out.println("Server Started");
    status.setText("\nListening");
    status.setForeground(Color.green);
    log.append("Server Started");
    // log.append("Server Started\n");
  }

  protected void serverStopped() {
    status.setText("Stopped");
    status.setForeground(Color.red);
    log.append(
        "\nServer Stopped Accepting New Clients - Press Listen to Start Accepting New Clients");
  }

  protected void serverClosed() {
    status.setText("Closed");
    status.setForeground(Color.red);
    log.append("\nServer and all current clients are closed - Press Listen to Restart");
  }


  protected void clientConnected(ConnectionToClient client) {
    log.append("Client Connected \n");

    // If we aren't at the maximum amount of players, then add the client
    if (clients.size() < 5) {
      clients.add(client);
    } else if (clients.size() == 5) {
      // When the maximum amount of players joins, start the game
      sendToAllClients("Start game");

      // The first client will go, starting the game
      try {
        clients.get(0).sendToClient("Go");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        clients.get(1).sendToClient("Stop");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        clients.get(2).sendToClient("Stop");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        clients.get(3).sendToClient("Stop");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        clients.get(4).sendToClient("Stop");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        client.sendToClient("Maximum amount of players reached");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
