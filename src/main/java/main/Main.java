package main;

import controller.Euclidean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.math.BigInteger;

public class Main {

    private static FXMLLoader mainLoader = new FXMLLoader();
    private static Stage window;

    public static void main(String[] args) {
        //Application.launch(Launcher.class, args);
        Euclidean euclidean = new Euclidean(new BigInteger("7"), new BigInteger("23"));
        euclidean.calc();
        euclidean.printTable();
    }

    public static FXMLLoader getMainLoader() {
        return mainLoader;
    }

    public static void setMainLoader(FXMLLoader mainLoader) {
        Main.mainLoader = mainLoader;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        Main.window = window;
    }
}
