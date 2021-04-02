package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class InitialControl implements ActionListener {
  private JPanel container;

  public InitialControl(JPanel container) {
    this.container = container;

  }

  // Handle button clicks.
  public void actionPerformed(ActionEvent ae) {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Login button takes the user to the login panel.
    if (command.equals("Login")) {
      LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
      loginPanel.setError("");
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "2");
    }

    // The Create button takes the user to the create account panel.
    else if (command.equals("Create Account")) {
      CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
      createAccountPanel.setError("");
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "3");
    }
  }
}
