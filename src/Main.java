/**
 * Main.java
 * 11/30/2018
 * @author Philemon Petit-Frere
 * This is the driver class that will run the GUI
 * Also sets up the default products for the product to be used by other controllers.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectclasses.AudioPlayer;
import projectclasses.DbUtil;
import projectclasses.Global;
import projectclasses.MonitorType;
import projectclasses.MoviePlayer;
import projectclasses.Screen;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    /**
     * Set up a projectclasses productList, with 3 moviePlayers and 2 audioPlayers
     */
    Global.productList.add(new MoviePlayer("DBPOWER MK101",
        new Screen("720x480",40,22), MonitorType.LCD));

    AudioPlayer ap = new AudioPlayer("ipod Mini", "MP3");
    Global.productList.add(ap);
    Global.productList.add(new MoviePlayer("Pyle PDV156BK",
        new Screen("1366x768",40,22),MonitorType.LED));

    AudioPlayer ap2 = new AudioPlayer("Walkman", "WAV");
    Global.productList.add(ap2);
    Global.productList.add(new MoviePlayer("DBPOWER MK101",
        new Screen("720x480",40,22),MonitorType.LCD));
    Global.productList.clear();
    DbUtil.getStarted();
    Parent root = FXMLLoader.load(getClass().getResource("fxmlsandcontrollers/StartWindow.fxml"));
    primaryStage.setTitle("Home Window");
    primaryStage.setScene(new Scene(root, 478, 341));
    primaryStage.show(); //open

  }

  /**
   * Application entry point.
   * @param args args an array of command-line arguments for the application
   */
  public static void main(String[] args) {
    launch(args);
  }
}
