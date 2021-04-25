package yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GamePanel extends JPanel {



  // Dices for the rollable and non-rollable location
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
  ImageIcon bgTable;


  // Faces for the dice
  private ImageIcon face1 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_1.png"));
  private ImageIcon face2 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_2.png"));
  private ImageIcon face3 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_3.png"));
  private ImageIcon face4 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_4.png"));
  private ImageIcon face5 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_5.png"));
  private ImageIcon face6 =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/face_6.png"));
  private ImageIcon blank =
      new ImageIcon(this.getClass().getResource("/yahtzee/images/board_color.png"));


  // Creates button for each category for the user of the scoreboard
  private JButton[] userScoreButtons = new JButton[17];
  private JButton nameUser = new JButton("");
  private JButton oneUser = new JButton("");
  private JButton twoUser = new JButton("");
  private JButton threeUser = new JButton("");
  private JButton fourUser = new JButton("");
  private JButton fiveUser = new JButton("");
  private JButton sixUser = new JButton("");
  private JButton bonus1User = new JButton("");
  private JButton kind3User = new JButton("");
  private JButton kind4User = new JButton("");
  private JButton fhUser = new JButton("");
  private JButton smallSUser = new JButton("");
  private JButton largeSUser = new JButton("");
  private JButton yahtzeeUser = new JButton("");
  private JButton chanceUser = new JButton("");
  private JButton bonus2User = new JButton("");
  private JButton totalUser = new JButton("");

  private ArrayList<JButton[]> oppScore = new ArrayList<JButton[]>();
  private JButton[] oppScoreButtons;
  private JButton nameOpp;
  private JButton oneOpp;
  private JButton twoOpp;
  private JButton threeOpp;
  private JButton fourOpp;
  private JButton fiveOpp;
  private JButton sixOpp;
  private JButton bonus1Opp;
  private JButton kind3Opp;
  private JButton kind4Opp;
  private JButton fhOpp;
  private JButton smallSOpp;
  private JButton largeSOpp;
  private JButton yahtzeeOpp;
  private JButton chanceOpp;
  private JButton bonus2Opp;
  private JButton totalOpp;

  // Creates button for each category label for the scoreboard
  private JButton[] catButtons = new JButton[17];
  JButton name = new JButton("Name");
  JButton ones = new JButton("Ones");
  JButton twos = new JButton("Twos");
  JButton threes = new JButton("Threes");
  JButton fours = new JButton("Fours");
  JButton fives = new JButton("Fives");
  JButton sixes = new JButton("Sixes");
  JButton bonus1 = new JButton("Bonus 1");
  JButton kind3 = new JButton("Three of a Kind");
  JButton kind4 = new JButton("Four of a Kind");
  JButton fh = new JButton("Full House");
  JButton smallS = new JButton("Small Straight");
  JButton largeS = new JButton("Large Straight");
  JButton yahtzee = new JButton("Yahtzee");
  JButton chance = new JButton("Chance");
  JButton bonus2 = new JButton("Bonus 2");
  JButton total = new JButton("Total");



  // Buttons for user selections
  JButton rollDice;
  JButton chooseCategory;
  JButton quitButton;
  JComboBox pickCategory;

  int orderNum;
  int numOpps;



  ArrayList<String> categorySelection = new ArrayList<String>();
  final String[] categorySelectionsFINAL = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
      "Three of a Kind", "Four of a Kind", "Full House", "Small Straight", "Large Straight",
      "Yahtzee", "Chance", "Select Categeory"}; // Starting category label names

  // Dice Interaction Panel
  JPanel layoutPanel = new JPanel(new BorderLayout(40, 25));
  JPanel playPanel = new JPanel(new BorderLayout());
  JPanel scoresPanel = new JPanel(new GridBagLayout());
  JPanel scoreboardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
  JPanel diceControlPanel = new JPanel();
  JPanel catP = new JPanel(new GridBagLayout());
  GridBagConstraints catC = new GridBagConstraints();
  GridBagConstraints scoreC = new GridBagConstraints();
  JPanel categoriesPanel = new JPanel(new GridLayout(17, 0, 10, 5));
  JPanel quitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
  GridBagConstraints c = new GridBagConstraints();
  JPanel dicePanel = new JPanel(new GridBagLayout());

  // Style Constants
  Font catFont = new Font("Arial", Font.BOLD, 20);
  Font nameFont = new Font("Arial", Font.BOLD, 14);
  Font buttonFont = new Font("Arial", Font.BOLD, 32);
  Color yRed = new Color(215, 45, 53);
  Color yourTurn = new Color(160, 230, 255);
  Dimension diceSize = new Dimension(70, 70);

  //////////////////////////////////
  ///// Changes Dice Status /////
  //////////////////////////////////

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
    dice1Keep.setDisabledIcon(LogoIcon);
    dice1Keep.setEnabled(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice1Roll.setIcon(LogoIcon);
    dice1Roll.setDisabledIcon(LogoIcon);
    dice1Roll.setEnabled(!isClickable);
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
    dice2Keep.setDisabledIcon(LogoIcon);
    dice2Keep.setEnabled(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice2Roll.setIcon(LogoIcon);
    dice2Roll.setDisabledIcon(LogoIcon);
    dice2Roll.setEnabled(!isClickable);


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
    dice3Keep.setDisabledIcon(LogoIcon);
    dice3Keep.setEnabled(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice3Roll.setIcon(LogoIcon);
    dice3Roll.setDisabledIcon(LogoIcon);
    dice3Roll.setEnabled(!isClickable);

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
    dice4Keep.setDisabledIcon(LogoIcon);
    dice4Keep.setEnabled(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice4Roll.setIcon(LogoIcon);
    dice4Roll.setDisabledIcon(LogoIcon);
    dice4Roll.setEnabled(!isClickable);

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
    dice5Keep.setDisabledIcon(LogoIcon);
    dice5Keep.setEnabled(isClickable);

    LogoIcon = new ImageIcon(this.getClass().getResource(rollLogo));
    dice5Roll.setIcon(LogoIcon);
    dice5Roll.setDisabledIcon(LogoIcon);
    dice5Roll.setEnabled(!isClickable);

  }

  //////////////////////////////////////////
  ///// INITIAL SCOREBOARD SETUPS /////
  /////////////////////////////////////////
  public void setNumOpps(int num) {
    oppScore = new ArrayList<JButton[]>();
    numOpps = num;

    setOppScoreboard();
    scoreboardPanel.add(scoresPanel);
    layoutPanel.add(scoreboardPanel, BorderLayout.LINE_START);
    layoutPanel.add(playPanel, BorderLayout.LINE_END);

    this.add(layoutPanel);
  }

  public void setCategoryButtons() {

    // Sets ButtonArray for category LABELS for the scoreboard
    catButtons[0] = name;
    catButtons[1] = ones;
    catButtons[2] = twos;
    catButtons[3] = threes;
    catButtons[4] = fours;
    catButtons[5] = fives;
    catButtons[6] = sixes;
    catButtons[7] = bonus1;
    catButtons[8] = kind3;
    catButtons[9] = kind4;
    catButtons[10] = fh;
    catButtons[11] = smallS;
    catButtons[12] = largeS;
    catButtons[13] = yahtzee;
    catButtons[14] = chance;
    catButtons[15] = bonus2;
    catButtons[16] = total;

    // Sets the style elements for each label
    for (int i = 0; i < catButtons.length; i++) {
      catButtons[i].setBorderPainted(false);
      catButtons[i].setFocusPainted(false);
      catButtons[i].setBackground(yRed);
      catButtons[i].setForeground(Color.WHITE);
      catButtons[i].setFont(catFont);
    }


    catP.setBackground(Color.LIGHT_GRAY);
    int spacing = 0;
    for (int i = 0; i < userScoreButtons.length; i++) {
      catButtons[i].setBorderPainted(false);
      catButtons[i].setFocusPainted(false);
      catButtons[i].setForeground(Color.WHITE);
      catButtons[i].setFont(catFont);
      catButtons[i].setPreferredSize(new Dimension(185, 35));

      catC.gridx = 0;
      catC.gridy = spacing;
      catC.insets = new Insets(3, 0, 3, 0);
      if (i == userScoreButtons.length - 1 || i == 0)
        catC.insets = new Insets(10, 0, 10, 0);
      catP.add(catButtons[i], catC);
      spacing = spacing + 5;
    }

    for (String categories : categorySelectionsFINAL)
      categorySelection.add(categories);
  }

  public void setUserScoreboard() {
    // Sets ButtonArray for each category SCORE for the USER
    userScoreButtons[0] = nameUser;

    userScoreButtons[1] = oneUser;
    userScoreButtons[2] = twoUser;
    userScoreButtons[3] = threeUser;
    userScoreButtons[4] = fourUser;
    userScoreButtons[5] = fiveUser;
    userScoreButtons[6] = sixUser;
    userScoreButtons[7] = bonus1User;
    userScoreButtons[8] = kind3User;
    userScoreButtons[9] = kind4User;
    userScoreButtons[10] = fhUser;
    userScoreButtons[11] = smallSUser;
    userScoreButtons[12] = largeSUser;
    userScoreButtons[13] = yahtzeeUser;
    userScoreButtons[14] = chanceUser;
    userScoreButtons[15] = bonus2User;
    userScoreButtons[16] = totalUser;

    scoresPanel.setBackground(Color.LIGHT_GRAY);

    int spacing = 0;
    for (int i = 0; i < userScoreButtons.length; i++) {
      userScoreButtons[i].setFont(catFont);
      userScoreButtons[i].setPreferredSize(new Dimension(75, 35));
      userScoreButtons[i].setBackground(Color.white);


      scoreC.gridx = 0;
      scoreC.gridy = spacing;
      scoreC.insets = new Insets(3, 7, 3, 23);
      if (i == userScoreButtons.length - 1 || i == 0)
        scoreC.insets = new Insets(10, 7, 10, 23);
      scoresPanel.add(userScoreButtons[i], scoreC);
      spacing = spacing + 5;
    }
  }

  public void setOppScoreboard() {
    for (int i = 0; i < numOpps; i++) {
      oppScoreButtons = new JButton[17];
      nameOpp = new JButton();
      oneOpp = new JButton();
      twoOpp = new JButton();
      threeOpp = new JButton();
      fourOpp = new JButton();
      fiveOpp = new JButton();
      sixOpp = new JButton();
      bonus1Opp = new JButton();
      kind3Opp = new JButton();
      kind4Opp = new JButton();
      fhOpp = new JButton();
      smallSOpp = new JButton();
      largeSOpp = new JButton();
      yahtzeeOpp = new JButton();
      chanceOpp = new JButton();
      bonus2Opp = new JButton();
      totalOpp = new JButton();

      oppScoreButtons[0] = nameOpp;
      oppScoreButtons[1] = oneOpp;
      oppScoreButtons[2] = twoOpp;
      oppScoreButtons[3] = threeOpp;
      oppScoreButtons[4] = fourOpp;
      oppScoreButtons[5] = fiveOpp;
      oppScoreButtons[6] = sixOpp;
      oppScoreButtons[7] = bonus1Opp;
      oppScoreButtons[8] = kind3Opp;
      oppScoreButtons[9] = kind4Opp;
      oppScoreButtons[10] = fhOpp;
      oppScoreButtons[11] = smallSOpp;
      oppScoreButtons[12] = largeSOpp;
      oppScoreButtons[13] = yahtzeeOpp;
      oppScoreButtons[14] = chanceOpp;
      oppScoreButtons[15] = bonus2Opp;
      oppScoreButtons[16] = totalOpp;

      oppScore.add(oppScoreButtons);
      scoresPanel.setBackground(Color.LIGHT_GRAY);

      int spacing = 0;
      for (int j = 0; j < oppScoreButtons.length; j++) {
        oppScoreButtons[j].setFont(catFont);
        oppScoreButtons[j].setPreferredSize(new Dimension(75, 35));
        oppScoreButtons[j].setBackground(Color.white);
        scoreC.gridx = i + 1;
        scoreC.gridy = spacing;
        scoreC.insets = new Insets(3, 7, 3, 7);


        if (j == userScoreButtons.length - 1 || j == 0)
          scoreC.insets = new Insets(10, 7, 10, 7);
        scoresPanel.add(oppScoreButtons[j], scoreC);
        spacing = spacing + 5;
      }
    }

  }

  public void resetScoreboard() {
    categorySelection.clear();
    for (String categories : categorySelectionsFINAL)
      categorySelection.add(categories);
    setUserScoreboard();
    oppScore.clear();
    removeCategorySelection("");
  }

  ////////////////////////////////////////////////////
  ///// Sets Names on the Top of Scoreboard /////
  //////////////////////////////////////////////////

  public void setUserName(String username) {
    nameUser.setText(username);
    nameUser.setFont(nameFont);
  }

  public void setOppName(Object oppName) {
    ArrayList<String> oppNames = (ArrayList<String>) oppName;

    for (int i = 0; i < oppNames.size(); i++) {
      System.out.println(oppNames.get(i));
      oppScore.get(i)[0].setText(oppNames.get(i));
      oppScore.get(i)[0].setFont(nameFont);
    }
  }


  // Called Internally from GameControl
  // Updates the users scoreboard values as dice are being thrown
  public void updateUserScoreboard(Integer[] userScore, Integer[] finalScore) {
    // for (int i = 0; i < oppScore.size(); i++) {
    // for (int j = 1; j < oppScore.get(i).length; j++) {
    // oppScore.get(i)[j].setBackground(yourTurn);
    // }
    // }
    for (int i = 1; i < userScore.length; i++) {
      // userScoreButtons[i].setBackground(yourTurn);
    }


    // Add all scores to userScoreboard
    for (int i = 1; i < userScore.length; i++) {

      if (i == 7 && finalScore[i] == -1 || i == 15 && finalScore[i] == -1)
        continue;
      userScoreButtons[i].setText(String.valueOf(userScore[i]));
      userScoreButtons[i].setForeground(Color.red);
    }


    // Overrides and adds previously selected categories
    for (int i = 1; i < finalScore.length; i++) {
      if (finalScore[i] != -1) {
        userScoreButtons[i].setText(String.valueOf(finalScore[i]));
        userScoreButtons[i].setForeground(Color.black);
      }
    }
  }

  // Called from GameControl from the ChatServer
  // Updates the state of the dice and the opps scoreboard once a category is submitted
  public void updateOppGame(Object arg0, String whosData) {

    System.out.println("whos Data: " + whosData);
    System.out.println(oppScore.get(0)[0].getText());
    GameData currentGameData = (GameData) arg0;
    Integer[] diceValue = currentGameData.getDiceValues();
    Boolean[] rollable = currentGameData.getRollable();
    Integer[] score = currentGameData.getPlayerScore();

    for (int i = 0; i < oppScore.size(); i++) {
      if (oppScore.get(i)[0].getText().equals(whosData)) {
        for (int j = 1; j < oppScore.get(i).length; j++) {
          oppScore.get(i)[j].setBackground(yourTurn);
          if (score[j] != -1) {
            oppScore.get(i)[j].setText(String.valueOf(score[j]));
          }
        }
      }
    }


    setDice1(rollable[0], diceValue[0]);
    setDice2(rollable[1], diceValue[1]);
    setDice3(rollable[2], diceValue[2]);
    setDice4(rollable[3], diceValue[3]);
    setDice5(rollable[4], diceValue[4]);
  }

  // Sets the color for the user scoreboard if it's their turn
  public void setTurnIndicator() {
    for (int i = 1; i < userScoreButtons.length; i++)
      userScoreButtons[i].setBackground(yourTurn);

    for (int i = 0; i < oppScore.size(); i++) {
      for (int j = 1; j < oppScore.get(i).length; j++) {
        oppScore.get(i)[j].setBackground(Color.WHITE);
      }
    }


  }

  // Sets message in gamePanel
  public void showGameMessage(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }

  // Sets the color of the User scoreboard back to white and finalScore colors
  public void resetUserScoreboard(Integer[] finalScore) {
    for (int i = 1; i < finalScore.length; i++) {
      userScoreButtons[i].setBackground(Color.white);
      if (finalScore[i] != -1) {
        userScoreButtons[i].setText(String.valueOf(finalScore[i]));
        userScoreButtons[i].setForeground(Color.black);
      } else {
        userScoreButtons[i].setText("");
      }
    }
  }

  // Removes the chosen category from the JComboBox
  public void removeCategorySelection(String selection) {

    DefaultComboBoxModel model = (DefaultComboBoxModel) pickCategory.getModel();
    String toRemove = "";
    for (String cat : categorySelection) {
      if (cat.equals(selection))
        toRemove = selection;
    }
    categorySelection.remove(toRemove);
    model.removeAllElements();
    for (String cat : categorySelection)
      model.addElement(cat);

    pickCategory.setModel(model);
    pickCategory.setSelectedIndex(categorySelection.size() - 1);

  }



  public GamePanel(GameControl gc) {
    setCategoryButtons();
    setUserScoreboard();

    scoreboardPanel.setBackground(Color.LIGHT_GRAY);
    categoriesPanel.setBackground(Color.LIGHT_GRAY);
    scoreboardPanel.add(catP);

    //////////////////////////
    /* Buttons for the Dice */
    //////////////////////////
    dice1Keep.setPreferredSize(diceSize);
    dice1Keep.addActionListener(gc);
    dice1Keep.setBorderPainted(false);
    dice1Keep.setFocusPainted(false);
    dice1Keep.setIcon(blank);
    dice1Keep.setIconTextGap(-6);

    dice1Roll.setPreferredSize(diceSize);
    dice1Roll.addActionListener(gc);
    dice1Roll.setBorderPainted(false);
    dice1Roll.setFocusPainted(false);
    dice1Roll.setIconTextGap(-6);
    dice1Roll.setIcon(face1);


    dice2Keep.setPreferredSize(diceSize);
    dice2Keep.addActionListener(gc);
    dice2Keep.setBorderPainted(false);
    dice2Keep.setFocusPainted(false);
    dice2Keep.setIcon(blank);
    dice2Keep.setIconTextGap(-6);


    dice2Roll.setPreferredSize(diceSize);
    dice2Roll.addActionListener(gc);
    dice2Roll.setBorderPainted(false);
    dice2Roll.setFocusPainted(false);
    dice2Roll.setIcon(face1);
    dice2Roll.setIconTextGap(-6);

    dice3Keep.setPreferredSize(diceSize);
    dice3Keep.addActionListener(gc);
    dice3Keep.setBorderPainted(false);
    dice3Keep.setFocusPainted(false);
    dice3Keep.setIcon(blank);
    dice3Keep.setIconTextGap(-6);

    dice3Roll.setPreferredSize(diceSize);
    dice3Roll.addActionListener(gc);
    dice3Roll.setBorderPainted(false);
    dice3Roll.setFocusPainted(false);
    dice3Roll.setIcon(face1);
    dice3Roll.setIconTextGap(-6);

    dice4Keep.setPreferredSize(diceSize);
    dice4Keep.addActionListener(gc);
    dice4Keep.setBorderPainted(false);
    dice4Keep.setFocusPainted(false);
    dice4Keep.setIcon(blank);
    dice4Keep.setIconTextGap(-6);

    dice4Roll.setPreferredSize(diceSize);
    dice4Roll.addActionListener(gc);
    dice4Roll.setBorderPainted(false);
    dice4Roll.setFocusPainted(false);
    dice4Roll.setIcon(face1);
    dice4Roll.setIconTextGap(-6);

    dice5Keep.setPreferredSize(diceSize);
    dice5Keep.addActionListener(gc);
    dice5Keep.setBorderPainted(false);
    dice5Keep.setFocusPainted(false);
    dice5Keep.setIcon(blank);
    dice5Keep.setIconTextGap(-6);

    dice5Roll.setPreferredSize(diceSize);
    dice5Roll.addActionListener(gc);
    dice5Roll.setBorderPainted(false);
    dice5Roll.setFocusPainted(false);
    dice5Roll.setIcon(face1);
    dice5Roll.setIconTextGap(-6);



    ////////////////////////////////////////
    /* Sets Location for the Dice Buttons */
    ////////////////////////////////////////

    c.gridx = 2;
    c.gridy = 2;
    c.insets = new Insets(0, 20, 0, 20);
    dicePanel.add(dice1Roll, c);
    c.gridx = 3;
    dicePanel.add(dice2Roll, c);
    c.gridx = 4;
    dicePanel.add(dice3Roll, c);
    c.gridx = 5;
    dicePanel.add(dice4Roll, c);
    c.gridx = 6;
    dicePanel.add(dice5Roll, c);

    c.gridx = 2;
    c.gridy = 3;
    c.insets = new Insets(100, 20, 0, 20);
    dicePanel.add(dice1Keep, c);
    c.gridx = 3;
    dicePanel.add(dice2Keep, c);
    c.gridx = 4;
    dicePanel.add(dice3Keep, c);
    c.gridx = 5;
    dicePanel.add(dice4Keep, c);
    c.gridx = 6;
    dicePanel.add(dice5Keep, c);


    quitButton = new JButton("Quit Game");
    quitButton.addActionListener(gc);

    rollDice = new JButton("Roll Dice");
    rollDice.addActionListener(gc);
    rollDice.setPreferredSize(new Dimension(0, 75));
    rollDice.setFont(buttonFont);

    pickCategory = new JComboBox(categorySelection.toArray());

    pickCategory.addItemListener(gc);
    pickCategory.setSelectedIndex(categorySelection.size() - 1);
    pickCategory.setPreferredSize(new Dimension(200, 40));
    pickCategory.setFont(catFont);

    chooseCategory = new JButton("Pick Category");
    chooseCategory.addActionListener(gc);
    chooseCategory.setPreferredSize(new Dimension(200, 40));
    chooseCategory.setFont(catFont);



    // adding sections to the scoreboard
    scoreboardPanel.add(categoriesPanel);
    quitPanel.add(quitButton);

    // add dice selection
    diceControlPanel.setPreferredSize(new Dimension(1, 100));
    diceControlPanel.add(pickCategory);
    diceControlPanel.add(chooseCategory);

    // add dice interaction
    playPanel.add(rollDice, BorderLayout.PAGE_START);
    playPanel.add(dicePanel, BorderLayout.LINE_END);
    playPanel.add(diceControlPanel, BorderLayout.PAGE_END);

    // add all panels
    layoutPanel.add(quitPanel, BorderLayout.PAGE_START);

  }
}

