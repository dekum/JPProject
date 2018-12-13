/**
 * DBUtil.java
 * 12/10/18
 * @author Philemon Petit-Frere
 * This class is a utility class that accesses the database and creates resultSets.
 * To use this method, create a query and pass it to one of the methods
 * This class has methods to Connect and Deconnect from database,
 * and also to initialize the product list used the program.
 * Use this class if you going to access the database.
 */

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

  /**
   * Connection object used to connect to database.
   */
  private static Connection connection = null;

  /**
   * Relative URL to the database. Derby is used in this program.
   * This url allows it to be used on other devices that have derby installed.
   */
  static final String DATABASE_URL = "jdbc:derby:lib\\productdb";

  /**
   * This method makes a connection to the database.
   * Prints an error statement if an error occurs.
   *
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   * @throws  SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
  public static void dbConnect() throws SQLException, ClassNotFoundException {

    //Establish the Oracle Connection using Connection String
    try {
      connection = DriverManager.getConnection(
          DATABASE_URL, "", "");
      //findBugs suggests making this database password protected.
    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console" + e);
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * This method disconnects program from database.
   *
   * @throws  SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
  public static void dbDisconnect() throws SQLException {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * This method execute a query operation, given a query Statement in parameter.
   *
   * @param  queryStmt A query to be executed and turned into a result set
   * @return crs a resultset of the query statement passed.
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   * @throws  SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
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

  /**
   * Method used to initalize the productlist used in the program.
   * This excutes a querty to join 3 tables, product audioplayer and movieplayer together,
   * where the serial number match.
   * Then a resultset is generated, which is used line by line to create new objects
   * of AudioPlayers and MoviePlayers.
   * The program knows if a product is a AudioPlayer or Movieplayer because of Product
   * table's Type column, AP is audioplayer and MP is moviePlayer
   * These objects are then added to Global's static productList, an arraylist of products.
   * Serial number is important and must be unique so program knows which tables to join at what
   * time.
   */
  public static void populateProductList() {

    final String databaseUrl = "jdbc:derby:lib\\productdb";
    //Use the sql Query "Select" To get data from the "authorID", "firstName" and "lastName"
    //from the table named author.
    final String selectQuery =
        //"SELECT authorID, firstName, lastName FROM authors";
        "Select * "
            + "from NEW_SCHEMA.PRODUCT \n"
            + "LEFT JOin NEW_SCHEMA.MOVIEPLAYER "
            + "on NEW_SCHEMA.PRODUCT.SERIALNUMBER = NEW_SCHEMA.MOVIEPLAYER.SERIALNUMBERMP \n"
            + "LEFT Join NEW_SCHEMA.AUDIOPLAYER "
            + "on  NEW_SCHEMA.PRODUCT.SERIALNUMBER = NEW_SCHEMA.AUDIOPLAYER.SERIALNUMBERAP";
    // use try-with-resources to connect to and query the database
    try (
        //Make a new connection with the database
        Connection connection = DriverManager.getConnection(
            databaseUrl,
            "", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery)) {
      // get ResultSet's meta data
      {
        //resultSet.next();
        // This line is important, it set results to first row instead of an error.
        Global.productList.clear();
        String name = "";
        String screenRes = "";
        int responseTime = 1;
        int refreshRate = 1;
        int serialNum = 1;
        String monitorType = "";

        String audioSpec = "";

        //populate product arraylist
        while (resultSet.next()) {
          System.out.println("ROW NEXT");
          if (resultSet.getString("TYPE").equalsIgnoreCase("AP")) {
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
            monitorType = resultSet.getString("MONITORTYPE");
            MonitorType monitorTypeEnum = MonitorType.LED;
            if (monitorType.equalsIgnoreCase("LCD")) {
              monitorTypeEnum = MonitorType.LCD;
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
      // AutoCloseable objects' close methods are called now
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } catch (RuntimeException e) {
      System.out.println("Error " + e);
    } catch (Exception ex) {
      //Database was unable to connect
      System.out.println("Error");
    }

  }

  //DB Execute Update (For Update/Insert/Delete) Operation

  /**
   * A method used to excute an update on the database.
   * Such as a Update, Insert or Delete operation.
   *
   * @param sqlStmt sql statement to be performed by this method.
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   * @throws  SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
  public static void dbExecuteUpdate(String sqlStmt) throws ClassNotFoundException, SQLException {
    //Declare statement as null
    Statement stmt = null;
    try {
      //Connect to DB (Establish Oracle Connection)
      dbConnect();
      //Create Statement
      stmt  =  connection.createStatement();
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
