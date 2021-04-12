package yahtzee;

import java.io.Serializable;

public class GameData implements Serializable {
  private Integer[] playerScore = new Integer[6];
  private Integer[] diceValues = new Integer[5];
  private Boolean[] rollable = new Boolean[5];

  public void setPlayerScore(Integer[] playerScore) {
    this.playerScore = playerScore;
  }

  public void setDiceValues(Integer[] diceValues) {
    this.diceValues = diceValues;
  }

  public void setRollable(Boolean[] rollable) {
    this.rollable = rollable;
  }

  public Integer[] getPlayerScore() {
    return playerScore;
  }

  public Integer[] getDiceValues() {
    return diceValues;
  }

  public Boolean[] getRollable() {
    return rollable;
  }



}
