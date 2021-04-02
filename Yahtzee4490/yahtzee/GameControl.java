package yahtzee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GameControl implements ActionListener {
  private JPanel container;
  private ChatClient client;


  // Constructor for the login controller.
  public GameControl(JPanel container, ChatClient client) {
    this.container = container;
    this.client = client;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {


  }

}
