package yahtzee;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class Database {
  private Connection conn;

  public Database() {
    // Read from the properties
    Properties prop = new Properties();

    // Create a file input stream to read from db.properties
    try {
      FileInputStream fis = new FileInputStream("yahtzee/db.properties");
      prop.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Properties from db file
    String user = prop.getProperty("user");
    String url = prop.getProperty("url");
    String pass = prop.getProperty("password");

    // Create the connection object
    try {
      conn = DriverManager.getConnection(url, user, pass);
      // this.executeDML(
      // "insert into theuser values('username', aes_encrypt('password', 'secretkey'), '0')");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  // select * from user; (example of query)
  public ArrayList<String> query(String query) {
    // Declare array list
    ArrayList<String> array = new ArrayList<String>();

    // Declare statement and resultset
    Statement stmt;
    ResultSet rs;
    ResultSetMetaData rmd;
    String record = "";


    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);

      if (!rs.next()) {
        return null;
      } else {
        // Get metadata
        rmd = rs.getMetaData();
        int numColumns = rmd.getColumnCount();
        int i = 1;

        // Outerloop gets each row
        do {
          // Inner loop gets each column
          for (i = 1; i <= numColumns; i++) {
            // The record is the current thing in rs
            record = rs.getString(i);

          }
          // Add to the array
          array.add(record);


        } while (rs.next());
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return array;
  }


  public void executeDML(String dml) throws SQLException {
    Statement stmt = conn.createStatement();

    // Execute the statement
    stmt.execute(dml);

  }

}
