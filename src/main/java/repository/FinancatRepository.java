package repository;

import Database.DBConnection;
import models.Dto.Financat.CreateFinancatDto;
import models.Dto.Financat.UpdateFinancatDto;
import models.domain.Financat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinancatRepository {
    private final Connection connection;

    public FinancatRepository() {
        this.connection = DBConnection.getConnection();
    }

    public Financat getById(int id) {
        try {
            String query = "SELECT * FROM Financat WHERE FinancatID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Financat.getInstance(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Financat> getAll() {
        List<Financat> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Financat ORDER BY FinancatID DESC";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(Financat.getInstance(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Financat> getByPershkrimiOseData(String keyword) {
        List<Financat> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Financat WHERE LOWER(Pershkrimi) LIKE ? OR Data::TEXT LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            String key = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, key);
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(Financat.getInstance(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Financat create(CreateFinancatDto dto) {
        try {
            String query = "INSERT INTO Financat (Data, teArdhura, Shpenzime, Pershkrimi) VALUES (?, ?, ?, ?) RETURNING *";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(dto.getDate()));
            stmt.setFloat(2, dto.getTeArdhura());
            stmt.setFloat(3, dto.getShpenzime());
            stmt.setString(4, dto.getPershkrimi());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Financat.getInstance(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Financat update(UpdateFinancatDto dto) {
        try {
            String query = "UPDATE Financat SET Data = ?, teArdhura = ?, Shpenzime = ?, Pershkrimi = ? WHERE FinancatID = ? RETURNING *";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(dto.getDate()));
            stmt.setFloat(2, dto.getTeArdhura());
            stmt.setFloat(3, dto.getShpenzime());
            stmt.setString(4, dto.getPershkrimi());
            stmt.setInt(5, dto.getFinancatID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Financat.getInstance(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        try {
            String query = "DELETE FROM Financat WHERE FinancatID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
