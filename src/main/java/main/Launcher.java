package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    private FXMLLoader mainLoader = Main.getMainLoader();

    public void start(Stage stage) throws Exception {
        Main.setWindow(stage);
        Stage window = Main.getWindow();

        mainLoader.setLocation(Main.class.getResource("/fxml/main.fxml"));
        Parent root = mainLoader.load();
        Scene scene = new Scene(root);

        window.setTitle("RSA");
        window.setMinWidth(420);
        window.setMinHeight(320);
        window.setScene(scene);
        window.show();
    }
}