/**
 * ControllerStats.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This controller is tied tot he StatsWindow.fxml
 * This class will display the statitics of the program in a Table
 * This class uses the Global.productList that is shared by all controllers
 * so the information is always up to date.
 */

package fxmlsandcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AudioPlayer;
import sample.Global;
import sample.MonitorType;
import sample.MoviePlayer;
import sample.Product;
import sample.StatsReport;

public class ControllerStats implements Initializable {

  /**
   * A tableView that is populated by StatusReport list.
   * This displays the Name of the Stat and the number of it.
   */
  @FXML private TableView<StatsReport> tableViewStats;
  /**
   * A tableColumn for TableViewStats.
   * This displays the stat number in its cells.
   */
  @FXML private TableColumn<StatsReport,Integer> colStatNumber;
  /**
   * A tableColumn for TableViewStats.
   * This displays the Stat name.
   */
  @FXML private TableColumn<StatsReport,String> colStatName;

  /**
   * This method will open the StartWidow.fxml window, also also closes current window.
   * 
   * @param  event is a mouseClick event.
   *
   */
  @FXML void handleExit(ActionEvent event) {
    Stage stage = (Stage) tableViewStats.getScene().getWindow();
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
    /*
    This method runs first
    It gets the the data from Global.productList which stores all the products
    Then counts AudioPlayers, Movie Players,LCDs,LED, copies and unique products
    Lastly it gives the data to the Stats tableview
     */
    ArrayList<Product> productList = Global.productList;

    int apCounter = 0;//Counts AudioPlayers
    int mpCount = 0;//Counts MoviePlayers
    int lcdCounter = 0;//Counts MoviePlayer objects with MoniterType LCD
    int ledCounter = 0;//Counts MoviePlayer objects with MoniterType LCD
    int numOfCopies = 0;//Counts number of products with the same Name
    int nubOfOriginal = 0;//Counts number of unique products.

    /*This checks how many are original or copies by creating a Set of the productList
    Since Set lists cannot contain duplicates, it will count how many are unique
    */
    Set inputSet = new HashSet(productList);
    nubOfOriginal = inputSet.size();
    if (inputSet.size() < productList.size()) {
      numOfCopies = productList.size() - inputSet.size(); //All - unique
      nubOfOriginal = inputSet.size(); //numOfOrigoma;
    }

    for (Object o: productList
    ) {
      if (AudioPlayer.class.isInstance(o)) {
        //If current object pointed to is a AudioPlayer add to Count
        apCounter++;
      }
      if (MoviePlayer.class.isInstance(o)) {
        //If current object pointed to is a MoviePlayer add to Count
        MoviePlayer objectMoviePlayer = (MoviePlayer)o;
        mpCount++;
        if (objectMoviePlayer.getMonitorType() == MonitorType.LCD) {
          //if current MoviePlayer object is LCD add to count
          lcdCounter++;
        } else {
          ledCounter++;
        }
      }

    }
    //Create Stats objects with Name and number
    ArrayList<StatsReport> statsList = new ArrayList<>();
    statsList.add(new StatsReport("Total Products",productList.size()));
    statsList.add(new StatsReport("Total Unique",nubOfOriginal));
    statsList.add(new StatsReport("Total Duplicates",numOfCopies));
    statsList.add(new StatsReport("Total Audio Players",apCounter));
    statsList.add(new StatsReport("Total Movie Players",mpCount));
    statsList.add(new StatsReport("Total LCDs",lcdCounter));
    statsList.add(new StatsReport("Total LEDs",ledCounter));


    ObservableList<StatsReport> observableStatsList = FXCollections.observableArrayList(statsList);
    //set Cellfactory for name column
    colStatName.setCellValueFactory(cellData -> cellData.getValue().getStatNameProperty());
    colStatNumber.setCellValueFactory(
        new PropertyValueFactory<StatsReport, Integer>("statNumber"));
    //colStatNumber.setCellValueFactory(cellData -> cellData.getValue().getStatNumberProperty());
    tableViewStats.setItems(observableStatsList);
  }
}
