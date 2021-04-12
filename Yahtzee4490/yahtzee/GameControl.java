package yahtzee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;


public class GameControl implements ActionListener, ItemListener {
  private JPanel container;
  private ChatClient client;

  private Integer[] diceValues = new Integer[5];
  private Boolean[] rollable = new Boolean[5];
  private Integer[] currScore = new Integer[16];
  private Integer[] finalScore = new Integer[16];
  private String selection = "";
  private Boolean canRoll = false;
  private int rollCount = 0;


  Random rand = new Random();



  // Constructor for the login controller.
  public GameControl(JPanel container, ChatClient client) {
    this.container = container;
    this.client = client;
    Arrays.fill(finalScore, -1);
    Arrays.fill(diceValues, 1);
    Arrays.fill(rollable, true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {

    String command = ae.getActionCommand();
    GamePanel gamePanel = (GamePanel) container.getComponent(4);


    if (command == "Roll Dice" && rollCount < 3) {
      rollDice();
      rollCount++;
    }

    if (rollCount > 0 && rollCount < 3) {

      // UPDATE EACH DICE LOCATION
      if (command == "1Keep" || command == "1Roll") {
        if (command == "1Keep") {
          gamePanel.setDice1(true, diceValues[0]);
          rollable[0] = true;
        } else {
          gamePanel.setDice1(false, diceValues[0]);
          rollable[0] = false;
        }
      } else if (command == "2Keep" || command == "2Roll") {
        if (command == "2Keep") {
          gamePanel.setDice2(true, diceValues[1]);
          rollable[1] = true;
        } else {
          gamePanel.setDice2(false, diceValues[1]);
          rollable[1] = false;
        }
      } else if (command == "3Keep" || command == "3Roll") {
        if (command == "3Keep") {
          gamePanel.setDice3(true, diceValues[2]);
          rollable[2] = true;
        } else {
          gamePanel.setDice3(false, diceValues[2]);
          rollable[2] = false;
        }
      } else if (command == "4Keep" || command == "4Roll") {
        if (command == "4Keep") {
          gamePanel.setDice4(true, diceValues[3]);
          rollable[3] = true;
        } else {
          gamePanel.setDice4(false, diceValues[3]);
          rollable[3] = false;
        }
      } else if (command == "5Keep" || command == "5Roll") {
        if (command == "5Keep") {
          gamePanel.setDice5(true, diceValues[4]);
          rollable[4] = true;
        } else {
          gamePanel.setDice5(false, diceValues[4]);
          rollable[4] = false;
        }
      }

    }
    if (command == "Pick Category" && rollCount > 0) {
      if (selection.equals("Select Categeory"))
        return;
      int category = selectionTranslation(selection);
      finalScore[category] = currScore[category];
      gamePanel.updateUserScoreboard(currScore, finalScore, category);
      gamePanel.removeCategory(selection);
    }
    GameData data = new GameData(finalScore, diceValues, rollable);


    try {
      client.sendToServer(data);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  // Listens for the JComboBox
  @Override
  public void itemStateChanged(ItemEvent arg0) {
    selection = (String) arg0.getItem();

  }


  // Translates the Category selection into an int that correlates to JButton Array
  private int selectionTranslation(String cat) {
    int num = -1;
    System.out.println(cat);
    switch (cat) {
      case "One":
        num = 0;
        break;
      case "Two":
        num = 1;
        break;
      case "Three":
        num = 2;
        break;
      case "Four":
        num = 3;
        break;
      case "Five":
        num = 4;
        break;
      case "Six":
        num = 5;
        break;
      case "Three of a Kind":
        num = 7;
        break;
      case "Four of a Kind":
        num = 8;
        break;
      case "Full House":
        num = 9;
        break;
      case "Small Straight":
        num = 10;
        break;
      case "Large Straight":
        num = 11;
        break;
      case "Yahtzee":
        num = 12;
        break;
      case "Chance":
        num = 13;
        break;
    }
    return num;


  }

  // Rolls the dice that are selected to be rolled
  private void rollDice() {
    int rand_int = 0;
    for (int i = 0; i < diceValues.length; i++) {
      if (rollable[i] == true) {
        rand_int = rand.nextInt(6) + 1;
        diceValues[i] = rand_int;
      }
    }
    updateDice();
  }


  // Updates the dice value and dice location
  private void updateDice() {
    for (int value : diceValues) {
      // System.out.println(value);
    }
    GamePanel gamePanel = (GamePanel) container.getComponent(4);
    gamePanel.setDice1(rollable[0], diceValues[0]);
    gamePanel.setDice2(rollable[1], diceValues[1]);
    gamePanel.setDice3(rollable[2], diceValues[2]);
    gamePanel.setDice4(rollable[3], diceValues[3]);
    gamePanel.setDice5(rollable[4], diceValues[4]);
    calculateScore();
  }


  // Calculates the Score of the Dice and updates the GUI's Scoreboard
  private void calculateScore() {
    Arrays.fill(currScore, 0);

    // Single number Scores
    for (int value : diceValues) {
      if (value == 1)
        currScore[0] = currScore[0] + 1;
      else if (value == 2)
        currScore[1] = currScore[1] + 2;
      else if (value == 3)
        currScore[2] = currScore[2] + 3;
      else if (value == 4)
        currScore[3] = currScore[3] + 4;
      else if (value == 5)
        currScore[4] = currScore[4] + 5;
      else if (value == 6)
        currScore[5] = currScore[5] + 6;
    }

    Integer[] arr = diceValues.clone();
    Arrays.sort(arr);
    List<Integer> list = Arrays.asList(diceValues);

    int sum = 0;
    for (int value : arr) {
      sum += value;
    }

    // Three of a Kind, Four of a Kind, Full House
    for (int i = 0; i < 3; i++) {
      if (arr[i] == arr[i + 2]) { // Three of a kind
        if (i < 2 && arr[i] == arr[i + 3]) { // Four of a kind V1
          currScore[8] = sum;
        } else if (i == 0 && arr[2] == arr[3]) { // Four of a kind V2
          currScore[8] = sum;
        } else if (i == 2 && arr[0] == arr[1]) { // Full House V1
          currScore[9] = 25;
        } else if (i == 0 && arr[3] == arr[4]) { // Full House V2
          currScore[9] = 25;
        } else {
          currScore[7] = sum;
        }
      }
    }

    // Yahtzee
    if (arr[0] == arr[4])
      currScore[12] = 50;

    // Small Straight, Large Straight
    boolean striaght;
    for (int i = 0; i < 3; i++) { // For all places
      striaght = false;
      for (int j = 1; j <= 4; j++) {// 1,2,3,4; 2,3,4,5; 3,4,5,6

        if (!list.contains(i + j))
          break;
        if (j == 4)
          striaght = true;

      }
      if (striaght == true) {
        if (i < 3 && list.contains(i + 5))
          currScore[11] = 40;
        else
          currScore[10] = 30;
        break;

      }
    }



    GamePanel gamePanel = (GamePanel) container.getComponent(4);
    gamePanel.updateUserScoreboard(currScore, finalScore, -1);
  }


  public void setOpponentsInfo() {
    GamePanel gamePanel = (GamePanel) container.getComponent(4);
    gamePanel.updateOpponentInfo();
  }
}
