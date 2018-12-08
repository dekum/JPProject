package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Global.productList.add(new MoviePlayer("DBPOWER MK101",new Screen("720x480",40,22),MonitorType.LCD));

        AudioPlayer ap = new AudioPlayer("ipod Mini", "MP3");
        Global.productList.add(ap);
        Global.productList.add(new MoviePlayer("Pyle PDV156BK",new Screen("1366x768",40,22),MonitorType.LED));

        AudioPlayer ap2 = new AudioPlayer("Walkman", "WAV");
        Global.productList.add(ap2);
        Global.productList.add(new MoviePlayer("DBPOWER MK101",new Screen("720x480",40,22),MonitorType.LCD));

        Parent root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
        primaryStage.setTitle("Home Window");
        primaryStage.setScene(new Scene(root, 478, 341));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
