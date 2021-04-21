package yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderboardPanel extends JPanel {

  public LeaderboardPanel(LeaderboardControl lbc) {

    // Style Constants

    Font titleFont = new Font("Arial", Font.BOLD, 98);
    Font labelFont = new Font("Arial", Font.PLAIN, 45);
    Font lbFont = new Font("Arial", Font.PLAIN, 32);
    Dimension size = new Dimension(200, 50);

    JPanel layoutPanel = new JPanel(new BorderLayout(20, 20));



    JButton name;
    JButton score;
    JLabel title = new JLabel("Leaderboard");
    JButton returnButton;

    JLabel labels = new JLabel("User        Score");
    labels.setFont(labelFont);
    labels.setForeground(Color.white);

    title.setFont(titleFont);
    title.setForeground(Color.white);

    GridBagConstraints c = new GridBagConstraints();
    JPanel lbScores = new JPanel(new GridBagLayout());
    JPanel lbPanel = new JPanel();

    lbPanel.setPreferredSize(new Dimension(650, 700));
    lbScores.setBackground(Color.GRAY);


    returnButton = new JButton("Return to Main Menu");
    returnButton.addActionListener(lbc);
    returnButton.setPreferredSize(new Dimension(300, 50));
    returnButton.setFont(lbFont);



    for (int i = 0; i < 6; i++) {
      c.gridx = 0;
      c.gridy = i;
      c.insets = new Insets(10, 40, 10, 40);
      name = new JButton(String.valueOf(i));
      name.setPreferredSize(size);
      name.setFont(lbFont);
      lbScores.add(name, c);

      c.gridx = 1;

      score = new JButton(String.valueOf(i * 10));
      score.setPreferredSize(size);
      score.setFont(lbFont);
      lbScores.add(score, c);
    }


    // Yahtzee logo
    JLabel logo1 = new JLabel("");
    JLabel logo2 = new JLabel("");
    ImageIcon LogoIcon = new ImageIcon(this.getClass().getResource("/yahtzee/images/logo2.png"));
    logo1.setIcon(LogoIcon);
    logo2.setIcon(LogoIcon);

    lbPanel.add(title);
    lbPanel.add(labels);
    lbPanel.add(lbScores);


    // layoutPanel.add(title, BorderLayout.PAGE_START);
    layoutPanel.add(logo1, BorderLayout.LINE_START);
    layoutPanel.add(lbPanel, BorderLayout.CENTER);
    layoutPanel.add(logo2, BorderLayout.LINE_END);
    layoutPanel.add(returnButton, BorderLayout.PAGE_END);
    this.add(layoutPanel);



  }
}
