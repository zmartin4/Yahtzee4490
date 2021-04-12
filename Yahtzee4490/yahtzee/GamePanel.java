package yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;



public class GamePanel extends JPanel {
  private JButton dice1Keep = new JButton("1Keep");
  private JButton dice1Roll = new JButton("1Roll");
  private JButton dice2Keep = new JButton("2Keep");
  private JButton dice2Roll = new JButton("2Roll");
  private JButton dice3Keep = new JButton("3Keep");
  private JButton dice3Roll = new JButton("3Roll");
  private JButton dice4Keep = new JButton("4Keep");
  private JButton dice4Roll = new JButton("4Roll");
  private JButton dice5Keep = new JButton("5Keep");
  private JButton dice5Roll = new JButton("5Roll");


  ImageIcon face1 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_1.png"));
  ImageIcon face2 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_2.png"));
  ImageIcon face3 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_3.png"));
  ImageIcon face4 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_4.png"));
  ImageIcon face5 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_5.png"));
  ImageIcon face6 = new ImageIcon(this.getClass().getResource("/yahtzee/images/face_6.png"));
  ImageIcon blank = new ImageIcon(this.getClass().getResource("/yahtzee/images/board_color.png"));

  JButton[] buttons = new JButton[6];
  JButton oneUser = new JButton("");
  JButton twoUser = new JButton("");
  JButton threeUser = new JButton("");
  JButton fourUser = new JButton("");
  JButton fiveUser = new JButton("");
  JButton sixUser = new JButton("");


  JButton rollDice = new JButton("Roll Dice");


  JButton choose = new JButton("Pick Category");
  GridBagConstraints c = new GridBagConstraints();
  JPanel dp = new JPanel(new GridBagLayout());



  public void setDice1(boolean rollable, int value) {
    boolean isClickable = true;
    ImageIcon LogoIcon;
    String keepLogo = "/yahtzee/images/face_" + value + ".png";
    String rollLogo = "/yahtzee/images/board_color.png";

    if (rollable) {
      isClickable = false;
      keepLogo = "/yahtzee/images/board_color.png";
      rollLogo = "/yahtzee/images/face_" + value + ".png";
    }

    LogoIcon = new ImageIcon(this.getClass().getResource(keepLogo));
    dice1Keep.setIcon(LogoIcon);
    dice1Keep.setBorderPainted(isClickable);
    dice1Keep.setFocusPainted(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice1Roll.setIcon(LogoIcon);
    dice1Roll.setBorderPainted(!isClickable);
    dice1Roll.setFocusPainted(!isClickable);

  }

  public void setDice2(boolean rollable, int value) {
    boolean isClickable = true;
    ImageIcon LogoIcon;
    String keepLogo = "/yahtzee/images/face_" + value + ".png";
    String rollLogo = "/yahtzee/images/board_color.png";

    if (rollable) {
      isClickable = false;
      keepLogo = "/yahtzee/images/board_color.png";
      rollLogo = "/yahtzee/images/face_" + value + ".png";
    }

    LogoIcon = new ImageIcon(this.getClass().getResource(keepLogo));
    dice2Keep.setIcon(LogoIcon);
    dice2Keep.setBorderPainted(isClickable);
    dice2Keep.setFocusPainted(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice2Roll.setIcon(LogoIcon);
    dice2Roll.setBorderPainted(!isClickable);
    dice2Roll.setFocusPainted(!isClickable);

  }

  public void setDice3(boolean rollable, int value) {
    boolean isClickable = true;
    ImageIcon LogoIcon;
    String keepLogo = "/yahtzee/images/face_" + value + ".png";
    String rollLogo = "/yahtzee/images/board_color.png";

    if (rollable) {
      isClickable = false;
      keepLogo = "/yahtzee/images/board_color.png";
      rollLogo = "/yahtzee/images/face_" + value + ".png";
    }

    LogoIcon = new ImageIcon(this.getClass().getResource(keepLogo));
    dice3Keep.setIcon(LogoIcon);
    dice3Keep.setBorderPainted(isClickable);
    dice3Keep.setFocusPainted(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice3Roll.setIcon(LogoIcon);
    dice3Roll.setBorderPainted(!isClickable);
    dice3Roll.setFocusPainted(!isClickable);
  }

  public void setDice4(boolean rollable, int value) {
    boolean isClickable = true;
    ImageIcon LogoIcon;
    String keepLogo = "/yahtzee/images/face_" + value + ".png";
    String rollLogo = "/yahtzee/images/board_color.png";

    if (rollable) {
      isClickable = false;
      keepLogo = "/yahtzee/images/board_color.png";
      rollLogo = "/yahtzee/images/face_" + value + ".png";
    }

    LogoIcon = new ImageIcon(this.getClass().getResource(keepLogo));
    dice4Keep.setIcon(LogoIcon);
    dice4Keep.setBorderPainted(isClickable);
    dice4Keep.setFocusPainted(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice4Roll.setIcon(LogoIcon);
    dice4Roll.setBorderPainted(!isClickable);
    dice4Roll.setFocusPainted(!isClickable);
  }

  public void setDice5(boolean rollable, int value) {
    boolean isClickable = true;
    ImageIcon LogoIcon;
    String keepLogo = "/yahtzee/images/face_" + value + ".png";
    String rollLogo = "/yahtzee/images/board_color.png";

    if (rollable) {
      isClickable = false;
      keepLogo = "/yahtzee/images/board_color.png";
      rollLogo = "/yahtzee/images/face_" + value + ".png";
    }

    LogoIcon = new ImageIcon(this.getClass().getResource(keepLogo));
    dice5Keep.setIcon(LogoIcon);
    dice5Keep.setBorderPainted(isClickable);
    dice5Keep.setFocusPainted(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice5Roll.setIcon(LogoIcon);
    dice5Roll.setBorderPainted(!isClickable);
    dice5Roll.setFocusPainted(!isClickable);
  }


  public void setUserBArray() {
    buttons[0] = oneUser;
    buttons[1] = twoUser;
    buttons[2] = threeUser;
    buttons[3] = fourUser;
    buttons[4] = fiveUser;
    buttons[5] = sixUser;
  }


  // Enables choose button to be clicked after first roll
  public void enableSelection() {
    choose.setEnabled(true);
  }

  // Is called from GameControl and updates the GUI Scoreboard
  public void updateUserScoreboard(Integer[] userScore, int selection) {
    // oneUser.setText(String.valueOf(userScore[0]));

    for (int i = 0; i < 6; i++)
      buttons[i].setText(String.valueOf(userScore[i]));

    if (selection != -1)
      buttons[selection].setForeground(Color.red);
  }

  public GamePanel(GameControl gc) {
    // Style Constants
    Font catFont = new Font("Arial", Font.PLAIN, 20);
    Color color = new Color(215, 45, 53);
    Dimension diceSize = new Dimension(70, 70);
    setUserBArray();


    // Scoreboard Panels
    JPanel scoreboardPanel = new JPanel(new GridLayout(1, 3, 10, 5));
    JPanel categoriesPanel = new JPanel(new GridLayout(6, 0, 10, 5));
    JPanel userScorePanel = new JPanel(new GridLayout(6, 1, 0, 0));
    JPanel oppScorePanel = new JPanel(new GridLayout(17, 1, 0, 0));

    JPanel diceKeepPanel = new JPanel();
    JPanel diceRollPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel layoutPanel = new JPanel(new BorderLayout());


    //////////////////////////////////////
    /* Categories Labels for Scoreboard */
    //////////////////////////////////////
    JButton ones = new JButton("Ones");
    ones.setBorderPainted(false);
    ones.setFocusPainted(false);
    ones.setBackground(color);
    ones.setForeground(color.WHITE);
    categoriesPanel.add(ones);

    JButton twos = new JButton("Twos");
    twos.setBorderPainted(false);
    twos.setFocusPainted(false);
    twos.setBackground(color);
    twos.setForeground(color.WHITE);
    categoriesPanel.add(twos);

    JButton threes = new JButton("Threes");
    threes.setBorderPainted(false);
    threes.setFocusPainted(false);
    threes.setBackground(color);
    threes.setForeground(color.WHITE);
    categoriesPanel.add(threes);

    JButton fours = new JButton("Fours");
    fours.setBorderPainted(false);
    fours.setFocusPainted(false);
    fours.setBackground(color);
    fours.setForeground(color.WHITE);
    categoriesPanel.add(fours);

    JButton fives = new JButton("Fives");
    fives.setBorderPainted(false);
    fives.setFocusPainted(false);
    fives.setBackground(color);
    fives.setForeground(color.WHITE);
    categoriesPanel.add(fives);

    JButton sixes = new JButton("Sixes");
    sixes.setBorderPainted(false);
    sixes.setFocusPainted(false);
    sixes.setBackground(color);
    sixes.setForeground(color.WHITE);
    categoriesPanel.add(sixes);


    // User Scores for Scoreboard
    userScorePanel.add(oneUser);
    userScorePanel.add(twoUser);
    userScorePanel.add(threeUser);
    userScorePanel.add(fourUser);
    userScorePanel.add(fiveUser);
    userScorePanel.add(sixUser);



    //////////////////////////
    /* Buttons for the Dice */
    //////////////////////////
    dice1Keep.setPreferredSize(diceSize);
    dice1Keep.addActionListener(gc);
    dice1Keep.setBorderPainted(false);
    dice1Keep.setFocusPainted(false);
    dice1Keep.setIcon(blank);
    dice1Keep.setIconTextGap(-5);

    dice1Roll.setPreferredSize(diceSize);
    dice1Roll.addActionListener(gc);
    dice1Roll.setBorderPainted(false);
    dice1Roll.setFocusPainted(false);
    dice1Roll.setIconTextGap(-5);
    dice1Roll.setIcon(face1);


    dice2Keep.setPreferredSize(diceSize);
    dice2Keep.addActionListener(gc);
    dice2Keep.setBorderPainted(false);
    dice2Keep.setFocusPainted(false);
    dice2Keep.setIcon(blank);
    dice2Keep.setIconTextGap(-5);


    dice2Roll.setPreferredSize(diceSize);
    dice2Roll.addActionListener(gc);
    dice2Roll.setBorderPainted(false);
    dice2Roll.setFocusPainted(false);
    dice2Roll.setIcon(face1);
    dice2Roll.setIconTextGap(-5);

    dice3Keep.setPreferredSize(diceSize);
    dice3Keep.addActionListener(gc);
    dice3Keep.setBorderPainted(false);
    dice3Keep.setFocusPainted(false);
    dice3Keep.setIcon(blank);
    dice3Keep.setIconTextGap(-5);

    dice3Roll.setPreferredSize(diceSize);
    dice3Roll.addActionListener(gc);
    dice3Roll.setBorderPainted(false);
    dice3Roll.setFocusPainted(false);
    dice3Roll.setIcon(face1);
    dice3Roll.setIconTextGap(-5);

    dice4Keep.setPreferredSize(diceSize);
    dice4Keep.addActionListener(gc);
    dice4Keep.setBorderPainted(false);
    dice4Keep.setFocusPainted(false);
    dice4Keep.setIcon(blank);
    dice4Keep.setIconTextGap(-5);

    dice4Roll.setPreferredSize(diceSize);
    dice4Roll.addActionListener(gc);
    dice4Roll.setBorderPainted(false);
    dice4Roll.setFocusPainted(false);
    dice4Roll.setIcon(face1);
    dice4Roll.setIconTextGap(-5);

    dice5Keep.setPreferredSize(diceSize);
    dice5Keep.addActionListener(gc);
    dice5Keep.setBorderPainted(false);
    dice5Keep.setFocusPainted(false);
    dice5Keep.setIcon(blank);
    dice5Keep.setIconTextGap(-5);

    dice5Roll.setPreferredSize(diceSize);
    dice5Roll.addActionListener(gc);
    dice5Roll.setBorderPainted(false);
    dice5Roll.setFocusPainted(false);
    dice5Roll.setIcon(face1);
    dice5Roll.setIconTextGap(-5);


    ////////////////////////////////////////
    /* Sets Location for the Dice Buttons */
    ////////////////////////////////////////
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 0, 0, 0);
    dp.add(dice1Roll, c);
    c.gridx = 1;
    dp.add(dice2Roll, c);
    c.gridx = 2;
    dp.add(dice3Roll, c);
    c.gridx = 3;
    dp.add(dice4Roll, c);
    c.gridx = 4;
    dp.add(dice5Roll, c);

    c.gridx = 0;
    c.gridy = 1;
    dp.add(dice1Keep, c);
    c.gridx = 1;
    dp.add(dice2Keep, c);
    c.gridx = 2;
    dp.add(dice3Keep, c);
    c.gridx = 3;
    dp.add(dice4Keep, c);
    c.gridx = 4;
    dp.add(dice5Keep, c);

    rollDice = new JButton("Roll Dice");
    rollDice.addActionListener(gc);

    choose = new JButton("Pick Category");
    choose.setEnabled(false);
    choose.addActionListener(gc);


    String[] categorySelections = {"One", "Two", "Three", "Four", "Five", "Six"};
    JComboBox pick = new JComboBox(categorySelections);
    pick.addItemListener(gc);



    /*
     * JButton dice1Roll = new JButton("1"); dice1Roll.setPreferredSize(new Dimension(70, 70));
     * diceRollPanel.add(dice1Roll); JButton dice2Roll = new JButton("2");
     * dice2Roll.setPreferredSize(new Dimension(70, 70)); diceRollPanel.add(dice2Roll); JButton
     * dice3Roll = new JButton("3"); dice3Roll.setPreferredSize(new Dimension(70, 70));
     * diceRollPanel.add(dice3Roll); JButton dice4Roll = new JButton("4");
     * dice4Roll.setPreferredSize(new Dimension(70, 70)); diceRollPanel.add(dice4Roll); JButton
     * dice5Roll = new JButton("5"); dice5Roll.setPreferredSize(new Dimension(70, 70));
     * diceRollPanel.add(dice5Roll); JButton dice6Roll = new JButton("6");
     * dice6Roll.setPreferredSize(new Dimension(70, 70)); diceRollPanel.add(dice6Roll);
     */



    // for visual TESTING
    scoreboardPanel.setBackground(color.WHITE);
    userScorePanel.setBackground(color.DARK_GRAY);
    oppScorePanel.setBackground(color.orange);
    leftPanel.setBackground(color.black);

    // adding sections to the scoreboard
    scoreboardPanel.add(categoriesPanel);
    scoreboardPanel.add(userScorePanel);
    scoreboardPanel.add(oppScorePanel);

    // natural height, maximum width

    /*
     * GridBagConstraints c = new GridBagConstraints(); JButton button = new JButton("Button 2");
     * c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.5; c.gridx = 0; c.gridy = 0; //
     * layoutPanel.add(button, c); this.add(button, FlowLayout.LEFT); button = new
     * JButton("Button 3"); c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.5; c.gridx = 1;
     * c.gridy = 0; // layoutPanel.add(button, c); this.add(button, FlowLayout.LEFT);
     */

    layoutPanel.add(scoreboardPanel);
    // layoutPanel.add(a);
    // layoutPanel.add(b);
    // layoutPanel.add(new JSeparator(), BorderLayout.CENTER);
    // layoutPanel.add(a, BorderLayout.CENTER);
    // layoutPanel.add(b, BorderLayout.PAGE_END);
    // this.add(button, c);
    this.setBackground(color.blue);
    this.add(layoutPanel);
    this.add(diceKeepPanel);
    this.add(diceRollPanel);
    this.add(dp);
    this.add(rollDice);
    this.add(pick);
    this.add(choose);



  }
}
