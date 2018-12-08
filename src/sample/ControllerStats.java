package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

public class ControllerStats implements Initializable {

  @FXML private TableColumn<StatsReport,Integer> colStatNumber;
  @FXML private TableColumn<StatsReport,String> colStatName;
  @FXML private TableView<StatsReport> tableViewStats;



  @FXML
  private void handleExit(ActionEvent event) {
    Stage stage = (Stage) tableViewStats.getScene().getWindow(); //Ask currentScene what window it is.
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ArrayList<Product> productList = Global.productList;
    ArrayList<StatsReport> statsList= new ArrayList<>();


    statsList.add( new StatsReport("Total Products",productList.size()));
    int apCounter=0;
    int mpCount=0;
    int lcdCounter=0;
    int ledCounter=0;
    int numOfCopies=0;
    int numOfOrginal=0;

    //ArrayList<Product> inputList = Arrays.asList(productList);
    Set inputSet = new HashSet(productList);
    numOfOrginal = inputSet.size();
    if(inputSet.size()< productList.size()){
      numOfCopies = productList.size() - inputSet.size();
      numOfOrginal = inputSet.size();
    }else if(inputSet.size()== productList.size()){


    }
    //System.out.println(productList.size() +"  "+ inputSet.size());




    for (Object o: productList
    ) {
      if (AudioPlayer.class.isInstance(o)){
        apCounter++;
      }
      if (MoviePlayer.class.isInstance(o)){

        MoviePlayer oMoviePlayer = (MoviePlayer)o;
        mpCount++;
        if (oMoviePlayer.getMonitorType() == MonitorType.LCD){
          lcdCounter++;
        }else {
          ledCounter++;
        }
      }

    }
    statsList.add( new StatsReport("Total Unique",numOfOrginal));
    statsList.add( new StatsReport("Total Duplicates",numOfCopies));
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
