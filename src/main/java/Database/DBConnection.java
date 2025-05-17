package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Vendos URL-në e saktë me portën dhe fjalëkalimin tënd
    private static final String DB_URL = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres?pgbouncer=true";
    private static final String USER = "postgres.nlkjkprrqgjlzwxnbdsm";  // emri që ke nga supabase
    private static final String PASSWORD = "mindes257";         // fut fjalëkalimin tënd

    public static Connection getConnection() {
        try {
            // DriverManager kërkon JDBC url me prefix jdbc:postgresql://
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Gabim gjatë lidhjes me databazën:");
            e.printStackTrace();
            return null;
        }
    }
}
