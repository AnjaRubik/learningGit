/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agarest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Andrew
 */
public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    private Connection conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
	}
        catch (ClassNotFoundException e) {
            System.out.println("Missing driver");
	}

	try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/agarest","root", "123456");
            return connect;
        } 
        catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return null;
	}
    }
    
  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/agarest?"
              + "user=sqluser&password=sqluserpw");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from feedback.comments");
      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
      // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      // Parameters start with 1
      preparedStatement.setString(1, "Test");
      preparedStatement.setString(2, "TestEmail");
      preparedStatement.setString(3, "TestWebpage");
      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
      preparedStatement.setString(5, "TestSummary");
      preparedStatement.setString(6, "TestComment");
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      // Remove again the insert comment
      preparedStatement = connect
      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from feedback.comments");
      writeMetaData(resultSet);
      
    } catch (ClassNotFoundException | SQLException e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user = resultSet.getString("myuser");
      String website = resultSet.getString("webpage");
      String summery = resultSet.getString("summery");
      Date date = resultSet.getDate("datum");
      String comment = resultSet.getString("comments");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("Summery: " + summery);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

    public String addCharacter(String name, int level, int STR, int VIT, int AGI, int INT, int WIS, int LUK) {
        try {
            preparedStatement = conn().prepareStatement("insert into agarest.characters (name,level,str,vit,agi,intel,wis,luk) values (?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, level);
            preparedStatement.setInt(3, STR);
            preparedStatement.setInt(4, VIT);
            preparedStatement.setInt(5, AGI);
            preparedStatement.setInt(6, INT);
            preparedStatement.setInt(7, WIS);
            preparedStatement.setInt(8, LUK);
     
            String result = String.valueOf(preparedStatement.executeUpdate());
            return result;
        }  
        catch (SQLException e) {
            return String.valueOf(e);
        }
    }
    
    public void test() {
    
    }
    
    public String levelUpChar(String name, int level, int STR, int VIT, int AGI, int INT, int WIS, int LUK) {
        try {
            Connection transaction = conn();
            transaction.setAutoCommit(false);
            
            preparedStatement = transaction.prepareStatement("update agarest.characters set level = ?, str = ?, vit = ?, agi = ?, int = ?, wis = ?, luk = ? where name = ?");
            // Parameters start with 1
            preparedStatement.setInt(1, level);
            preparedStatement.setInt(2, STR);
            preparedStatement.setInt(3, VIT);
            preparedStatement.setInt(4, AGI);
            preparedStatement.setInt(5, INT);
            preparedStatement.setInt(6, WIS);
            preparedStatement.setInt(7, LUK);
            preparedStatement.setString(8, name);
     
            String result = String.valueOf(preparedStatement.executeUpdate());
            return result;
        }  
        catch (SQLException e) {
            return String.valueOf(e);
        }
    }
} 
