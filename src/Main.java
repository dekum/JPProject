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

  /**
   * This method starts the GUI.
   *
   * @param primaryStage the firsts stage.
   * @throws Exception a general exception.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    // clear productlist since it will be read from the database.
    Global.productList.clear();
    //DbUtil.populateProductList();
    Parent root = FXMLLoader.load(getClass().getResource("fxmlsandcontrollers/StartWindow.fxml"));
    primaryStage.setTitle("Home Window");
    primaryStage.setScene(new Scene(root, 476, 401));
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
