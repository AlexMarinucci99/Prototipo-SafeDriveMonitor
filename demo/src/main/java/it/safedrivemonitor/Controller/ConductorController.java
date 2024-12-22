package it.safedrivemonitor.Controller;

import it.safedrivemonitor.model.MonitoringController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConductorController {

    private final MonitoringController monitoringController;

    @FXML
    private TextField driverIdField;
    @FXML
    private Label testResultLabel;
    @FXML
    private Label vehicleStatusLabel;
    @FXML
    private Label authorityLabel;

    public ConductorController(MonitoringController monitoringController) {
        this.monitoringController = monitoringController;
    }

    @FXML
    private void onExecuteTest() {
        String driverId = driverIdField.getText();
        if (driverId == null || driverId.isEmpty()) {
            testResultLabel.setText("ID Conducente non inserito");
            return;
        }

        MonitoringController.TestResult result = monitoringController.executeTest(driverId);

        if (result.passed) {
            testResultLabel.setText("TEST SUPERATO");
            testResultLabel.setStyle("-fx-text-fill: green;");
            vehicleStatusLabel.setText("VEICOLO SBLOCCATO");
            vehicleStatusLabel.setStyle("-fx-text-fill: green;");
            authorityLabel.setText("");
        } else {
            testResultLabel.setText("TEST NON SUPERATO");
            testResultLabel.setStyle("-fx-text-fill: red;");
            vehicleStatusLabel.setText("VEICOLO BLOCCATO");
            vehicleStatusLabel.setStyle("-fx-text-fill: red;");
            authorityLabel.setText("Notifica alle autorità inviata");
        }
    }
}
