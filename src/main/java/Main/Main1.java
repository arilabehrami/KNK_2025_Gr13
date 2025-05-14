package Main;

import Database.DBConnection;
import java.sql.*;

public class Main1 {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();

            String ushqimiInsert = """
                INSERT INTO Ushqimet (EmriUshqimit, Kategoria, Pershkrimi)
                VALUES ('Pite me djathë', 'Mengjes', 'Pite e freskët me djathë të bardhë')
            """;
            stmt.executeUpdate(ushqimiInsert);
            System.out.println("U shtua ushqimi!");

            String menyjaInsert = """
                INSERT INTO MenyjaDitore (Dita, GrupiID, UshqimiID)
                VALUES ('E Hene', 1, 1)
            """;
            stmt.executeUpdate(menyjaInsert);
            System.out.println("U shtua menyja ditore!");

            String preferencaInsert = """
                INSERT INTO PreferencaDietike (FemijaID, LlojiPreferences, Detaje)
                VALUES (1, 'Alergji', 'Alergji ndaj glutenit')
            """;
            stmt.executeUpdate(preferencaInsert);
            System.out.println("U shtua preferenca dietike!");

            System.out.println("\n--- Ushqimet ---");
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM Ushqimet");
            while (rs1.next()) {
                System.out.println("ID: " + rs1.getInt("UshqimiID"));
                System.out.println("Emri: " + rs1.getString("EmriUshqimit"));
                System.out.println("Kategoria: " + rs1.getString("Kategoria"));
                System.out.println("Pershkrimi: " + rs1.getString("Pershkrimi"));
                System.out.println("-------------");
            }

            System.out.println("\n--- Menyja Ditore ---");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM MenyjaDitore");
            while (rs2.next()) {
                System.out.println("ID: " + rs2.getInt("MenuID"));
                System.out.println("Dita: " + rs2.getString("Dita"));
                System.out.println("GrupiID: " + rs2.getInt("GrupiID"));
                System.out.println("UshqimiID: " + rs2.getInt("UshqimiID"));
                System.out.println("-------------");
            }

            System.out.println("\n--- Preferencat Dietike ---");
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM PreferencaDietike");
            while (rs3.next()) {
                System.out.println("ID: " + rs3.getInt("PreferencaID"));
                System.out.println("FemijaID: " + rs3.getInt("FemijaID"));
                System.out.println("Lloji: " + rs3.getString("LlojiPreferences"));
                System.out.println("Detaje: " + rs3.getString("Detaje"));
                System.out.println("-------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
