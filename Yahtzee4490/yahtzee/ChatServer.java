package yahtzee;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;

  private Database db = new Database();



  int playersChosen = 0;
  int roundCounter = 0;
  private GameData gameData;
  private ArrayList<ConnectionToClient> clients;
  private ArrayList<String> clientNames;
  Integer[] turns;
  Integer[] currScores;



  public ChatServer() {
    super(12345);
    clients = new ArrayList<ConnectionToClient>();
    clientNames = new ArrayList<String>();
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

    System.out.println(arg0.toString() + " ||||  " + arg1.getId());


    if (arg0 instanceof LoginData) {
      LoginData loginData = (LoginData) arg0;

      // Check if login data is correct; Attempt to query data from
      // user table, if the query returns an arraylist then the username and
      // password are correct. If the query returns an empty arraylist
      // then the username and/or password are incorrect.
      String query =
          "SELECT userName, userPassword FROM TheUser WHERE userName='" + loginData.getUsername()
              + "' AND userPassword=AES_ENCRYPT('" + loginData.getPassword() + "','secretkey');";
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
      }
    }

    if (arg0 instanceof CreateAccountData) {

      CreateAccountData createAccountData = (CreateAccountData) arg0;

      // Check if create account data is correct; Attempt to query data from
      // user table, if the query returns an arraylist then the username.
      // If the query returns an empty arraylist
      // then the username and password are available.
      String query = "SELECT userName, userPassword FROM TheUser WHERE userName='"
          + createAccountData.getUsername() + "';";
      ArrayList<String> queryData = new ArrayList<String>();
      queryData = db.query(query);

      if (queryData != null) {
        try {
          arg1.sendToClient("NAF");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {

        System.out.println("New Account");

        // Create new account - set highscore to 0 upon creation
        query = "INSERT INTO TheUser VALUES('" + createAccountData.getUsername()
            + "', aes_encrypt('" + createAccountData.getPassword() + "','secretkey'), 0);";

        try {
          db.executeDML(query);
        } catch (SQLException e) {
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

      sendToGameClients(arg1.getName()); // send clients what client sent the data
      sendToGameClients(gameData); // send clients the GameData

      Integer[] dv = gameData.getDiceValues();
      Boolean[] r = gameData.getRollable();
      Boolean turnOver = true;

      for (int i : dv) {
        if (i != 1)
          turnOver = false;
      }
      for (Boolean d : r) {
        if (d == false)
          turnOver = false;
      }

      if (turnOver) {
        for (int i = 0; i < playersChosen; i++) {
          if (turns[i] == 1) {
            currScores[i] = gameData.getPlayerScore()[16];
          }
        }
        nextTurn();
        if (roundCounter == 13) {

          gameover();
        }
        tellToGo();



      }

    }
    if (arg0 instanceof String) { // Redirects Client on the MainMenu depending on the game state
      String command = (String) arg0;

      if (command.equals("New Game")) {
        String message = "";

        if (playersChosen == 0) {
          message = "New Lobby"; // New Lobby - playersChosen not set

        } else
          message = "New Lobby Redirect"; // Redirect to join the created lobby - playersChosen is
                                          // set

        try {
          arg1.sendToClient(message);
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else if (command.equals("Join Game")) {

        if (clients.size() == 0) { // if the game lobby has not been made
          try {
            arg1.sendToClient("No Lobby Redirect");
          } catch (IOException e) {
            e.printStackTrace();
          }


          //// If the lobby has not reached the maximum capicaty of players /////
        } else if (clients.size() < playersChosen) {
          clients.add(arg1); // add client object to game list
          clientNames.add(arg1.getName()); // add client name to game list

          // Set the number of Opponents for Client GUI
          try {
            arg1.sendToClient(playersChosen - 1);
          } catch (IOException e1) {
            e1.printStackTrace();
          }

          // Tell the client to continue to GamePanel
          try {
            arg1.sendToClient("Join Lobby");
          } catch (IOException e) {
            e.printStackTrace();
          }

          // sends all clients, the clients in game for scoreboard
          if (clients.size() != 1)
            sendToGameClients(clientNames);

        } else {
          try {
            arg1.sendToClient("Join Lobby Redirect");
          } catch (IOException e) {

            e.printStackTrace();
          }
        }
        if (clients.size() == playersChosen && playersChosen != 0) {
          log.append("Game Start\n");
          sendToGameClients(clientNames);
          turns[0] = 1;

          tellToGo();
        }
      }

      else if (command.startsWith("P")) {
        clients.add(arg1); // add client object to game list
        clientNames.add(arg1.getName()); // add client name to game list

        playersChosen = Integer.parseInt(Character.toString(command.charAt(1)));
        turns = new Integer[playersChosen];
        currScores = new Integer[playersChosen];
        Arrays.fill(turns, 0);

        // Set the number of Opponents for Client GUI
        try {
          arg1.sendToClient(playersChosen - 1);
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        // Tell the client to continue to GamePanel
        try {
          arg1.sendToClient("Join Lobby");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (command.equals("Get Leaderboard Stats")) {

        String query = "SELECT userName FROM TheUser";
        ArrayList<String> usernameData = new ArrayList<String>();
        usernameData = db.query(query);

        query = "SELECT userHighscore FROM TheUser";
        ArrayList<String> highscoreData = new ArrayList<String>();
        highscoreData = db.query(query);

        String[][] scoreboardData = new String[highscoreData.size()][2];

        for (int i = 0; i < highscoreData.size(); i++) {
          scoreboardData[i][0] = usernameData.get(i);
          scoreboardData[i][1] = highscoreData.get(i);
        }

        Arrays.sort(scoreboardData, new Comparator<String[]>() {

          @Override
          public int compare(final String[] first, final String[] second) {
            // here you should usually check that first and second
            // a) are not null and b) have at least two items
            // updated after comments: comparing Double, not Strings
            // makes more sense, thanks Bart Kiers
            return Double.valueOf(second[1]).compareTo(Double.valueOf(first[1]));
          }
        });



        try {
          arg1.sendToClient(scoreboardData);
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }



  private void gameover() {
    for (int i = 0; i < clientNames.size(); i++) {
      String query =
          "SELECT userHighscore FROM TheUser WHERE userName='" + clientNames.get(i) + "' ;";
      ArrayList<String> queryData = new ArrayList<String>();
      queryData = db.query(query);
      int highscore = Integer.valueOf(queryData.get(0));

      if (highscore < currScores[i]) {
        query = "UPDATE TheUser SET userHighscore= '" + currScores[i] + "' WHERE userName='"
            + clientNames.get(i) + "' ;";
        try {
          db.executeDML(query);
        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      System.out.println();
    }
    roundCounter = 0;
    sendToGameClients("Game Over");
    playersChosen = 0;
    clients.clear();
    clientNames.clear();



  }

  // Tells whoever's turn it is to go to Go
  public void tellToGo() {

    for (int i = 0; i < playersChosen; i++) {

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

    for (int i = 0; i < clients.size(); i++) {

      if (turns[i] == 1) {
        turns[i] = 0;

        if (i == clients.size() - 1) {
          turns[0] = 1;

          roundCounter++;
          System.out.println("RC: " + roundCounter);

        } else {
          turns[i + 1] = 1;
        }
        break;
      }
    }
  }

  // Sends to all clients who are currently in the game (sendToAllClients would include people who
  // haven't selected play game)
  public void sendToGameClients(Object obj) {

    for (int i = 0; i < clients.size(); i++) {
      try {
        clients.get(i).sendToClient(obj);
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
