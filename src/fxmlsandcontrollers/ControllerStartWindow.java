/**
 * ControllerStartWindow.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This controller is connected to the Start Window.fxml
 * The first window the user sees.
 * The main function of this controller is to set the tableView and the buttons to add products
 * The tableViewProducts displays the name and type of products created in this program
 *
 */

package fxmlsandcontrollers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projectclasses.DbUtil;
import projectclasses.Global;
import projectclasses.Product;

public class ControllerStartWindow implements  Initializable {
  /**
   * A Product object stores the reference to the product was clicked in the TableView of Products.
   */
  Product productClickedOn; //Clicked product in the tableView
  /**
   * A tableView populated from the products of the productList.
   * This tableView displays the product Name and type in it's columns.
   */
  @FXML
  TableView<Product> tableViewProducts; //table for Products
  /**
   * A tableView column that displays the name of products in each cell.
   */
  @FXML
  TableColumn<Product,String> colName; //Displays name of Product
  /**
   * A tableView column that displays the class type of product in each cell.
   */
  @FXML
  TableColumn<Product,String> colType; //Displays either AudioPlyaer or MoviePlayer

  /**
   * This method displays a popup with window with the toString of the product user clicks.
   *
   * @param  event is a mouseClick event
   */
  public void handleMoreInfo(ActionEvent event) {
    //Check if a product was clicked on
    if (productClickedOn != null) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText(productClickedOn.toString()); //print product toSTring
      alert.showAndWait();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Please click on a product."); //error message
      alert.showAndWait();
    }
  }

  public  void deleteFromDataBase()
      throws SQLException, ClassNotFoundException {
    int serialNumberDelete =productClickedOn.getSerialNumber();

    String updateStmt=
        "DELETE FROM NEW_SCHEMA.PRODUCT WHERE "
            + "NEW_SCHEMA.PRODUCT.SERIALNUMBER="+serialNumberDelete;
    System.out.println(updateStmt);

    //Execute DELETE operation
    try {
      DbUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText(productClickedOn.getName()+"deleted from records."); //print product toSTring
    alert.showAndWait();
    Global.productList.remove(productClickedOn);


  }

  @FXML
  void handleDelete(ActionEvent event) {
    if (productClickedOn != null) {
      try {
        deleteFromDataBase();
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Please click on a product."); //error message
      alert.showAndWait();
    }

  }

  /**
   * This method will open the AddAudioPlayer.fxml window, also also closes current window.
   *
   * @param  event is a mouseClick event.
   */
  public void handleAudio(ActionEvent event) {
    Stage stage = (Stage) tableViewProducts.getScene().getWindow(); //get the current window
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader loader = new FXMLLoader();

    try {
      DbUtil.dbDisconnect();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //Load a Url to the next window
    loader.setLocation(getClass().getResource("AddAudioPlayer.fxml"));
    try {
      loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(ControllerAddAudioPlayer.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = loader.getRoot();
    stage = new Stage();

    stage.setTitle("Add Audio Player");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window
  }

  /**
   * This method handleMovie will open the AddMoviePlayer.fxml window.
   * Also also closes current window.
   *
   * @param  event is a mouseClick event.
   *
   */
  public void handleMovie(ActionEvent event) {

    Stage stage = (Stage) tableViewProducts.getScene().getWindow(); //get the current window
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader loader = new FXMLLoader();

    //Load a Url to the next window
    loader.setLocation(getClass().getResource("AddMoviePlayer.fxml"));
    try {
      loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(ControllerAddMoviePlayer.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = loader.getRoot();
    stage = new Stage();

    stage.setTitle("Add Movie Player");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window

  }

  @FXML void handleStats(ActionEvent event) {
    /**
     * This method will open the StatsWindow.fxml window, also also closes current window.
     *
     * @param  event is a mouseClick event.
     */
    //Find currentScene of what window it is.
    Stage stage = (Stage) tableViewProducts.getScene().getWindow();
    stage.close(); //Close current Window

    //Loads FXML Loader
    FXMLLoader loader = new FXMLLoader();

    //Load a Url to the HomePage Fxml
    loader.setLocation(getClass().getResource("StatsWindow.fxml"));
    try {

      loader.load(); //Loads
    } catch (IOException ex) {
      Logger.getLogger(ControllerStartWindow.class.getName()).log(Level.SEVERE, null, ex);

    }

    Parent p = loader.getRoot();
    stage = new Stage();

    stage.setTitle("Stats Window");
    stage.setScene(new Scene(p));
    stage.show(); //Opens new Window
  }


  @Override public void initialize(URL location, ResourceBundle resources) {
    /**
     * This method runs first, it populates the Product table, default vales in Main.java
     * The columns receive the data from the productList of Global Class
     */
    ArrayList<Product> productList = Global.productList;
    ObservableList<Product> observableListProductList =
        FXCollections.observableArrayList(productList);
    //set CellFactory for name column
    colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    colType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
    tableViewProducts.setItems(observableListProductList);

    tableViewProducts.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //set the current room clicked on
        productClickedOn = tableViewProducts.getSelectionModel().getSelectedItem();

      }
    });

  }

  /**
   * Quits the program by closing the program.
   *
   * @param  event is a mouseClick event.
   */
  @FXML void handleQuit(ActionEvent event) {
    System.out.println("Goodbye");
    Stage stageExit = (Stage) tableViewProducts.getScene().getWindow();
    stageExit.close();
  }
}
