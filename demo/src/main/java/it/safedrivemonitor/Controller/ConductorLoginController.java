package it.safedrivemonitor.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConductorLoginController {

    @FXML
    private TextField driverNameField;

    @FXML
    private TextField driverIdField;

    // Nel onLogin passiamo a device_detection.fxml
    @FXML
    private void onLogin() {
        // se vuoi già creare/recuperare l'utente dal DB qui, puoi farlo
        String name = driverNameField.getText();
        String id = driverIdField.getText();
        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Nome o ID vuoti");
            return;
        }

        // Salviamo in una "conductor temp" o passiamo parametri
        // Più semplice se memorizziamo in uno static var per prototipo:
        ConductorSession.name = name;
        ConductorSession.id = id;

        // Passo alla device_detection.fxml
        try {
            Stage stage = (Stage) driverIdField.getScene().getWindow();
            double w = stage.getWidth();
            double h = stage.getHeight();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/device_detection.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(w);
            stage.setHeight(h);
            stage.setTitle("Rilevamento Dispositivo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
