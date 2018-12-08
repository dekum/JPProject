package fxmlsandcontrollers;

/**
 * ControllerAddAudioPlayer.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This class is a controller that is connected to the AddAudioPlayer.fxml
 * A user can create a AudioPlyaer product by entering details in the textfiles
 * By Pressing "Add", the programs checks for valid inputs and then will create the product.
 */

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AudioPlayer;
import sample.Global;

public class ControllerAddAudioPlayer implements Initializable {
  /**
   * This controller sets functionality to validate user inputs and create AudioPlayer object.
   */
  @FXML
  private TextField txtFieldName;
  @FXML
  private TextField txtFieldCopy;
  @FXML
  private TextField txtFieldAudioSpec;

  /**
   * This method displays a popup with the Message passed in the parameter.
   * @param message is a String passed by other method to be displayed to the user
   */
  void showAlert(String message) {

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog.");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();

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
   * @param event is an MouseClick event
   */
  @FXML
  void handleAdd(ActionEvent event) {
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
      if (copies > 1) {
        for (int i = 0; i < copies; i++) {
          //Loop to create copies
          AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
          Global.productList.add(ap1);
        }
      } else if (copies == 1) {
        AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
        Global.productList.add(ap1);
        //alert = new Alert(AlertType.INFORMATION);
        showAlert(name + " was sucessfully created.");//Opens alert box

      }
      if (copies == 0 || copies < 0) {
        showAlert("No Audio Player was created, please enter number of copies.");//Opens alert box
      } else if (copies > 1) {
        showAlert(copies + " copies of " + name + " were created.");//Opens alert box
      }
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
