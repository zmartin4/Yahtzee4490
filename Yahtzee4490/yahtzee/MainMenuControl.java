package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MainMenuControl implements ActionListener {

  private JPanel container;


  public MainMenuControl(JPanel container) {
    this.container = container;
  }

  public void actionPerformed(ActionEvent ae) {
    String command = ae.getActionCommand();

    if (command == "New Game") {

    } else if (command == "Join Game") {

    } else if (command == "Leaderboard") {

    } else if (command == "Log Out") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "2");

    }

  }
}
