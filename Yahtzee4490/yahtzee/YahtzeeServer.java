package yahtzee;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class YahtzeeServer extends AbstractServer {
  private JTextArea log;
  private JLabel status;
  //private Database db = new Database();
  //I actually have the solution for the Database lab, if you need it
  
  
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
    
    if(arg0 instanceof GameData) {
    	
    	GameData gameData = (GameData)arg0;
    	
    	//if(gameData.)
    	//^ Not sure what to do with gameData but I'll implement more later
    	
    	
    }
    
    //if(arg0 instanceof JoinServerData)
    //^ We might not be doing JoinServer

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
