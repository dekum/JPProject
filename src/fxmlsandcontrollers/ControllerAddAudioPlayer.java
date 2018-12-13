/**
 * ControllerAddAudioPlayer.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This class is a controller that is connected to the AddAudioPlayer.fxml
 * A user can create a AudioPlyaer product by entering details in the textfiles
 * By Pressing "Add", the programs checks for valid inputs
 * and then will create the product.
 */

package fxmlsandcontrollers;

import java.awt.Checkbox;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projectclasses.AudioPlayer;
import projectclasses.DbUtil;
import projectclasses.Global;

public class ControllerAddAudioPlayer implements Initializable {

  /**
   * Image displayed in the top right.
   */
  @FXML
  private ImageView imageView;
  /**
   * Txtfield to set the AudioPlayer Name.
   */
  @FXML
  private TextField txtFieldName;
  /**
   * Txtfield to set the number of Audio Player objects to create.
   */
  @FXML
  private TextField txtFieldCopy;
  /**
   * Txtfield to set the AudioPlayer's Audio Specification .
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
   * Checkbox to set datepicker to current date.
   */
  @FXML private CheckBox checkBox;

  /**
   * Label to show user if they are addoing or updating a audioplayer.
   */
  @FXML Label typeLabel;
  /**
   * A button that is hidden, but it performs the update function when visible.
   * When user clicks update product, the update button is visible.
   */
  @FXML Button buttonUpdate;

  /**
   * The button that has a click event that creates a audioPlayer object.
   */
  @FXML private Button buttonAdd = new Button();

  /**
   * This method displays a popup with the Message passed in the parameter.
   *
   * @param  message is a String passed by other method to be displayed to the user
   */
  void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog.");
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
   * @throws  SQLException An exception that provides information on a 
   *                        database access error or other errors.
   */
  public  int getMaxSerialNumber() throws ClassNotFoundException, SQLException {

    // String selectStmt = "SELECT * FROM NEW_SCHEMA.PRODUCT WHERE  "
    //     + "NEW_SCHEMA.PRODUCT.SERIALNUMBER=(SELECT max(NEW_SCHEMA.PRODUCT.SERIALNUMBER)"
    //     + " FROM NEW_SCHEMA.PRODUCT) ";
    int maxSerialNumber = 0;
    String selectStmt = "SELECT * FROM NEW_SCHEMA.PRODUCT "
        + "ORDER BY SERIALNUMBER desc";
    try {
      //Get ResultSet from dbExecuteQuery method
      ResultSet rsAuth = DbUtil.dbExecuteQuery(selectStmt);
      if (rsAuth.next()) {
        System.out.println(rsAuth.getInt("SERIALNUMBER") + " " + rsAuth.getString("NAME"));
        maxSerialNumber = rsAuth.getInt("SERIALNUMBER");
        rsAuth.close();
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
   * A method to add a product to AudioPlayer and Product tables.
   * IMPORTANT: Use single quotes for update querty, double quotes will only cause errrors.
   * The method is called after the HandleAdd verifies to see if inputs are valid.
   * This method the dbExecuteUpdate of the DbUtil class.
   * First this method calls getMaxSerialNumber to get the highest serial number
   * and then increments by one.
   * First it inserts a new row of data into the Product table.
   * Then it inserts a new row of data into the AudioPlayer table.
   *
   * @param  name name of the product inputted by user.
   * @param audiSpec audio Specification inputted by user.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  public  void addToDb(String name, String audiSpec)
      throws SQLException, ClassNotFoundException {
    int currentSerialNumber = 1;
    currentSerialNumber = getMaxSerialNumber() + 1;
    LocalDate dateFromDatePicker =  datePickerManufactured.getValue();
    Color color1 = colorPicker.getValue();
    String colorPicked = color1.toString();


    String updateStmt =
        "INSERT INTO NEW_SCHEMA.PRODUCT "
            + "(NEW_SCHEMA.PRODUCT.NAME, NEW_SCHEMA.PRODUCT.SERIALNUMBER,TYPE,"
            + " NEW_SCHEMA.PRODUCT.MANUFACTUREDON, NEW_SCHEMA.PRODUCT.COLOR) "
            + "VALUES ('" + name + "',"  + currentSerialNumber + ",\'AP\', '"
            + dateFromDatePicker + "','" + colorPicked +  "')";
    System.out.println(updateStmt);

    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt);
      //checkBugs suggests using a peepared statement for effcientcy
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }

    String updateStmt2 =
        "INSERT INTO NEW_SCHEMA.AUDIOPLAYER "
            + "(NEW_SCHEMA.AUDIOPLAYER.NAME, NEW_SCHEMA.AUDIOPLAYER.SERIALNUMBERAP,"
            + "NEW_SCHEMA.AUDIOPLAYER.AUDIOSPECIFICATION) "
            + "VALUES ('" + name + "',"  + currentSerialNumber + ",'" +  audiSpec + "')";
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
   *This method updates a audioplyer objec in the productlist.
   *This method is called after the inputs are validated from the user.
   * This method takes those inputs and changes the database data to match it.
   * Using a Update quuery and the dbUtil Class the database is updated with the changes.
   *
   * @param  name name of the product inputted by user.
   * @param  audiSpec audio Specification inputted by user.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  public  void updateToDb(String name, String audiSpec)
      throws SQLException, ClassNotFoundException {
    //int currentSerialNumber = 1;
    //currentSerialNumber = getMaxSerialNumber() + 1;
    LocalDate dateFromDatePicker =  datePickerManufactured.getValue();
    Color color1 = colorPicker.getValue();
    String colorPicked = color1.toString();
    int serialNumber = Global.productSelected.getSerialNumber();


    String updateStmt =
        "UPDATE NEW_SCHEMA.PRODUCT "
            + "SET  NAME='" + name + "',  TYPE = 'AP',"
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

  }

  /**
   * This method will open the StartWidow.fxml window, also also closes current window.
   * @param event is a mouseClick event.
   *
   */
  @FXML void handleExit(ActionEvent event) {

    Stage stage = (Stage) txtFieldAudioSpec.getScene().getWindow(); //get the current window
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader loader = new FXMLLoader();

    //Load a Url to the next window
    loader.setLocation(getClass().getResource("StartWindow.fxml"));
    try {
      loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(ControllerStartWindow.class.getName()).log(Level.SEVERE, null, ex);

    }
    Global.isUpdating = false; //Not updating anymore
    Parent p = loader.getRoot();
    stage = new Stage();
    stage.setTitle("Home Screen");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window


  }

  /**
   * This method will check the textFields for right inputs then will create a audioPlayer.
   * name and audioSpec textfields must not be empty to be valid
   * copies textfield must be a non-negative int, or the program will display an error
   * @param  event is an MouseClick event
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   * @throws ClassNotFoundException Requested classes are not found in classpath.
   */
  @FXML
  void handleAdd(ActionEvent event) throws SQLException, ClassNotFoundException {

    String name = txtFieldName.getText();
    String audioSpec = txtFieldAudioSpec.getText();
    Boolean success1 = true; //Boolean for if copies input is an int
    int copies = 1;
    try {
      copies = Integer.parseInt(txtFieldCopy.getText());
    } catch (NumberFormatException exception) {
      showAlert("Copies input is invalid");
      success1 = false;
    }
    if (txtFieldName.getText().equals("") || txtFieldAudioSpec.getText().equals("")) {
      showAlert("Invalid input for Name or Audio Specification");
      success1 = false;
    }
    //If successful then create new audioPlayer
    if (success1) {
      if (Global.isUpdating) {
        updateToDb(name,audioSpec);
        showAlert("Product successfully updated.");
      } else {
        if (copies > 1) {
          for (int i = 0; i < copies; i++) {
            //Loop to create copies
            AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
            Global.productList.add(ap1);
            addToDb(name, audioSpec);
          }
        } else if (copies == 1) {
          AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
          Global.productList.add(ap1);
          addToDb(name, audioSpec);
          //alert = new Alert(AlertType.INFORMATION);
          showAlert(name + " was successfully created.");//Opens alert box

        }
        if (copies == 0 || copies < 0) {
          showAlert("No Audio Player was created, please enter number of copies.");//Opens alert box
        } else if (copies > 1) {
          showAlert(copies + " copies of " + name + " were created.");//Opens alert box
        }
      }
    }

  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (Global.isUpdating) {
      txtFieldCopy.setDisable(true);
      typeLabel.setText("Update AudiPlayer");
      buttonAdd.setVisible(false);
      buttonUpdate.setVisible(true);

    }
    //Image image = new Image("..\\..\\images\\audioPlayer.jpg");


    Image image = new Image("fxmlsandcontrollers/audioPlayer.jpg");
    imageView.setImage(image);
    datePickerManufactured.setValue(LocalDate.now());
  }
}
