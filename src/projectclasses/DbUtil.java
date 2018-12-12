package projectclasses;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DbUtil {

  //Connection
  private static Connection connection = null;

  static final String DATABASE_URL = "jdbc:derby:lib\\productdb";


  public static void dbConnect() throws SQLException, ClassNotFoundException {

    //Establish the Oracle Connection using Connection String
    try {
      connection = DriverManager.getConnection(
          DATABASE_URL, "", "");
    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console" + e);
      e.printStackTrace();
      throw e;
    }
  }

  public static void dbDisconnect() throws SQLException {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception e) {
      throw e;
    }
  }

  //DB Execute Query Operation
  public static ResultSet dbExecuteQuery(String queryStmt)
      throws SQLException, ClassNotFoundException {
    //Declare statement, resultSet and CachedResultSet as null
    Statement stmt = null;
    ResultSet resultSet = null;
    CachedRowSetImpl crs = null;
    try {
      //Connect to DB
      dbConnect();
      System.out.println("Select statement: " + queryStmt + "\n");

      //Create statement
      stmt = connection.createStatement();

      //Execute select (query) operation
      resultSet = stmt.executeQuery(queryStmt);

      //CachedRowSet Implementation
      //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
      //We are using CachedRowSet
      crs = new CachedRowSetImpl();
      crs.populate(resultSet);
    } catch (SQLException e) {
      System.out.println("Problem occurred at executeQuery operation : " + e);
      throw e;
    } finally {
      if (resultSet != null) {
        //Close resultSet
        resultSet.close();
      }
      if (stmt != null) {
        //Close Statement
        stmt.close();
      }
      //Close connection
      dbDisconnect();
    }
    //Return CachedRowSet
    return crs;
  }

//  //Delete an author with a given Id from DB
//  @FXML
//  private void deleteAuthor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//    try {
//      AuthorDAO.deleteAuthWithId(authIdText.getText());
//      resultArea.setText("Author deleted! Author id: " + authIdText.getText() + "\n");
//      searchAuthors(new ActionEvent());
//    } catch (SQLException e) {
//      resultArea.setText(
//          "Enter the id of the author to delete. \nProblem occurred while deleting author " + e);
//      throw e;
//    }
//  }


  public static void getStarted2(){
    try {
      dbConnect();
      final String SELECT_QUERY =
          //"SELECT authorID, firstName, lastName FROM authors";
          "Select * "
              + "from NEW_SCHEMA.PRODUCT \n"
              + "LEFT JOin NEW_SCHEMA.MOVIEPLAYER on NEW_SCHEMA.PRODUCT.SERIALNUMBER = NEW_SCHEMA.MOVIEPLAYER.SERIALNUMBERMP \n"
              + "LEFT Join NEW_SCHEMA.AUDIOPLAYER on  NEW_SCHEMA.PRODUCT.SERIALNUMBER= NEW_SCHEMA.AUDIOPLAYER.SERIALNUMBERAP";
      // use try-with-resources to connect to and query the database
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void getStarted(){

    final String DATABASE_URL = "jdbc:derby:lib\\productdb";
    //Use the sql Query "Select" To get data from the "authorID", "firstName" and "lastName"
    //from the table named author.
    final String SELECT_QUERY =
        //"SELECT authorID, firstName, lastName FROM authors";
        "Select * "
            + "from NEW_SCHEMA.PRODUCT \n"
            + "LEFT JOin NEW_SCHEMA.MOVIEPLAYER on NEW_SCHEMA.PRODUCT.SERIALNUMBER = NEW_SCHEMA.MOVIEPLAYER.SERIALNUMBERMP \n"
            + "LEFT Join NEW_SCHEMA.AUDIOPLAYER on  NEW_SCHEMA.PRODUCT.SERIALNUMBER= NEW_SCHEMA.AUDIOPLAYER.SERIALNUMBERAP";
    // use try-with-resources to connect to and query the database
    try (
        //Make a new connection with the database
        Connection connection = DriverManager.getConnection(
            DATABASE_URL,
            "", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
      // get ResultSet's meta data
      {
        //    resultSet.next(); // This line is important, it set results to first row instead of an error.
        // lblFirst.setText(resultSet.getString(2)); //firstName label gets FirstName column Result
        // lblLast.setText(resultSet.getString(3));//lastName label gets LastName column Result
        //lblID.setText(resultSet.getString(1));//authorID label gets authorID column Result
        // lblFirst.setText(resultSet.getString("NAME"));
        //lblLast.setText(String.valueOf(resultSet.getInt("serialNumber")) );
        Global.productList.clear();
        String name="";
        String screenRes="";
        int responseTime=1;
        int refreshRate=1;
        int serialNum=1;
        String monitorType="";

        String audioSpec="";



        //populate product arraylist
        while (resultSet.next()){
          System.out.println("ROW NEXT");
          if (resultSet.getString("TYPE").equalsIgnoreCase("AP")){
            //AudioPlyer detected
            name = resultSet.getString("NAME");
            audioSpec = resultSet.getString("AUDIOSPECIFICATION");
            AudioPlayer ap1 = new AudioPlayer(name, audioSpec);
            ap1.setProductionNumber(resultSet.getInt("SERIALNUMBER"));
            java.sql.Date sqlDate = resultSet.getDate("MANUFACTUREDON");
            ap1.setLocalDateManufactured(sqlDate.toLocalDate());

            String color = resultSet.getString("COLOR");
            ap1.setColor(color);
            Global.productList.add(ap1);

          } else {
            //MOVIE PLAYER DETECTED
            name = resultSet.getString("NAME");
            screenRes = resultSet.getString("SCREENRESOLUTION");
            responseTime = resultSet.getInt("RESPONSETIME");
            refreshRate = resultSet.getInt("REFRESHRATE");
            serialNum = resultSet.getInt("SERIALNUMBER");
            monitorType=resultSet.getString("MONITORTYPE");
            MonitorType monitorTypeEnum= MonitorType.LED;
            if (monitorType.equalsIgnoreCase("LCD")){
              monitorTypeEnum= MonitorType.LCD;
            }
            MoviePlayer mp1 = new MoviePlayer(name,
                new Screen(screenRes,refreshRate,responseTime),monitorTypeEnum);
           java.sql.Date sqlDate = resultSet.getDate("MANUFACTUREDON");
            mp1.setLocalDateManufactured(sqlDate.toLocalDate());

            mp1.setProductionNumber(resultSet.getInt("SERIALNUMBER"));
            String color = resultSet.getString("COLOR");
            mp1.setColor(color);
            Global.productList.add(mp1);
          }

        }

      }
    } // AutoCloseable objects' close methods are called now
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } catch (Exception ex) {
      //Database was unable to connect
      System.out.println("Error");
    }

  }

  //DB Execute Update (For Update/Insert/Delete) Operation
  public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
    //Declare statement as null
    Statement stmt = null;
    try {
      //Connect to DB (Establish Oracle Connection)
      dbConnect();
      //Create Statement
      stmt = connection.createStatement();
      //Run executeUpdate operation with given sql statement
      stmt.executeUpdate(sqlStmt);
    } catch (SQLException e) {
      System.out.println("Problem occurred at executeUpdate operation : " + e);
      throw e;
    } finally {
      if (stmt != null) {
        //Close statement
        stmt.close();
      }
      //Close connection
      dbDisconnect();
    }
  }

}
