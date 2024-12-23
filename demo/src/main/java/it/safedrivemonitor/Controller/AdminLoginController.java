package it.safedrivemonitor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

import it.safedrivemonitor.model.DatabaseManager;

public class AdminLoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final DatabaseManager dbManager;

    public AdminLoginController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @FXML
    private void onLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        // Esempio: user=admin, pass=admin (o admin/admin123, come preferisci)
        if ("admin".equals(user) && "admin".equals(pass)) {
            try {
                // Carichiamo la vista 'admin_view.fxml' che ha AdminController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_view.fxml"));
                Scene scene = new Scene(loader.load(), 600, 400);

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Amministratore");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credenziali non valide");
        }
    }

    // Se non serve più, rimuovi pure:
    /*
     * @FXML
     * private void onViewLog() {
     * try {
     * FXMLLoader loader = new
     * FXMLLoader(getClass().getResource("/fxml/admin_log.fxml"));
     * Scene scene = new Scene(loader.load(), 600, 400);
     * Stage stage = new Stage();
     * stage.setScene(scene);
     * stage.setTitle("Log Letture");
     * stage.show();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
