package yahtzee;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;

  private DatabaseFile database = new DatabaseFile();
  private Database db = new Database();

  private Integer[] playerScore = new Integer[16];
  private Integer[] diceValues = new Integer[5];
  private Boolean[] rollable = new Boolean[5];
  private Integer[] playerScores = new Integer[3];
  private ArrayList<Integer[]> gameHistory;
  int counter = 0;
  int playersChosen = 2;
  int oppTurn = 0;
  int counter_reset = 0;
  int roundCounter = 0;
  private GameData gameData;
  private ArrayList<ConnectionToClient> clients;
  private ArrayList<String> clientName;
  int[] turns = {0, 0};


  public ChatServer() {
    super(12345);
    clients = new ArrayList<ConnectionToClient>();
    clientName = new ArrayList<String>();
  }

  public ChatServer(int port) {
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

  public GameData getServerGameData() {
    return gameData;
  }

  public void setServerGameData(GameData gameData) {
    this.gameData = gameData;
  }


  @Override
  protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {

    System.out.println("Message from Client" + arg0.toString() + " ||||  " + arg1.getId());

    if (getNumberOfClients() == 0)
      counter = 0;

    if (arg0 instanceof LoginData) {
      LoginData loginData = (LoginData) arg0;

      // Check if login data is correct; Attempt to query data from
      // user table, if the query returns an arraylist then the username and
      // password are correct. If the query returns an empty arraylist
      // then the username and/or password are incorrect.
      String query = "SELECT userName, userPassword FROM TheUser WHERE userName='"
          + loginData.getUsername() + "' AND userPassword='" + loginData.getPassword() + "';";
      ArrayList<String> queryData = new ArrayList<String>();
      queryData = db.query(query);

      if (queryData != null) {
        try {
          arg1.sendToClient("LS");
          try {

            arg1.sendToClient("uName," + loginData.getUsername()); // Sets username in ChatClient
            arg1.setName(loginData.getUsername()); // Sets the ThreadName to the Username entered


          } catch (IOException e) {
            e.printStackTrace();
          }


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

      // Check if create account data is correct; Attempt to query data from
      // user table, if the query returns an arraylist then the username and
      // password are taken. If the query returns an empty arraylist
      // then the username and password are available.
      String query = "SELECT userName, userPassword FROM TheUser WHERE userName='"
          + createAccountData.getUsername() + "' AND userPassword='"
          + createAccountData.getPassword() + "';";
      ArrayList<String> queryData = new ArrayList<String>();
      queryData = db.query(query);

      if (queryData != null) {
        System.out.println("Account Failed");
        try {
          arg1.sendToClient("NAF");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {

        System.out.println("New Account");

        // Create new account - set highscore to 0 upon creation
        query = "INSERT INTO TheUser VALUES('" + createAccountData.getUsername() + "', '"
            + createAccountData.getPassword() + "', 0);";

        try {
          db.executeDML(query);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
        }
        try {
          arg1.sendToClient("NAS");
          arg1.setName(createAccountData.getUsername());
          arg1.sendToClient(arg1.getName()); // send client their username


        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    if (arg0 instanceof GameData) {
      GameData gameData = (GameData) arg0;
      sendToGameClients(arg1.getName()); // send clients what user it came from
      sendToGameClients(gameData); // send clients the GameData


      // Somewhere we may need to keep the high score for a specific user updated

      nextTurn();
      tellToGo();



    }



    if (arg0.equals("New")) {


      if (clients.size() < playersChosen) {
        clients.add(arg1); // add client to game list
        clientName.add(arg1.getName());
        if (clients.size() != 1)
          sendToGameClients(clientName); // sends all clients, the clients in game for scoreboard

      }
      if (clients.size() == playersChosen) {
        log.append("Game Start\n");
        turns[0] = 1;

        tellToGo();
      }
    }

    // TODO
    if (arg0.equals("Client needs to set number of players")) {
      String message = "";

      if (playersChosen == 0)
        message = "Make Lobby";
      else
        message = "Join Lobby";

      try {
        arg1.sendToClient(message);
      } catch (IOException e) {

        e.printStackTrace();
      }
    }
  }


  // Tells whoever's turn it is to go to Go
  public void tellToGo() {

    for (int i = 0; i < 2; i++) {

      if (turns[i] == 1) {
        try {
          clients.get(i).sendToClient("Go");
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else {

        try {
          clients.get(i).sendToClient("Wait");
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }

  // Increments whose turn it is. Wraps around if the fifth client is currently going
  public void nextTurn() {
    for (int i = 0; i < 2; i++) {
      if (turns[i] == 1) {
        turns[i] = 0;

        if (i == 4) {
          turns[0] = 1;
        } else {
          turns[i + 1] = 1;
        }
      }
    }
  }

  // Sends to all clients who are currently in the game (sendToAllClients would include people who
  // haven't selected play game)
  public void sendToGameClients(Object obj) {
    for (int i = 0; i < clients.size(); i++) {
      try {
        clients.get(i).sendToClient(obj);
      } catch (EOFException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }


  protected void listeningException(Throwable exception) {
    // Display info about the exception
    System.out.println("Listening Exception:" + exception);
    exception.printStackTrace();
    System.out.println(exception.getMessage());
  }

  protected void serverStarted() {
    System.out.println("Server Started");
    status.setText("\nListening");
    status.setForeground(Color.green);
    log.append("Server Started");

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

  }



}
