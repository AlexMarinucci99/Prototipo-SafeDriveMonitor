package it.safedrivemonitor.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void onLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if ("admin".equals(user) && "admin123".equals(pass)) {
            // Carica vista admin
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_view.fxml"));
                Scene scene = new Scene(loader.load(), 600, 400);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Amministratore");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Mostra errore
            System.out.println("Credenziali non valide");
        }
    }

    @FXML
    private void onViewLog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_log.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Log Letture");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
