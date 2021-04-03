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
      System.out.println("ChatClient - Login");
      lc.loginSuccess();
    } else if (arg0.equals("LF")) {
      System.out.println("ChatClient - Login Failed");
      lc.displayError("Incorrect Username or Password");
    }


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
