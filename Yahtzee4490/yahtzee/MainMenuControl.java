package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;

public class MainMenuControl implements ActionListener {

  private JPanel container;
  private ChatClient client;
  private String numPlayers;


  public MainMenuControl(JPanel container, ChatClient client) {
    this.container = container;
    this.client = client;
  }

  public void actionPerformed(ActionEvent ae) {
    String command = ae.getActionCommand();
    if (command == "New Game") {
      try {
        client.sendToServer("New Game");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (command == "Join Game") {
      try {
        client.sendToServer("Join Game");
      } catch (IOException e) {
        e.printStackTrace();
      }


    } else if (command == "Leaderboard") {
      try {
        client.sendToServer("Get Leaderboard Stats");
      } catch (IOException e) {
        e.printStackTrace();
      }
      // CardLayout cardLayout = (CardLayout) container.getLayout();
      // cardLayout.show(container, "6");
    } else if (command == "Log Out") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "2");

    }

  }

  // Get the number of players to set the game lobby to
  public void newLobby() {
    MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(3);
    numPlayers = mainMenuPanel.createLobby();
    try {
      client.sendToServer("P" + numPlayers);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void newLobbyRedirect() {
    MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(3);
    mainMenuPanel.redirect("The game lobby has already been set. Please Select Join Game");
  }

  // Allow a client to procede to the GamePanel
  public void joinLobby() {
    CardLayout cardLayout = (CardLayout) container.getLayout();
    cardLayout.show(container, "5");
  }

  public void joinLobbyRedirect() {
    MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(3);
    mainMenuPanel.redirect("The game lobby is currently full. Please wait until the game is over");
  }

  public void noLobbyRedirect() {
    MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(3);
    mainMenuPanel.redirect("The game lobby has not been created. Please Select New Game");
  }



}
