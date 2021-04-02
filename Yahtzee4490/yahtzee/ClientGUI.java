package yahtzee;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
    MainMenuControl mmc = new MainMenuControl(container);

    // ContactPanel cp = new ContactPanel(container, client);



    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    JPanel view4 = new MainMenuPanel(mmc);


    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view4, "4");

    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);



    // Show the initial view in the card layout.
    cardLayout.show(container, "1");

    // Add the card layout container to the JFrame.
    this.add(container, BorderLayout.CENTER);

    // Show the JFrame.
    this.setSize(800, 600);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args) {
    new ClientGUI();
  }
}
