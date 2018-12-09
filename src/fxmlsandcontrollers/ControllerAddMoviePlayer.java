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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Global;
import sample.MonitorType;
import sample.MoviePlayer;
import sample.Screen;

public class ControllerAddMoviePlayer implements Initializable {

  /**
   * TextField to set the Screen Resolution.
   */
  @FXML private TextField textFieldRes;
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
   * This method will check the textFields for right inputs then will create a moviePlayer.
   * name and screemRes textfields must not be empty to be valid.
   * copies,refreshRate textfield textFields must be a non-negative int,
   * or the program will display an error.
   * If an invalid input is made, then it will be added to an alert message.
   * The alert message is a combination of errors message and will be displays as a popup.
   * 
   * @param  event is an MouseClick event
   */
  @FXML
  void handleAdd(ActionEvent event) {
    String name = txtFieldName.getText();
    //monitorTypeString to be converted to MonitorType enum
    String monitorTypeString = choiceBoxMonitorType.getValue();
    MonitorType monitorType;
    String screenRes = "";
    int refreshRate = -1;
    int responseTime = -1;

    String alertMessage = "";
    Boolean successCopies = true;

    if (monitorTypeString.equals("LCD")) {
      monitorType = MonitorType.LCD;
    } else {
      monitorType = MonitorType.LED;
    }

    if (txtFieldName.getText().equals("")) {
      alertMessage += "Name input is invalid\n";

    }
    if (textFieldRes.getText().equals("")) {
      alertMessage += "Screen resolution input is invalid \n";

    }
    int copies = 1;
    try {
      copies = Integer.parseInt(txtFieldCopy.getText());
    } catch (NumberFormatException exception) {
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage += "Copies input is invalid\n";
      // successCopies= false;
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



    if (alertMessage.equals("")) {
      if (copies > 1) {
        for (int i = 0; i < copies; i++) {
          //Loop to create copies
          MoviePlayer mp =
              new MoviePlayer(name,new Screen(screenRes,refreshRate,responseTime),monitorType);
          Global.productList.add(mp);
        }
      } else if (copies == 1) {
        MoviePlayer mp =
            new MoviePlayer(name,new Screen(screenRes,refreshRate,responseTime),monitorType);
        Global.productList.add(mp);
        showAlert(name + " was successfully created.");//Opens alert box

      }
      if (copies <= 0) {
        showAlert("No Movie Player was created, please enter number of copies.");//Opens alert box
      } else if (copies > 1) {
        showAlert(copies + " copies of " + name + " were created.");//Opens alert box
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
     */
    //create fields for ChoiceBox
    List<String> monitorTypeNames = new ArrayList<>();
    monitorTypeNames.add("LCD");
    monitorTypeNames.add("LED");
    ObservableList<String> monitorTypeList = FXCollections.observableArrayList(monitorTypeNames);
    choiceBoxMonitorType.setValue("LCD");
    choiceBoxMonitorType.setItems(monitorTypeList);
  }
}
