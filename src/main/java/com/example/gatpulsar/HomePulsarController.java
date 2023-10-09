package com.example.gatpulsar;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePulsarController implements Initializable {
    public ComboBox<String> env = new ComboBox<>();
    public FileChooser fileDialog = new FileChooser();
    public TextField certValue;
    public TextField keyValue;
    public TextField tlsTrustCertFileValue;
    public TextArea message;
    public TextField topic;

    public HomePulsarController(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        env.getItems().addAll(
                "DEV",
                "TST",
                "TSI",
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


    public void sendMessage(ActionEvent actionEvent) {
        String cert = this.certValue.getText();
        String key = this.keyValue.getText();
        String tlsCertsTrust = this.tlsTrustCertFileValue.getText();
        String env = this.env.getValue();
        String message = this.message.getText();
        String topic = this.topic.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dados");
        alert.setContentText("Ambiente: "+env+"\n certicado: "+cert+"\n key: "+key+"\n tls: "+ tlsCertsTrust);
        alert.showAndWait();
    }

    private void sentMessageWithPulsar(String cert, String key, String tlsCertsTrust, String env, String topic, String message){

    }
}
