package com.example.gatpulsar;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePulsarController implements Initializable {
    public ComboBox<String> env = new ComboBox<String>();
    public FileChooser fileDialog = new FileChooser();
    public TextField certValue;
    public TextField keyValue;
    public TextField tlsTrustCertFileValue;

    public HomePulsarController(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        env.getItems().addAll(
                "DEV",
                "TST",
                "CER",
                "QLY",
                "PRD"
        );
    }

    public void selectCert(ActionEvent actionEvent) {
        Scene scene = getScene(actionEvent);
        File file = fileDialog.showOpenDialog(scene.getWindow());

        if (file != null)
            certValue.setText(file.getPath());
    }

    public void selectKey(ActionEvent actionEvent) {
        Scene scene = getScene(actionEvent);
        File file = fileDialog.showOpenDialog(scene.getWindow());
        if (file != null)
            keyValue.setText(file.getPath());
    }

    public void selectTlsTrustCertFile(ActionEvent actionEvent) {
        Scene scene = getScene(actionEvent);
        File file = fileDialog.showOpenDialog(scene.getWindow());
        if (file != null)
            tlsTrustCertFileValue.setText(file.getPath());
    }
    private Scene getScene(ActionEvent actionEvent){
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage.getScene();
    }


}
