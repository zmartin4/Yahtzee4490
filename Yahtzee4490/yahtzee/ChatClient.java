package yahtzee;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

  LoginControl lc;
  CreateAccountControl cac;


  public ChatClient() {
    super("localhost", 8300);

  }

  @Override
  public void handleMessageFromServer(Object arg0) {

    if (arg0.equals("NAS")) {
      System.out.println("ChatClient - New Account");
      cac.createAccountSuccess();

    } else if (arg0.equals("LS")) {
      System.out.println("Client - Login");
      lc.loginSuccess();
    } else if (arg0.equals("LF")) {
      System.out.println("Client - Login Failed");
      lc.displayError("Incorrect Username or Password");
    }
    
    //You'll want some code here to help manage receiving gameData from the server.
    //For instance, when receiving gameData (such as the dice values) from a different client that rolled, update the client GUI
    //Your implementation of gameData doesn't keep the values from the other players so we can display (or doesn't seem to)
    //but we don't necessarily need that I guess


  }


  public void connectionException(Throwable exception) {
    // Add your code here
  }

  public void connectionEstablished() {
    // Add your code here
  }

  public void setLoginControl(LoginControl lc) {
    this.lc = lc;
  }

  public void setCreateAccountControl(CreateAccountControl cac) {
    this.cac = cac;
  }

}
