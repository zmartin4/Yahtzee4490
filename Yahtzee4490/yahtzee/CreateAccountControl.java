package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener {

  private JPanel container;
  private ChatClient client;


  // Constructor for the login controller.
  public CreateAccountControl(JPanel container, ChatClient client) {
    this.container = container;
    this.client = client;
  }

  // Handle button clicks.
  public void actionPerformed(ActionEvent ae) {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Cancel button takes the user back to the initial panel.
    if (command == "Cancel") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "1");
    }

    // The Submit button submits the login information to the server.
    else if (command == "Submit") {
      // Get the username and password the user entered.
      CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
      CreateAccountData data = new CreateAccountData(createAccountPanel.getUsername(),
          createAccountPanel.getPassword(), createAccountPanel.getVerifyPassword());

      // Check the validity of the information locally first.
      if (data.getUsername().equals("") || data.getPassword().equals("")
          || data.getVerifyPassword().equals("")) {
        displayError("You must enter a username and password.");
        return;
      } else if (!data.getPassword().equals(data.getVerifyPassword())) {
        displayError("Both Passwords must be the same.");
        return;
      } else if (data.getPassword().length() < 5) {
        displayError("Password must be at least 6 characters ");
        return;
      }
      // Submit the login information to the server.
      try {
        client.sendToServer(data);
      } catch (EOFException e) {
        e.printStackTrace();

      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }

  // After the login is successful, set the User object and display the contacts screen. - this
  // method would be invoked by
  // the ChatClient
  public void createAccountSuccess() {
    CardLayout cardLayout = (CardLayout) container.getLayout();
    cardLayout.show(container, "2");
  }



  // Method that displays a message in the error - could be invoked by ChatClient or by this class
  // (see above)
  public void displayError(String error) {
    CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
    createAccountPanel.setError(error);

  }
}
