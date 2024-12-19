package it.safedrivemonitor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:monitoring.db";

    public void initDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS readings (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "driver_id TEXT," +
                            "alcohol_level REAL," +
                            "thc_level REAL," +
                            "cocaine_level REAL," +
                            "mdma_level REAL," +
                            "result TEXT," +
                            "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
                            ");";
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
