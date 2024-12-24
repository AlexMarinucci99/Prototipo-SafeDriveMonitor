package it.safedrivemonitor.controller;

import it.safedrivemonitor.model.DatabaseManager;
import it.safedrivemonitor.model.MonitoringController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConductorMainController {

    @FXML
    private Label testResultLabel;
    @FXML
    private Label vehicleStatusLabel;
    @FXML
    private Label authorityLabel;

    private MonitoringController monitoringController;
    private DatabaseManager dbManager;

    @FXML
    public void initialize() {
        dbManager = new DatabaseManager();
        dbManager.initDB();
        monitoringController = new MonitoringController(dbManager);
    }

    @FXML
    private void onExecuteTest() {
        String driverName = ConductorSession.name;
        String driverId = ConductorSession.id;

        // Eseguire test (ora con 2 parametri)
        MonitoringController.TestResult result = monitoringController.executeTest(driverId, driverName);

        // Gestione scritte
        if (result.passed) {
            testResultLabel.setText("TEST SUPERATO");
            testResultLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            vehicleStatusLabel.setText("VEICOLO SBLOCCATO");
            vehicleStatusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            authorityLabel.setText("");
        } else {

            testResultLabel.setText("TEST NON SUPERATO");
            testResultLabel.getStyleClass().removeAll("test-success-label", "test-fail-label");
            testResultLabel.getStyleClass().add("test-fail-label");

            vehicleStatusLabel.setText("VEICOLO BLOCCATO");
            vehicleStatusLabel.getStyleClass().removeAll("test-success-label", "test-fail-label");
            vehicleStatusLabel.getStyleClass().add("test-fail-label");

            // Scrittona con emoji
            authorityLabel.setText("Notifica alle autorità inviata \uD83D\uDE93");
            authorityLabel.getStyleClass().clear();
            authorityLabel.getStyleClass().add("authorities-label");
        }

    }

    @FXML
    private void onViewMyResults() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conductor_results.fxml"));
            Parent root = loader.load();

            // Recupera il controller
            ConductorResultsController controller = loader.getController();
            controller.setDriverId(ConductorSession.id);

            Scene scene = new Scene(root, 600, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("I miei risultati");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBack() {
        try {
            Stage stage = (Stage) testResultLabel.getScene().getWindow();
            double currentW = stage.getWidth();
            double currentH = stage.getHeight();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login_view.fxml"));
            Parent root = loader.load();

            Scene newScene = new Scene(root);
            stage.setScene(newScene);

            stage.setWidth(currentW);
            stage.setHeight(currentH);

            stage.setTitle("SafeDriveMonitor-Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
