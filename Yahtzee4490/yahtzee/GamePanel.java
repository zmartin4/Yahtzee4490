package yahtzee;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

  public GamePanel(GameControl gc) {
    // Style Constants
    Font catFont = new Font("Arial", Font.PLAIN, 20);
    Color color = new Color(215, 45, 53);
    Font font = new Font("Arial", Font.PLAIN, 20);


    JPanel scoreboardPanel = new JPanel(new GridLayout(1, 3, 10, 5));
    JPanel categoriesPanel = new JPanel(new GridLayout(6, 0, 10, 5));
    JPanel userScorePanel = new JPanel(new GridLayout(17, 1, 0, 0));
    JPanel oppScorePanel = new JPanel(new GridLayout(17, 1, 0, 0));


    JPanel dicePanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JButton a = new JButton("yuh");
    JButton b = new JButton("yeet");

    JPanel layoutPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    // this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);



    // For a nicer looking scoreboard

    // JButton one = new JButton("Ones");
    // one.setBorderPainted(false);
    // one.setFocusPainted(false);
    // one.setBackground(color);
    // one.setForeground(color.WHITE);
    // categoriesPanel.add(one);

    JLabel oneLabel = new JLabel("Ones");
    oneLabel.setFont(catFont);
    oneLabel.setForeground(color.WHITE);
    categoriesPanel.add(oneLabel);
    JLabel twoLabel = new JLabel("Twos");
    categoriesPanel.add(twoLabel);
    JLabel threeLabel = new JLabel("Threes");
    categoriesPanel.add(threeLabel);
    JLabel fourLabel = new JLabel("Fours");
    categoriesPanel.add(fourLabel);
    JLabel fiveLabel = new JLabel("Fives");
    categoriesPanel.add(fiveLabel);
    JLabel sixLabel = new JLabel("Sixes");
    categoriesPanel.add(sixLabel);

    scoreboardPanel.setBackground(color.WHITE);
    userScorePanel.setBackground(color.DARK_GRAY);
    oppScorePanel.setBackground(color.orange);
    leftPanel.setBackground(color.black);


    scoreboardPanel.add(categoriesPanel);
    scoreboardPanel.add(userScorePanel);
    scoreboardPanel.add(oppScorePanel);

    // natural height, maximum width



    JButton button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    // layoutPanel.add(button, c);
    this.add(button, FlowLayout.LEFT);
    button = new JButton("Button 3");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    // layoutPanel.add(button, c);
    this.add(button, FlowLayout.LEFT);



    // layoutPanel.add(a);
    // layoutPanel.add(b);
    // layoutPanel.add(new JSeparator(), BorderLayout.CENTER);
    // layoutPanel.add(a, BorderLayout.CENTER);
    // layoutPanel.add(b, BorderLayout.PAGE_END);
    // this.add(button, c);
    this.setBackground(color.blue);
    // this.add(layoutPanel);


  }
}
