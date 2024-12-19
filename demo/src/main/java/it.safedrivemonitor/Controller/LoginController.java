package it.safedrivemonitor.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button onConductorAccess;
    @FXML
    private Button onAdminAccess;

    @FXML
    private void onConductorAccess() {
        // Carica la vista del conducente
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/conductor_view.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            Stage stage = (Stage) onConductorAccess.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Conducente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAdminAccess() {
        // Carica una piccola vista di login admin
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_login.fxml"));
            Scene scene = new Scene(loader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login Admin");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
