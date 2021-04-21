package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LeaderboardControl implements ActionListener {

  private JPanel container;


  public LeaderboardControl(JPanel container) {
    this.container = container;
  }


  @Override
  public void actionPerformed(ActionEvent ae) {
    String command = ae.getActionCommand();

    if (command == "Return to Main Menu") {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "4");

    }
  }

}
