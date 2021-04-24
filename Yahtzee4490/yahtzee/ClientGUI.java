package yahtzee;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class ClientGUI extends JFrame {


  // Constructor that creates the client GUI.
  public ClientGUI() {
    ChatClient client = new ChatClient();

    client.setHost("localhost");
    client.setPort(8300);
    try {
      client.openConnection();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Set the title and default close operation.
    this.setTitle("Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    // Create the Controllers next
    // Next, create the Controllers
    InitialControl ic = new InitialControl(container);
    LoginControl lc = new LoginControl(container, client); // Probably will want to pass in
    CreateAccountControl cac = new CreateAccountControl(container, client); // ChatClient here
    MainMenuControl mmc = new MainMenuControl(container, client);
    GameControl gc = new GameControl(container, client);
    LeaderboardControl lbc = new LeaderboardControl(container);


    Color color = new Color(215, 45, 53);



    UIManager.getLookAndFeelDefaults().put("Panel.background", color);
    UIManager.getLookAndFeelDefaults().put("Button.background", Color.WHITE);
    UIManager.getLookAndFeelDefaults().put("OptionPane.background", color);
    UIManager.getLookAndFeelDefaults().put("OptionPane.messageForeground", Color.WHITE);
    UIManager.getLookAndFeelDefaults().put("ComboBox.background", Color.WHITE);
    UIManager.getLookAndFeelDefaults().put("ComboBox.selectionBackground", Color.WHITE);

    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    JPanel view4 = new MainMenuPanel(mmc);
    JPanel view5 = new GamePanel(gc);
    JPanel view6 = new LeaderboardPanel(lbc);


    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view4, "4");
    container.add(view5, "5");
    container.add(view6, "6");

    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);
    client.setGameControl(gc);
    client.setMainMenuControl(mmc);
    client.setLeaderboardControl(lbc);



    // Show the initial view in the card layout.
    cardLayout.show(container, "1");

    // Add the card layout container to the JFrame.
    // this.add(container, BorderLayout.LINE_START);
    this.add(container, FlowLayout.LEFT);



    // Show the JFrame.
    this.setSize(1200, 850);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args) {
    new ClientGUI();
  }
}
