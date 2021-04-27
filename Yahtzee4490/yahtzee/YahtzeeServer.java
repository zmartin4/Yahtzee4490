package yahtzee;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class YahtzeeServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;

  private Database db = new Database();



  private GameData gameData;
  private ArrayList<ConnectionToClient> clients; // CtC objects of clients that are in game
  private ArrayList<String> loggedInClients; // Names of Clients that are logged in
  private ArrayList<String> clientNames; // Names of Clients that are in game

  Integer[] turns; // 0 = Not Their Turn, 1 = Their Turn, -1 = No longer in lobby
  Integer[] currScores; // Running total for each client
  int playersChosen = 0;
  int roundCounter = 0;



  public YahtzeeServer() {
    super(12345);
    clients = new ArrayList<ConnectionToClient>();
    loggedInClients = new ArrayList<String>();
    clientNames = new ArrayList<String>();
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

  public GameData getServerGameData() {
    return gameData;
  }

  public void setServerGameData(GameData gameData) {
    this.gameData = gameData;
  }

  // Validates Logged In ArrayList for Hard Quits From MainMenu
  private void revalidateLogins() {
    Thread[] inServer = this.getClientConnections();
    ArrayList<String> toRemove = new ArrayList<String>();
    Boolean print = false;


    for (String lic : loggedInClients) {
      Boolean isConnected = false;

      for (Thread c : inServer) {
        if (lic.contains(c.getName()))
          isConnected = true;
      }
      if (!isConnected)
        toRemove.add(lic);
    }

    for (String remove : toRemove) {
      loggedInClients.remove(remove);
      print = false;
    }

    if (print) {
      for (String lic : loggedInClients)
        System.out.println(lic);
    }
  }

  // Validates Arraylist for Hard Quits from Game Lobby
  private void revalidateGamePlayers() {

    ArrayList<ConnectionToClient> toRemove = new ArrayList<ConnectionToClient>();

    for (ConnectionToClient d : clients) {
      Boolean isConnected = false;
      for (String lic : loggedInClients) {
        if (d.getName().equals(lic))
          isConnected = true;
      }
      if (!isConnected)
        toRemove.add(d);
    }

    for (ConnectionToClient remove : toRemove) {
      clients.remove(remove);

    }
  }

  @Override
  protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {

    revalidateLogins();
    revalidateGamePlayers();
    if (clients.size() == 0) {
      gameover(false);
    }


    for (String c : clientNames)
      System.out.println("==== " + c);


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
        if (loggedInClients.contains(loginData.getUsername())) {
          try {
            arg1.sendToClient("ALI");
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          try {
            arg1.sendToClient("LS");
            try {
              arg1.sendToClient("uName," + loginData.getUsername()); // Sets username in ChatClient
              arg1.setName(loginData.getUsername()); // Sets the ThreadName to the Username entered
              loggedInClients.add(arg1.getName());

            } catch (IOException e) {
              e.printStackTrace();
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
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

      if (clients.size() == 1) {
        sendToGameClients("Lobby Size Error");
        playersChosen = 0;
        return;
      }

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
          gameover(true);
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
          message = "New Lobby Redirect"; // Redirect to join lobby - playersChosen is set

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

          try {
            arg1.sendToClient(playersChosen - 1); // Set the number of Opponents for Client GUI
            arg1.sendToClient("Join Lobby"); // Tell the client to continue to GamePanel
          } catch (IOException e1) {
            e1.printStackTrace();
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


        /// \\\ Sets the number of clients internally ///\\\
      } else if (command.startsWith("P")) {
        clients.add(arg1); // add client object to game list
        clientNames.add(arg1.getName()); // add client name to game list

        playersChosen = Integer.parseInt(Character.toString(command.charAt(1)));
        turns = new Integer[playersChosen];
        currScores = new Integer[playersChosen];
        Arrays.fill(turns, 0);


        /// \\\ Tell the client to continue to GamePanel and sets number of Opponents ///\\\
        try {
          arg1.sendToClient(playersChosen - 1);
          arg1.sendToClient("Join Lobby");
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        //// \\\ Gets and parses leaderboard stats from database ///\\\
      } else if (command.equals("Get Leaderboard Stats")) {

        String query = "SELECT userName FROM TheUser";
        ArrayList<String> usernameData = new ArrayList<String>();
        usernameData = db.query(query);

        query = "SELECT userHighscore FROM TheUser";
        ArrayList<String> highscoreData = new ArrayList<String>();
        highscoreData = db.query(query);

        String[][] scoreboardData = new String[highscoreData.size()][2];
        // Arrays.fill(scoreboardData[0], "");
        // Arrays.fill(scoreboardData[1], "");

        for (int i = 0; i < highscoreData.size(); i++) {
          if (usernameData.get(i).equals("TestHighscore"))
            continue;
          scoreboardData[i][0] = usernameData.get(i);
          scoreboardData[i][1] = highscoreData.get(i);

        }
        /*
         * Arrays.sort(scoreboardData, new Comparator<String[]>() {
         * 
         * @Override public int compare(final String[] first, final String[] second) { return
         * Double.valueOf(second[1]).compareTo(Double.valueOf(first[1])); } });
         */
        try {
          arg1.sendToClient(scoreboardData);
        } catch (IOException e) {
          e.printStackTrace();
        }

        /// \\\ If a client quits, it adpats game aspects ///\\\
      } else if (command.equals("Quit Game")) {
        System.out.println("STOP");
        for (int i = 0; i < clients.size(); i++) {
          if (clients.get(i).getName().equals(arg1.getName())) {
            turns[i] = -1;
            break;
          }
        }

        clients.remove(arg1);
        clientNames.remove(arg1.getName());
        if (clients.size() <= 1) {
          gameover(false);
          sendToGameClients("Lobby Size Error");
        }
      }
    }
    revalidateLogins();
  }


  // Resets all of the game elements and if true, logs into database
  private void gameover(boolean logScore) {
    if (logScore) {
      for (int i = 0; i < clientNames.size(); i++) {
        if (turns[i] == -1)
          continue;
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
    }
    roundCounter = 0;
    sendToGameClients("Game Over");
    playersChosen = 0;
    clients.clear();
    clientNames.clear();



  }

  // Tells whoever's turn it is to go to Go
  public void tellToGo() {

    for (int i = 0; i < clients.size(); i++) {

      if (turns[i] == 1) {
        try {
          clients.get(i).sendToClient("Go");
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else if (turns[i] == 0) {

        try {
          clients.get(i).sendToClient("Wait");
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }

  // Increments whose turn it is.
  // 0 = Not Their Turn, 1 = Their Turn, -1 = No longer in lobby
  public void nextTurn() {

    for (int i = 0; i < clients.size(); i++) {

      if (turns[i] == 1) {
        turns[i] = 0;

        if (i == clients.size() - 1) {
          for (int j = 0; j < clients.size(); j++) {
            if (turns[j] != -1) {
              turns[j] = 1;
              break;
            }
          }

          roundCounter++;
          System.out.println("RC: " + roundCounter);

        } else {

          for (int j = i; j < clients.size(); j++) { // Skips clients with -1
            if (turns[j + 1] != -1) {
              turns[j + 1] = 1;
              break;
            }
          }

          Boolean noNext = false;
          for (int k = 0; k < turns.length; k++) { // Checks if there is 1 in any space
            if (turns[k] == 1) {
              noNext = true;
              break;
            }
          }

          if (!noNext) {
            noNext = false;
            for (int m = 0; m < i; m++) { // If there isn't a 1, loop back around to front
              if (turns[m] != -1) {
                turns[m] = 1;
                noNext = true;
                roundCounter++;
                System.out.println("RC: " + roundCounter);
                break;
              }
            }
          }
          if (!noNext) // if there STILL isn't a 1
            gameover(false);
        }
        break;
      }
    }

  }

  // Sends to all clients who are currently in the game
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
    log.append("\nClient Connected");

  }



}
