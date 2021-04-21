package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;

public class MainMenuControl implements ActionListener {

  private JPanel container;
  private ChatClient client;


  public MainMenuControl(JPanel container, ChatClient client) {
    this.container = container;
    this.client = client;
  }

  public void actionPerformed(ActionEvent ae) {
    String command = ae.getActionCommand();

    if (command == "New Game") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "5");

      try {
        client.sendToServer("New");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (command == "Join Game") {


    } else if (command == "Leaderboard") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "6");
    } else if (command == "Log Out") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "2");

    }

  }

  public void setLobby() {
    // TODO Auto-generated method stub

  }
}
