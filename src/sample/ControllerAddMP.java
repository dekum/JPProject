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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAddMP implements Initializable {
  Alert alert = new Alert(AlertType.INFORMATION);
  @FXML private TextField textFieldRes;
  @FXML private Label typeLabel;
  @FXML private TextField textFieldRefresh;
  @FXML private TextField textFieldResponse;
  @FXML
  TextField txtFieldName,txtFieldCopy;
  @FXML
  ChoiceBox<String> choiceBoxMediaType;

  void showAlert(String message){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();

  }
  @FXML
  void handleAdd (ActionEvent event){
    String name = txtFieldName.getText();
    String monitorTypeString=choiceBoxMediaType.getValue(); //to be converted to MoniterType enum
    MonitorType monitorType;
    String screenRes="";
    int refreshRate=-1;
    int responseTime=-1;

    String alertMessage= "";
    Boolean successCopies=true;
//    Boolean sucessScreenRes= true;
//    Boolean sucessRefreshRate= true;
//    Boolean sucessResponseTime= true;

    if(txtFieldName.getText().equals("")){
      alertMessage+="Name input is invalid\n";

    }
    if(textFieldRes.getText().equals("")){
      alertMessage+="Screen resolution input is invalid \n";

    }
    int copies=1;
    try {
      copies = Integer.parseInt(txtFieldCopy.getText());
    }
    catch (NumberFormatException exception){
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage+="Copies input is invalid\n";
     // successCopies= false;
    }
    try {
      refreshRate = Integer.parseInt(textFieldRefresh.getText());
    }
    catch (NumberFormatException exception){
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage+="Refresh Rate input is invalid\n";
      //sucessRefreshRate= false;
    }
    try {
      responseTime = Integer.parseInt(textFieldResponse.getText());
    }
    catch (NumberFormatException exception){
      //showAlert("Copies input is invalid");//Opens alert box
      alertMessage+="Response Time input is invalid\n";
     // sucessRefreshRate= false;
    }

    if (monitorTypeString.equals("LCD")){
      monitorType= MonitorType.LCD;
    }else
    {
      monitorType = MonitorType.LED;
    }

    if (alertMessage.equals("")){
      if (copies >1){
        for (int i = 0; i<copies; i++){

          MoviePlayer mp = new MoviePlayer(name,new Screen(screenRes,refreshRate,responseTime),monitorType);
          Global.productList.add(mp);


        }

      }else if (copies ==1){
        MoviePlayer mp = new MoviePlayer(name,new Screen(screenRes,refreshRate,responseTime),monitorType);
        Global.productList.add(mp);
        //alert = new Alert(AlertType.INFORMATION);

      }
      showAlert("Movie Player successfully created");//Opens alert box

    }else{
      showAlert(alertMessage);
    }
//    for (Product ap: Global.productList
//    ) {
//      System.out.println(ap.toString());
//    }


  }

  @FXML void handleExit(ActionEvent event){

    Stage stage = (Stage) txtFieldCopy.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    Loader.setLocation(getClass().getResource("StartWindow.fxml"));
    try {

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
        List<String> moniterTypeNames = new ArrayList<>();
    moniterTypeNames.add("LCD");
    moniterTypeNames.add("LED");

    ObservableList<String> moniterTypeList = FXCollections.observableArrayList(moniterTypeNames);
    choiceBoxMediaType.setValue("LCD");
    choiceBoxMediaType.setItems(moniterTypeList);
  }
}
