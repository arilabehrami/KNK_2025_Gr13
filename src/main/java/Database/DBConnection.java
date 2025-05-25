package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres?pgbouncer=true";
    private static final String USER = "postgres.nlkjkprrqgjlzwxnbdsm";
    private static final String PASSWORD = "mindes257";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Gabim gjatë lidhjes me databazën:");
            e.printStackTrace();
            return null;
        }
    }
}
