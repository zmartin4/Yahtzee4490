package yahtzee;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
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

  @Test
  public void testGUI() throws IOException, NoSuchFieldException, SecurityException,
      IllegalArgumentException, IllegalAccessException {


  }

  @Test
  public void testBonus2() throws NoSuchFieldException, SecurityException {
    final GameControl gcTest = new GameControl(gpTest, null);
    Boolean[] rollable = {false, true, true, false, true};
    Integer[] diceValues = {5, 5, 5, 5, 5};
    Integer[] finalScore = new Integer[17];
    Integer[] currScore = new Integer[17];

    Arrays.fill(finalScore, -1);
    Arrays.fill(currScore, 0);
    finalScore[13] = 150;



    gcTest.diceValues = diceValues;
    gcTest.finalScore = finalScore;
    gcTest.currScore = currScore;
    gcTest.calculateScore();
    gcTest.calculateOthers();
    assertEquals("Make sure Score calculations are correct", (int) gcTest.finalScore[13], 150);
    // How can we get to the GamePanel through Testing?


  }

  @Test
  public void testExecuteDML() throws SQLException {
    db.executeDML(
        "INSERT INTO TheUser VALUES('TestUser', AES_ENCRYPT('TestPassword', 'secretkey'), 0)");

  }

  @Test
  public void testExecuteQuery() throws SQLException {
    ArrayList<String> qHighscore =
        db.query("SELECT userHighscore FROM TheUser WHERE userName='TestHighscore'");
    assertEquals("Checking to see if it accessed the Highscore", qHighscore.get(0), "-1");
  }

}
