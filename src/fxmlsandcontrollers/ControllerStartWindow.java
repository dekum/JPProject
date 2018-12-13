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

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
   * Root or background of the fxml.
   */
  @FXML AnchorPane anchorPane;

  /**
   * Checkbox to control if delete button is disabled or enabled.
   */
  @FXML CheckBox checkBox;

  /**
   * Button to delete a product from database.
   */
  @FXML Button buttonDelete;

  /**
   * A mediaview that is used to store the mediaPlayer object.
   * This javafx element is called to access the mediaPlayer
   */
  @FXML MediaView mediaView;

  /**
   * This mediaPlayer is tied to a mediaView. It allows
   * the program to play "backgroundmsuic.mpe"
   * It contains a play() and pause() methods usesd in this program.
   */
  MediaPlayer mediaPlayer;



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

  /**
   * This method removes a selected Product from the database.
   * THis method is call after an event is clicked on, the checkbox is clicked
   * and the "delete product" button is clickeed.
   * THis generates a sql statement that will delete the product from
   * both the product list and  the class type table the product was.
   * After it is executed, the table is unfocused, and the table is updated.
   * Deleted products are permanently gone.
   *
   * @throws  ClassNotFoundException Requested classes are not found in classpath.
   * @throws SQLException An exception that provides information on a
   *                        database access error or other errors.
   */
  public  void deleteFromDataBase()
      throws SQLException, ClassNotFoundException {
    int serialNumberDelete = productClickedOn.getSerialNumber();

    String updateStmt =
        "DELETE FROM NEW_SCHEMA.PRODUCT WHERE "
            + "NEW_SCHEMA.PRODUCT.SERIALNUMBER=" + serialNumberDelete;
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
    alert.setContentText(productClickedOn.getName() + " deleted from records.");
    alert.showAndWait();
    Global.productList.remove(productClickedOn);
    ArrayList<Product> productList = Global.productList;
    ObservableList<Product> observableListProductList =
        FXCollections.observableArrayList(productList);
    tableViewProducts.setItems(observableListProductList);
    anchorPane.requestFocus();//unfocus the tableview


  }

  /**
   * This method is called when "Delete product" is clicked.
   * It will call the function that creates a sql query to deleted selected product.
   * To prevent accidental deletes, a checkbox will disable or enable delete button.
   * 
   * @param  event a moouseclick event.
   */
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
    
    stage = new Stage();
    stage.setTitle("Add Audio Player");
    if (Global.isUpdating) {
      stage.setTitle("Updating Audio PLayer");
    }
    Parent p = loader.getRoot();
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


    stage = new Stage();
    stage.setTitle("Add Movie Player");
    if (Global.isUpdating) {
      stage.setTitle("Updating Movie Player");
    }
    Parent p = loader.getRoot();
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
    DbUtil.populateProductList();
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
    buttonDelete.disableProperty().bind(checkBox.selectedProperty().not());
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

  /**
   * This method is called after "Update Product" button is clicked.
   * It will set a boolean "isUpdating" to be true which will change
   * borh AddAudioPlayer and addMoviePlayer controllers and fxml's
   * to update the product selected.
   * To prevent errors,
   * first the method checks if a product was selected.
   *
   */
  @FXML public void handleUpdate() {
    if (productClickedOn != null) {
      if (productClickedOn.getType().equals("AudioPlayer")) {
        Global.isUpdating = true;
        Global.productSelected = productClickedOn;
        //checkBugs give error this is an improper use, or bad practice
        // of a static field
        handleAudio(new ActionEvent());
      } else {
        Global.isUpdating = true;
        handleMovie(new ActionEvent());
      }
    }
  }

  /**
   * This method is when "Play/Puase Music" is clicked
   * This method will allow a embedded song to be played.
   * THis method uses a MediaPlayer and mediaView to play a mp3
   * to the fxml.
   * The Song will [play if the user moves between window to window.
   * The music is off on default, and the user can click the button again
   * to turm off the music if they desire.
   * Global is given the isPLaying boolean because it can store the toggle
   * variable even if windows change.
   * @param event a mouseclick Event.
   */
  @FXML public void playOrPauseMusic(ActionEvent event) {
    if (Global.isPlaying) {
      mediaView.getMediaPlayer().pause();
      Global.isPlaying = false;
    } else {
      Media media = new Media(new File("resources/backgroundmusic.mp3").toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      // mediaPlayer.setAutoPlay(true);
      mediaPlayer.play();
      mediaView.setMediaPlayer(mediaPlayer);
      Global.isPlaying = true;
    }
  }
}
