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
  GridBagConstraints c = new GridBagConstraints();
  JPanel lbScores = new JPanel(new GridBagLayout());
  JPanel lbPanel = new JPanel();
  JButton[] username = new JButton[6];
  JButton[] score = new JButton[6];
  Font labelFont = new Font("Arial", Font.PLAIN, 45);
  Font lbFont = new Font("Arial", Font.PLAIN, 32);
  Dimension size = new Dimension(200, 50);

  public LeaderboardPanel(LeaderboardControl lbc) {

    // Style Constants

    Font titleFont = new Font("Arial", Font.BOLD, 98);


    JPanel layoutPanel = new JPanel(new BorderLayout(20, 20));



    JLabel title = new JLabel("Leaderboard");
    JButton returnButton;

    JLabel labels = new JLabel("User        Score");
    labels.setFont(labelFont);
    labels.setForeground(Color.white);

    title.setFont(titleFont);
    title.setForeground(Color.white);


    lbPanel.setPreferredSize(new Dimension(650, 700));
    lbScores.setBackground(Color.GRAY);


    returnButton = new JButton("Return to Main Menu");
    returnButton.addActionListener(lbc);
    returnButton.setPreferredSize(new Dimension(300, 50));
    returnButton.setFont(lbFont);



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

  public void showLeaderboardData(String[][] leaderboardData) {

    for (int i = 0; i < 6; i++) {
      System.out.println(leaderboardData[i][0] + " " + leaderboardData[i][1]);
      c.gridx = 0;
      c.gridy = i;
      c.insets = new Insets(10, 40, 10, 40);
      username[i] = new JButton();
      username[i].setText(leaderboardData[i][0]);
      username[i].setPreferredSize(size);
      username[i].setFont(lbFont);
      lbScores.add(username[i], c);

      c.gridx = 1;
      score[i] = new JButton();
      score[i].setText(leaderboardData[i][1]);
      score[i].setPreferredSize(size);
      score[i].setFont(lbFont);
      lbScores.add(score[i], c);
    }
  }
}
