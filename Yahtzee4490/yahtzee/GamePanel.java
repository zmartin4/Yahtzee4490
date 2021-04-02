package yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

  public GamePanel(GameControl gc) {
    // Style Constants

    Color color = new Color(215, 45, 53);
    Font font = new Font("Arial", Font.PLAIN, 20);
    super.setFont(font);
    JPanel layoutPanel = new JPanel(new BorderLayout(5, 5));
    JPanel scoreboardPanel = new JPanel(new GridLayout(1, 3, 5, 5));
    JPanel categoriesPanel = new JPanel(new GridLayout(17, 1, 0, 0));
    JPanel userScorePanel = new JPanel(new GridLayout(17, 1, 0, 0));
    JPanel oppScorePanel = new JPanel(new GridLayout(17, 1, 0, 0));
    JPanel dicePanel = new JPanel();


    JLabel oneLabel = new JLabel("Ones");
    categoriesPanel.add(oneLabel);
    JLabel twoLabel = new JLabel("Twos");
    categoriesPanel.add(twoLabel);

    // this.setFont(font);

    categoriesPanel.setBackground(color);
    oppScorePanel.setBackground(color);
    scoreboardPanel.setBackground(color.green);
    dicePanel.setBackground(color.magenta);
    scoreboardPanel.add(categoriesPanel);
    scoreboardPanel.add(userScorePanel);
    scoreboardPanel.add(oppScorePanel);


    layoutPanel.add(scoreboardPanel, BorderLayout.LINE_START);
    this.setBackground(color.blue);
    this.add(layoutPanel);


  }
}
