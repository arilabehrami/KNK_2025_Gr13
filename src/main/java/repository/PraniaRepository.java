package repository;

import Database.DBConnection;
import models.domain.Prania;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PraniaRepository {
    public List<Prania> getAll() {
        List<Prania> list = new ArrayList<>();
        String sql = "SELECT * FROM Prania";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prania p = new Prania();
                p.setPraniaId(rs.getInt("PraniaID"));
                p.setFemijaId(rs.getInt("FemijaID"));
                p.setData(rs.getDate("Data").toLocalDate());
                p.setStatusi(rs.getString("Statusi"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insert(Prania prania) {
        String sql = "INSERT INTO Prania (FemijaID, Data, Statusi) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prania.getFemijaId());
            stmt.setDate(2, Date.valueOf(prania.getData()));
            stmt.setString(3, prania.getStatusi());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Prania prania) {
        String sql = "UPDATE Prania SET FemijaID = ?, Data = ?, Statusi = ? WHERE PraniaID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prania.getFemijaId());
            stmt.setDate(2, Date.valueOf(prania.getData()));
            stmt.setString(3, prania.getStatusi());
            stmt.setInt(4, prania.getPraniaId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM prania WHERE praniaid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            System.out.println("Rows deleted: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(Prania prania) {
        String sql = "INSERT INTO prania (femijaId, statusi, data) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prania.getFemijaId());
            stmt.setString(2, prania.getStatusi());
            stmt.setDate(3, java.sql.Date.valueOf(prania.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gabim gjatë ruajtjes në DB", e);
        }
    }

}
