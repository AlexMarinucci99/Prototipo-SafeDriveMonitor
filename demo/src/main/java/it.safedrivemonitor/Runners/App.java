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
        dbManager.initDB();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema Monitoraggio - Login");
        primaryStage.show();
    }
}
