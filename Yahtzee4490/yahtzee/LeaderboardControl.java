package yahtzee;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LeaderboardControl implements ActionListener {

  private JPanel container;
  private String[][] leaderboardData;

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

  public void setLeaderboardData(Object arg0) {
    leaderboardData = (String[][]) arg0;

    LeaderboardPanel leaderboardPanel = (LeaderboardPanel) container.getComponent(5);
    leaderboardPanel.showLeaderboardData(leaderboardData);

    CardLayout cardLayout = (CardLayout) container.getLayout();
    cardLayout.show(container, "6");



  }

}
