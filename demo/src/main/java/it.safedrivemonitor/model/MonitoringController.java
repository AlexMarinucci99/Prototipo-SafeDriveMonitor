package it.safedrivemonitor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MonitoringController {

    // Soglie
    private static final double ALCOHOL_THRESHOLD = 0.5;
    private static final double THC_THRESHOLD = 15; // ng/ml
    private static final double COCAINE_THRESHOLD = 10; // ng/ml
    private static final double MDMA_THRESHOLD = 50; // ng/ml

    private DatabaseManager dbManager;

    public MonitoringController() {
        dbManager = new DatabaseManager();
    }

    public TestResult executeTest(String driverId) {
        // Simulazione letture sensori
        double alcohol = Math.random(); // 0.0 - 1.0 g/l
        double thc = Math.random() * 30; // 0 - 30 ng/ml
        double cocaine = Math.random() * 20; // 0 - 20 ng/ml
        double mdma = Math.random() * 100; // 0 - 100 ng/ml

        boolean passed = alcohol <= ALCOHOL_THRESHOLD &&
                thc <= THC_THRESHOLD &&
                cocaine <= COCAINE_THRESHOLD &&
                mdma <= MDMA_THRESHOLD;

        String result = passed ? "OK" : "BLOCKED";

        // Salviamo su DB
        saveReading(driverId, alcohol, thc, cocaine, mdma, result);

        return new TestResult(passed, alcohol, thc, cocaine, mdma);
    }

    private void saveReading(String driverId, double alcohol, double thc, double cocaine, double mdma, String result) {
        String sql = "INSERT INTO readings(driver_id, alcohol_level, thc_level, cocaine_level, mdma_level, result) VALUES(?,?,?,?,?,?)";

        try (Connection conn = dbManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, driverId);
            pstmt.setDouble(2, alcohol);
            pstmt.setDouble(3, thc);
            pstmt.setDouble(4, cocaine);
            pstmt.setDouble(5, mdma);
            pstmt.setString(6, result);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class TestResult {
        public final boolean passed;
        public final double alcohol;
        public final double thc;
        public final double cocaine;
        public final double mdma;

        public TestResult(boolean passed, double alcohol, double thc, double cocaine, double mdma) {
            this.passed = passed;
            this.alcohol = alcohol;
            this.thc = thc;
            this.cocaine = cocaine;
            this.mdma = mdma;
        }
    }
}
