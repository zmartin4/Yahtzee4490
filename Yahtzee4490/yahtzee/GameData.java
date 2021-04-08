package yahtzee;

public class GameData {
	//Array that contains each players' score
	private int[] playerScores;
	
	//Array that contains the values for the dice
	private int[] diceValues;
	
	
	public int[] getPlayerScores(){
		return this.playerScores;
	}
	
	public int[] getDiceValues() {
		return this.diceValues;
	}
	
	public void setDiceValues(int[] diceValues) {
		this.diceValues = diceValues;
	}
	
	public GameData() {
		
		
		
		
		
	}
	
}
