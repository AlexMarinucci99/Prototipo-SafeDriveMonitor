package it.safedrivemonitor.Runners;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.yourcompany.alcoholmonitoring.model.DatabaseManager;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Inizializziamo il Database
        DatabaseManager dbManager = new DatabaseManager();
        MonitoringController monitoringController = new MonitoringController(dbManager);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/conductor_view.fxml"));
        loader.setControllerFactory(type -> {
            if (type == ConductorController.class) {
                return new ConductorController(monitoringController);
            } else if (type == AdminController.class) {
                return new AdminController(dbManager);
            }
            // ...
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Scene scene = new Scene(loader.load(), 400, 300);
    }
}
