package repository;

import Database.DBConnection;
import models.domain.Aktivitetet;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AktivitetetRepository {

    private Connection connection;

    public AktivitetetRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Aktivitetet> findAll() throws SQLException {
        List<Aktivitetet> list = new ArrayList<>();
        String sql = "SELECT * FROM Aktivitetet";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aktivitetet a = new Aktivitetet();
                a.setAktivitetiID(rs.getInt("AktivitetiID"));
                a.setEmriAktivitetit(rs.getString("EmriAktivitetit"));
                a.setPershkrimi(rs.getString("Pershkrimi"));
                a.setData(rs.getDate("Data").toLocalDate());
                a.setGrupiID(rs.getInt("GrupiID"));

                list.add(a);
            }
        }
        return list;
    }

    public void add(Aktivitetet a) throws SQLException {
        String sql = "INSERT INTO Aktivitetet (EmriAktivitetit, Pershkrimi, Data, GrupiID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, a.getEmriAktivitetit());
            stmt.setString(2, a.getPershkrimi());
            stmt.setDate(3, Date.valueOf(a.getData()));
            stmt.setInt(4, a.getGrupiID());
            stmt.executeUpdate();
        }
    }

    public void update(Aktivitetet a) throws SQLException {
        String sql = "UPDATE Aktivitetet SET EmriAktivitetit=?, Pershkrimi=?, Data=?, GrupiID=? WHERE AktivitetiID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, a.getEmriAktivitetit());
            stmt.setString(2, a.getPershkrimi());
            stmt.setDate(3, Date.valueOf(a.getData()));
            stmt.setInt(4, a.getGrupiID());
            stmt.setInt(5, a.getAktivitetiID());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Aktivitetet WHERE AktivitetiID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
