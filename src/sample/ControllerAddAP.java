package sample;

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

public class ControllerAddAP implements Initializable {
 @FXML
 TextField txtFieldName,txtFieldCopy,txtFieldAudioSpec;
  @FXML ChoiceBox<String> choiceBoxMediaType;

  void showAlert(String message){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();

  }

  @FXML void handleExit(ActionEvent event){

    Stage stage = (Stage) txtFieldAudioSpec.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    Loader.setLocation(getClass().getResource("StartWindow.fxml"));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();

    stage.setTitle("Home Screen");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window


  }
  @FXML
  void handleAdd (ActionEvent event){
    System.out.println("Happens");
    String name = txtFieldName.getText();
    String audioSpec = txtFieldAudioSpec.getText();
    Boolean sucess1= true;
    int copies=1;
    try {
      copies = Integer.parseInt(txtFieldCopy.getText());
    }
    catch (NumberFormatException exception){
    showAlert("Copies input is invalid");//Opens alert box
      sucess1= false;
    }
    Alert alert = new Alert(AlertType.INFORMATION);
    if (sucess1){
      if (copies >1){
        for (int i = 0; i<copies; i++){

          AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
          Global.productList.add(ap1);
          System.out.println("Worked");

        }

      }else if (copies ==1){
        AudioPlayer ap1 = new AudioPlayer(name,audioSpec);
        Global.productList.add(ap1);
         //alert = new Alert(AlertType.INFORMATION);

      }
      showAlert("Audio Player successfully created");//Opens alert box

    }
//    for (Product ap: Global.productList
//    ) {
//      System.out.println(ap.toString());
//    }


  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //This method runs first before window is opened

//    List<String> itenTypeNames = new ArrayList<>();
//    itenTypeNames.add("AUDIO");
//    itenTypeNames.add("VISUAL");
//    itenTypeNames.add("AUDIOMOBILE");
//    itenTypeNames.add("VISUALMOBILE");
//    List<String> itemTypeList = FXCollections.observableArrayList(itenTypeNames);
//    choiceBoxMediaType.setValue(1);
//    choiceBoxMediaType.setItems(itemTypeList);

    AudioPlayer ap = new AudioPlayer("ipod Mini", "MP3");
    Global.productList.add(ap);
    AudioPlayer ap2 = new AudioPlayer("Walkman", "WAV");
    Global.productList.add(ap2);

  }

  public void handleEscape(ActionEvent event) {
  }
}
