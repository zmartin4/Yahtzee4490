package yahtzee;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;
  
  //Will be red until Database is implemented
  private Database db = new Database();
  
  private GameData gameData;
  
  //Updated as the players make their turns
  private Integer[] highscores = {0,0,0,0,0};
  
  private ArrayList<ConnectionToClient> clients;
  
  //0 if not going
  private int[] turns = {0,0,0,0,0};

  public ChatServer() {
    super(12345);
    clients = new ArrayList<ConnectionToClient>();
    
    gameData = new GameData();
  }

  public ChatServer(int port) {
    super(port);
    clients = new ArrayList<ConnectionToClient>();
    
    gameData = new GameData();
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
  
  //Increments whose turn it is. Wraps around if the fifth client is currently going
  public void nextTurn() {
	  for(int i = 0; i < 5; i++) {
		  if(turns[i] == 1) {
			  turns[i] = 0;
			  
			  if(i == 4) {
				  turns[0] = 1;
			  }else {
				  turns[i + 1] = 1;
			  }
		  }
	  }
  }
  
  public static int findSum(Integer[] array) {
	    return Arrays.stream(array)
	      .mapToInt(Integer::intValue)
	      .sum();
	}
  
  //Tells whoever's turn it is to go to Go
  public void tellToGo() {
	  for(int i = 0; i < 5; i++) {
		  if(turns[i] == 1) {
			  try {
				  clients.get(i).sendToClient("Go");
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
			  
		  }else {
			  
			  try {
				  clients.get(i).sendToClient("Stop");
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
			  
		  }
	  }
  }
  
  public void sendToGameClients(Object obj) {
	  for(int i = 0; i < clients.size(); i++) {
		  try {
	          clients.get(i).sendToClient(obj);
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
		  
	  }
  }



  @Override
  protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
    // TODO Auto-generated method stub
    System.out.println("Message from Client" + arg0.toString() + " ||||  " + arg1.toString());

    
    //Change all this when the database is implemented
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
      
      
      //Should I be doing more here?
      if (arg0 instanceof GameData) {
    	  
    	  GameData newGameData = (GameData)arg0;
    	  System.out.println("Instance of gamedata");
    	  
    	  //Spread gamedata specifics to all clients
    	  this.gameData = newGameData;
    	  
    	  //Update specific highscores
    	  highscores[clients.indexOf(arg1)] += findSum(newGameData.getPlayerScore());
    	  
    	  sendToGameClients(gameData);
    	  
    	  //Somewhere we may need to keep the high score for a specific user updated
    	  
    	 
    	  nextTurn();
    	  tellToGo();
      }
      
      if (arg0 instanceof String) {
    	  
    	  String message = (String)arg0;
    	  
    	  if(message == "Game End") {
    		  //Sample -- would send to all clients if the game ended
    		  sendToGameClients("Game End");
    		  
    		  //We would have some method here for setting the highscore in the database for a client/user on game end
    		  //ONLY if game ended normally
    		  //db.setHighScore(highscores[0], clients.get(0)); <-- sample
    		  
    		  //Then closes connections with all clients and clears them
    		  try {
    			  clients.get(0).close();
        		  clients.get(1).close();
        		  clients.get(2).close();
        		  clients.get(3).close();
        		  clients.get(4).close();
    	        } catch (IOException e) {
    	          e.printStackTrace();
    	        }
    		  clients.clear();
    	  }
    	  
    	  if(message == "Play Game") {
    		  //If the player selects play game from the main menu, then
    		  //As the players get logged in and select play game, add them to the clients and tell them to go when enough players have joined
    	        if(clients.size() < 5) {
    	        	clients.add(client);
    	        }else if(clients.size() == 5) {
    	        	log.append("Game Start\n");
    	        	
    	        	//Indicator that it is first player's turn
    	        	turns[0] == 1;
    	        	try {
    	        		tellToGo();
    	              } catch (IOException e) {
    	                e.printStackTrace();
    	              }
    	        }
    		  
    		  
    	  }
      }



    }

  }



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
    
    
  }



}
