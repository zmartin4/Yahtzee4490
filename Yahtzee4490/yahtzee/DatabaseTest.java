package yahtzee;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;



public class DatabaseTest {
  private Database db;
  private GamePanel gpTest;
  private GameControl gcTest;


  @Before
  public void setUpBefore() throws Exception {
    db = new Database();
  }



  // This validates that the bonus calculation works correctly given the various
  // states of the yahtzee score and the bonus score
  @Test
  public void testBonus2() throws NoSuchFieldException, SecurityException {
    final GameControl gcTest = new GameControl(gpTest, null);
    GamePanel gp = new GamePanel(gcTest);


    Boolean[] rollable = {false, true, true, false, true};
    Integer[] diceValues = {5, 5, 5, 5, 5};
    Integer[] finalScore = new Integer[17];
    Integer[] currScore = new Integer[17];
    Arrays.fill(finalScore, -1);
    Arrays.fill(currScore, 0);

    finalScore[13] = 0;

    gcTest.gamePanel = gp;
    gcTest.diceValues = diceValues;
    gcTest.finalScore = finalScore;
    gcTest.currScore = currScore;

    gcTest.calculateScore();
    gcTest.calculateOthers();

    assertEquals("When Yahtzee category is already picked as 0", (int) gcTest.finalScore[15], 0);



    finalScore[13] = 50;
    finalScore[15] = -1;
    gcTest.finalScore = finalScore;
    gcTest.calculateScore();
    gcTest.calculateOthers();

    assertEquals("When Yahtzee category has been filled already", (int) gcTest.finalScore[15], 100);



    finalScore[15] = 100;
    gcTest.finalScore = finalScore;
    gcTest.calculateScore();
    gcTest.calculateOthers();

    assertEquals("When Yahtzee category and bonus has been filled once",
        (int) gcTest.finalScore[15], 200);



  }

  // Test to see if connected to the Database
  @Test
  public void testExecuteDML() throws SQLException {
    db.executeDML(
        "INSERT INTO TheUser VALUES('TestHighscore', AES_ENCRYPT('TestPassword', 'secretkey'), -1)");
  }

  // Test whether the prevoius entry is able to be accessed
  @Test
  public void testExecuteQuery() throws SQLException {
    ArrayList<String> qHighscore =
        db.query("SELECT userHighscore FROM TheUser WHERE userName='TestHighscore'");

    assertEquals("Checking to see if it accessed the Highscore", qHighscore.get(0), "-1");
  }

}
