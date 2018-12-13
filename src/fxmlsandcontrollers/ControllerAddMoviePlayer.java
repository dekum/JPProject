/**
 * ControllerAddMoviePlayer.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This controller is connected to AddMoviePlayer.fxml
 * A user can create a MoviePlayer product by entering details in the textfiles
 * By Pressing "Add", the programs checks for valid inputs and then will create the product.
 */

package fxmlsandcontrollers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import projectclasses.DbUtil;
import projectclasses.Global;
import projectclasses.MonitorType;
import projectclasses.MoviePlayer;
import projectclasses.Screen;

public class ControllerAddMoviePlayer implements Initializable {

  /**
   * Button activates update function.
   */
  public Button buttonUpdate;
  /**
   * Label to shoe user if they are adding or updating.
   */
  @FXML private Label typeLabel;
  /**
   * Button that activates adding a movie player.
   */
  @FXML private Button buttonAdd;
  /**
   * Image displayed in the top right.
   */
  @FXML  private ImageView imageView;

  /**
   * TextField to set the refresh rate.
   */
  @FXML private TextField textFieldRefresh;
  /**
   * TextField to set the response time.
   */
  @FXML private TextField textFieldResponse;
  /**
   * TextField to set the movie player name.
   */
  @FXML private TextField txtFieldName;
  /**
   * TextField to set number of copies to create.
   */
  @FXML private TextField txtFieldCopy;
  /**
   * ChoiceBox to set the monitor type enum.
   */
  @FXML private ChoiceBox<String> choiceBoxMonitorType;

  /**
   * Textfield to set audio specification.
   */
  @FXML
  private TextField txtFieldAudioSpec;

  /**
   * A datepicker to set the date this product was manufactured .
   * Default is today's date.
   */
  @FXML private DatePicker datePickerManufactured;
  /**
   * Javafx Colorpicker to set the color.
   */
  @FXML private ColorPicker colorPicker;
  /**
   * Slider that sets this screen resolution.
   * The slider is formatted, so it snaps to ticks.
   * The ticks are also changed to show resolution such as "1900x1080p"
   */
  @FXML private Slider sliderResolution;

  /**
   * This method displays a popup with the Message passed in the parameter.
   * 
   * @param  message is a String passed by other method to be displayed to the user
   */
  void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();
  }

  /**
   * A method to get the highest serial number in the database.
   * Used to make sure the serial number, isn't replicated when inserting a new product
   * This method searches the database for a querry that looks for the data
   * with the highest serial number, and returns that index
   * Then addToDb adds to index by 1 when inserting a new product.
   *
   * @return  maxSerialNumber an int with the highest number in the db.
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
  public  int getMaxSerialNumber() throws ClassNotFoundException, SQLException {

    String selectStmt = "SELECT * FROM NEW_SCHEMA.PRODUCT WHERE  "
        + "NEW_SCHEMA.PRODUCT.SERIALNUMBER=(SELECT max(NEW_SCHEMA.PRODUCT.SERIALNUMBER)"
        + " FROM NEW_SCHEMA.PRODUCT) ";
    int maxSerialNumber = 0;


    try {
      //Get ResultSet from dbExecuteQuery method
      ResultSet rsAuth = DbUtil.dbExecuteQuery(selectStmt);
      if (rsAuth.next()) {
        System.out.println(rsAuth.getInt("SERIALNUMBER") + " " + rsAuth.getString("NAME"));
        maxSerialNumber = rsAuth.getInt("SERIALNUMBER");
      }


    } catch (SQLException e) {
      System.out
          .println("While searching an product with  id, an error occurred: " + e);
      //Return exception
      throw e;
    }
    return maxSerialNumber;
  }

  /**
   * A method to add a product to MoviePlayer and Product tables.
   * IMPORTANT: Use single quotes for update querty, double quotes will only cause errrors.
   * The method is called after the HandleAdd verifies to see if inputs are valid.
   * This method the dbExecuteUpdate of the DbUtil class.
   * First this method calls getMaxSerialNumber to get the highest serial number
   * and then increments by one.
   * First it inserts a new row of data into the Product table.
   * Then it inserts a new row of data into the MoviePlayer table.
   * Product table has col called which lets the program know to join with which table.
   *
   * @param  name name of the product inputted by user.
   * @param screen the screen of the movieplayer containing 3 fields used in this method.
   * @param monitorType the MonitorType enum for the created movieplayer.
   *
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  /**
   * A method that takes the value of the sliderResolution and converts it to the string.
   *
   * @return the screen resolution, based on the sliderResolution value.
   */
  public String getResoultion() {
    double n = sliderResolution.getValue();
    if (n == 360) {
      return "480x360p";
    }
    if (n == 540) {
      return "960x540p";
    }
    if (n == 720) {
      return "1280x720p";
    }
    if (n == 900) {
      return "1600x900p";
    }
    if (n == 1080) {
      return "1920x1080p";
    }
    return "1920x1080p";//default value which shouldn't ever happen.
  }

  /**
   * A method to add a product to MoviePlayer and Product tables.
   * IMPORTANT: Use single quotes for update querty, double quotes will only cause errrors.
   * The method is called after the HandleAdd verifies to see if inputs are valid.
   * This method the dbExecuteUpdate of the DbUtil class.
   * First this method calls getMaxSerialNumber to get the highest serial number
   * and then increments by one.
   * First it inserts a new row of data into the Product table.
   * Then it inserts a new row of data into the MoviePlayer table.
   *
   * @param  name name of the product inputted by user.
   * @param  screen a screen object with valid inputs by user
   * @param  monitorType MonitorType enum that is passed.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  public void addToDb(String name, Screen screen, MonitorType monitorType)
      throws SQLException, ClassNotFoundException {
    int currentSerialNumber = 1;
    currentSerialNumber = getMaxSerialNumber() + 1;
    LocalDate dateFromDatePicker =  datePickerManufactured.getValue();
    Color color1 = colorPicker.getValue();
    String colorPicked = color1.toString();

    String screenRes = screen.getResolution();
    int refreshRate = screen.getRefreshRate();
    int responseTime = screen.getResponseTime();

    String updateStmt =
        "INSERT INTO NEW_SCHEMA.PRODUCT "
            + "(NEW_SCHEMA.PRODUCT.NAME, NEW_SCHEMA.PRODUCT.SERIALNUMBER,TYPE,"
            + " NEW_SCHEMA.PRODUCT.MANUFACTUREDON, NEW_SCHEMA.PRODUCT.COLOR) "
            + "VALUES ('" + name + "',"  + currentSerialNumber + ",\'MP\', '"
            + dateFromDatePicker + "','" + colorPicked +  "')";
    System.out.println(updateStmt);

    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }

    String updateStmt2 =
        "INSERT INTO NEW_SCHEMA.MOVIEPLAYER "
            + "(NEW_SCHEMA.MOVIEPLAYER.NAME, NEW_SCHEMA.MOVIEPLAYER.SERIALNUMBERMP,"
            + "NEW_SCHEMA.MOVIEPLAYER.SCREENRESOLUTION, NEW_SCHEMA.MOVIEPLAYER.RESPONSETIME,"
            + "NEW_SCHEMA.MOVIEPLAYER.REFRESHRATE, NEW_SCHEMA.MOVIEPLAYER.MONITORTYPE ) "
            + "VALUES ('" + name + "',"  + currentSerialNumber + ",'" +  screenRes
            + "'," + responseTime + "," + refreshRate + ", '" + monitorType + " ')";
    System.out.println(updateStmt2);

    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt2);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  /**
   *This method updates a movieplayer object in the productlist.
   *This method is called after the inputs are validated from the user.
   * This method takes those inputs and changes the database data to match it.
   * Using a Update query and the dbUtil Class the database is updated with the changes.
   *
   * @param  name name of the product inputted by user.
   * @param  screen a screen object with valid inputs by user
   * @param  monitorType MonitorType enum that is passed.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  public  void updateToDb(String name, Screen screen, MonitorType monitorType)
      throws SQLException, ClassNotFoundException {
    //int currentSerialNumber = 1;
    //currentSerialNumber = getMaxSerialNumber() + 1;
    LocalDate dateFromDatePicker =  datePickerManufactured.getValue();
    Color color1 = colorPicker.getValue();
    String colorPicked = color1.toString();
    int serialNumber = Global.productSelected.getSerialNumber();

    String screenRes = screen.getResolution();
    int refreshRate = screen.getRefreshRate();
    int responseTime = screen.getResponseTime();

    String updateStmt =
        "UPDATE NEW_SCHEMA.PRODUCT "
            + "SET  NAME='" + name + "',  TYPE = 'MP',"
            + "  MANUFACTUREDON = '" + dateFromDatePicker + "', COLOR='"
            + colorPicked + "' WHERE  SERIALNUMBER=" + serialNumber;

    System.out.println(updateStmt);


    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }

    String updateStmt2 =
        "UPDATE NEW_SCHEMA.MOVIEPLAYER "
            + "SET  NAME='" + name
            + "',  MONITORTYPE  = '" + monitorType
            + "', SCREENRESOLUTION = '" + screenRes
            + "', RESPONSETIME = " + responseTime
            + ", REFRESHRATE = " + refreshRate
            + "WHERE  SERIALNUMBERMP=" + serialNumber;

    System.out.println(updateStmt2);


    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt2);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }


  }


  /**
   * This method will check the textFields for right inputs then will create a moviePlayer.
   * name and screemRes textfields must not be empty to be valid.
   * copies,refreshRate textfield textFields must be a non-negative int,
   * or the program will display an error.
   * If an invalid input is made, then it will be added to an alert message.
   * The alert message is a combination of errors message and will be displays as a popup.
   *
   * @param  event is an MouseClick event
   * @throws  SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   */
  @FXML
  void handleAdd(ActionEvent event) throws SQLException, ClassNotFoundException {
    String name = txtFieldName.getText();
    //monitorTypeString to be converted to MonitorType enum
    String monitorTypeString = choiceBoxMonitorType.getValue();
    MonitorType monitorType;
    String screenRes = getResoultion();
    int refreshRate = -1;
    int responseTime = -1;

    String alertMessage = "";


    if (monitorTypeString.equals("LCD")) {
      monitorType = MonitorType.LCD;
    } else {
      monitorType = MonitorType.LED;
    }

    if (txtFieldName.getText().equals("")) {
      alertMessage += "Name input is invalid\n";
    } else {
      name = txtFieldName.getText();
    }
    int copies = 1;
    try {
      copies = Integer.parseInt(txtFieldCopy.getText());
    } catch (NumberFormatException exception) {
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage += "Copies input is invalid\n";

    }
    try {
      refreshRate = Integer.parseInt(textFieldRefresh.getText());
    } catch (NumberFormatException exception) {
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage += "Refresh Rate input is invalid\n";
      //sucessRefreshRate= false;
    }
    try {
      responseTime = Integer.parseInt(textFieldResponse.getText());
    } catch (NumberFormatException exception) {
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage += "Response Time input is invalid\n";
      // sucessRefreshRate= false;
    }


    //is alert message is empty, that means all inputs are valid.
    if (alertMessage.equals("")) {
      if (Global.isUpdating) {
        updateToDb(name, new Screen(screenRes, refreshRate, responseTime), monitorType);
        showAlert("Product successfully updated.");
      } else {
        if (copies > 1) {
          for (int i = 0; i < copies; i++) {
            //Loop to create copies
            MoviePlayer mp =
                new MoviePlayer(name, new Screen(screenRes, refreshRate, responseTime),
                    monitorType);
            Global.productList.add(mp);
            addToDb(name, new Screen(screenRes, refreshRate, responseTime), monitorType);
          }
        } else if (copies == 1) {
          MoviePlayer mp =
              new MoviePlayer(name, new Screen(screenRes, refreshRate, responseTime), monitorType);
          Global.productList.add(mp);
          addToDb(name, new Screen(screenRes, refreshRate, responseTime), monitorType);
          showAlert(name + " was successfully created.");//Opens alert box

        }
        if (copies <= 0) {
          showAlert("No Movie Player was created, please enter number of copies.");//Opens alert box
        } else if (copies > 1) {
          showAlert(copies + " copies of " + name + " were created.");//Opens alert box
        }
      }
    } else {
      showAlert(alertMessage);
    }

  }

  /**
   * This method will open the StartWidow.fxml window, also also closes current window.
   * 
   * @param  event is a mouseClick event.
   *
   */
  @FXML void handleExit(ActionEvent event) {


    Stage stage = (Stage) txtFieldCopy.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    loader.setLocation(getClass().getResource("StartWindow.fxml"));
    try {

      loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(ControllerStartWindow.class.getName()).log(Level.SEVERE, null, ex);

    }
    Global.isUpdating = false;
    Parent p = loader.getRoot();
    stage = new Stage();

    stage.setTitle("Home Screen");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window

  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /**
     * This method is called first before window loads.
     * This populates the choiceBox, and set default value
     * Akso sets the datepciker to today's date
     * The sliderResolution is also set
     */
    if (Global.isUpdating) {
      txtFieldCopy.setDisable(true);
      typeLabel.setText("Update AudiPlayer");
      buttonAdd.setVisible(false);
      buttonUpdate.setVisible(true);
      MoviePlayer currentMoviePlayer = (MoviePlayer) Global.productSelected;

      txtFieldName.setText(currentMoviePlayer.getName());
      datePickerManufactured.setValue(currentMoviePlayer.getLocalDateManufactured());
      colorPicker.setValue(Color.valueOf(currentMoviePlayer.getColor()));
      Screen currentScreen = currentMoviePlayer.getScreen();
      textFieldRefresh.setText(String.valueOf(currentScreen.getRefreshRate()));
      textFieldResponse.setText(String.valueOf(currentScreen.getResponseTime()));



    }


    //create fields for ChoiceBox
    List<String> monitorTypeNames = new ArrayList<>();
    monitorTypeNames.add("LCD");
    monitorTypeNames.add("LED");
    ObservableList<String> monitorTypeList = FXCollections.observableArrayList(monitorTypeNames);
    choiceBoxMonitorType.setValue("LCD");
    choiceBoxMonitorType.setItems(monitorTypeList);
    datePickerManufactured.setValue(LocalDate.now());

    Image image = new Image("fxmlsandcontrollers/moviePlayer.jpg");
    imageView.setImage(image);


    sliderResolution.setLabelFormatter(new StringConverter<Double>() {
      @Override
      public String toString(Double n) {
        if (n == 360) {
          return "480x360p";
        }
        if (n == 540) {
          return "960x540p";
        }
        if (n == 720) {
          return "1280x720p";
        }
        if (n == 900) {
          return "1600x900p";
        }
        if (n == 1080) {
          return "1920x1080p";
        }
        return "";
      }

      @Override
      public Double fromString(String s) {
        switch (s) {
          case "480x360":
            return 0d;
          case "960x540":
            return 1d;
          case "1280x720":
            return 2d;
          case "1600x900":
            return 4d;
          case "1920x1080":
            return 5d;

          default:
            return 5d;
        }
      }
    });

  }
}
