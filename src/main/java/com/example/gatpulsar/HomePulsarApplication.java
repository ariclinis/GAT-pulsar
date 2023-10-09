package com.example.gatpulsar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePulsarApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-pulsar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 440, 440);
        primaryStage.setTitle("GAT-pulsar");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
