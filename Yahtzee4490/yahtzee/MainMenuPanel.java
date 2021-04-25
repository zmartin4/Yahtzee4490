package yahtzee;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
  JPanel layoutPanel = new JPanel(new BorderLayout(50, 50)); // Overall Layout

  public MainMenuPanel(MainMenuControl mmc) {

    // Style Constants
    Color color = new Color(215, 45, 53);
    Font buttonFont = new Font("Arial", Font.PLAIN, 32);
    Font logoutFont = new Font("Arial", Font.PLAIN, 24);

    // Layout Initialization
    JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 50, 50)); // Contains Buttons
    JPanel logoutPanel = new JPanel(new FlowLayout()); // Contains logout Button
    JLabel bufferPanel = new JLabel(); // Spacing for GUI
    bufferPanel.setPreferredSize(new Dimension(0, 10));

    // New Game Button
    JButton newGameButton = new JButton("New Game");
    newGameButton.setFont(buttonFont);
    newGameButton.setPreferredSize(new Dimension(250, 75));
    newGameButton.addActionListener(mmc);
    JPanel newGameButtonBuffer = new JPanel();
    newGameButtonBuffer.add(newGameButton);
    buttonPanel.add(newGameButtonBuffer);

    // Join Game Button
    JButton joinGameButton = new JButton("Join Game");
    joinGameButton.setFont(buttonFont);
    joinGameButton.setPreferredSize(new Dimension(250, 75));
    joinGameButton.addActionListener(mmc);
    JPanel joinGameButtonBuffer = new JPanel();
    joinGameButtonBuffer.add(joinGameButton);
    buttonPanel.add(joinGameButtonBuffer);

    // Leaderboard Button
    JButton leaderboardButton = new JButton("Leaderboard");
    leaderboardButton.setFont(buttonFont);
    leaderboardButton.setPreferredSize(new Dimension(250, 75));
    leaderboardButton.addActionListener(mmc);
    JPanel leaderboardButtonBuffer = new JPanel();
    leaderboardButtonBuffer.add(leaderboardButton);
    buttonPanel.add(leaderboardButtonBuffer);

    // Log Out Button
    JButton logoutButton = new JButton("Log Out");
    logoutButton.setFont(logoutFont);
    logoutButton.setPreferredSize(new Dimension(150, 40));
    logoutButton.addActionListener(mmc);
    logoutPanel.add(logoutButton);

    // Yahtzee logo
    JLabel logo1 = new JLabel("");
    JLabel logo2 = new JLabel("");
    ImageIcon LogoIcon = new ImageIcon(this.getClass().getResource("/yahtzee/images/logo2.png"));
    logo1.setIcon(LogoIcon);
    logo2.setIcon(LogoIcon);



    // add items to layouts
    layoutPanel.add(bufferPanel, BorderLayout.PAGE_START);
    layoutPanel.add(logo1, BorderLayout.LINE_START);
    layoutPanel.add(buttonPanel, BorderLayout.CENTER);
    layoutPanel.add(logo2, BorderLayout.LINE_END);
    layoutPanel.add(logoutPanel, BorderLayout.SOUTH);
    this.add(layoutPanel);

  }

  public String createLobby() {

    String[] possibleValues = {"2", "3", "4"};
    String selectedValue = null;

    while (selectedValue == null) {
      selectedValue = (String) JOptionPane.showInputDialog(null, "Choose one", "Input",
          JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
    }

    return selectedValue;
  }

  public void redirect(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }
}
