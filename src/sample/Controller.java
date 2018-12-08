package sample;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller implements  Initializable {
  Product productClickedOn;
@FXML
  TableView<Product> tableViewProducts;
@FXML
  TableColumn<Product,String> colName,colType;

public void handleMoreInfo(ActionEvent event){

  Alert alert = new Alert(AlertType.INFORMATION);
  alert.setTitle("Information Dialog");
  alert.setHeaderText(null);
  alert.setContentText(productClickedOn.toString()); //print product toSTring

  alert.showAndWait();


}

  public void handleAudio(ActionEvent event) {
    Stage stage = (Stage) tableViewProducts.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    Loader.setLocation(getClass().getResource("AddAudioPLayer.fxml"));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();

    stage.setTitle("Add Audio Player");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window
  }

  public void handleMovie(ActionEvent event) {
    Stage stage = (Stage) tableViewProducts.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    Loader.setLocation(getClass().getResource("AddMoviePlayer.fxml"));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();

    stage.setTitle("Add Audio Player");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window

  }

  @FXML void handleStats(ActionEvent event){
    Stage stage = (Stage) tableViewProducts.getScene().getWindow(); //Ask currentScene what window it is.
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    Loader.setLocation(getClass().getResource("StatsWindow.fxml"));
    try {
      // Loader.setController(guestController); GuestMenuHome already has a controller so no need to set a new one.
      Loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = Loader.getRoot();
    stage = new Stage();

    stage.setTitle("Stats Window");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

//    for (Product ap1: Global.productList
//    ) {
//      System.out.println(ap1.toString());
//    }
    ArrayList<Product> productList = Global.productList;
    ObservableList<Product> observableListProductList = FXCollections.observableArrayList(productList);
    //set Cellfactory for name column
    colName.setCellValueFactory(cellData -> cellData.getValue().getNameSSPProperty());
    colType.setCellValueFactory(cellData -> cellData.getValue().getTypeSPProperty());
tableViewProducts.setItems(observableListProductList);

    tableViewProducts.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        productClickedOn = tableViewProducts.getSelectionModel().getSelectedItem(); //set the current room clicked on

      }
    });



  }
  @FXML void handleQuit(ActionEvent event){
    /**
     * Exit Program, closes window
     */
    System.out.println("Goodbye");
    Stage stageExit = (Stage) tableViewProducts.getScene().getWindow();
    stageExit.close();

  }

}
