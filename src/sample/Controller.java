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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements  Initializable {
@FXML
  TableView<Product> tableViewProducts;
@FXML
  TableColumn<Product,String> colName,colType;

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
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    for (Product ap1: Global.productList
    ) {
      System.out.println(ap1.toString());
    }
    ArrayList<Product> productList = Global.productList;
    ObservableList<Product> observableListProductList = FXCollections.observableArrayList(productList);
    //set Cellfactory for name column
    colName.setCellValueFactory(cellData -> cellData.getValue().getNameSSPProperty());
    colType.setCellValueFactory(cellData -> cellData.getValue().getTypeSPProperty());
tableViewProducts.setItems(observableListProductList);

  }
}
