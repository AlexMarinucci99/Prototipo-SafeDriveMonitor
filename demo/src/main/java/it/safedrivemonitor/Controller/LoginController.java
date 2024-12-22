package it.safedrivemonitor.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import it.safedrivemonitor.model.DatabaseManager;
import it.safedrivemonitor.model.MonitoringController;

public class LoginController {

    @FXML
    private Button onConductorAccess;
    @FXML
    private Button onAdminAccess;

    @FXML
    private void onConductorAccess() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conductor_view.fxml"));

            // Imposto una controller factory personalizzata
            loader.setControllerFactory(type -> {
                if (type == ConductorController.class) {
                    // Qui crei o recuperi ciò che serve al tuo controller
                    DatabaseManager db = new DatabaseManager();
                    db.initDB();
                    MonitoringController monCtrl = new MonitoringController(db);
                    return new ConductorController(monCtrl);
                }
                // Fallback: costruttore vuoto
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_login.fxml"));

            loader.setControllerFactory(type -> {
                if (type == AdminLoginController.class) {
                    DatabaseManager db = new DatabaseManager();
                    db.initDB();
                    return new AdminLoginController(db);
                }
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

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
