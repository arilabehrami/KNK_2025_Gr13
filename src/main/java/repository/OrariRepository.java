package repository;

import models.domain.Orari;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrariRepository {

    private Connection connection;

    public OrariRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Orari> findAll() throws SQLException {
        List<Orari> list = new ArrayList<>();
        String sql = "SELECT OrariID, FemijaID, Dita, ora_hyrjes, ora_daljes FROM Orari";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Orari o = new Orari();
                o.setOrariID(rs.getInt("OrariID"));
                o.setFemijaID(rs.getInt("FemijaID"));
                o.setDita(rs.getString("Dita"));
                o.setOraHyrjes(rs.getTime("ora_hyrjes").toLocalTime());
                o.setOraDaljes(rs.getTime("ora_daljes").toLocalTime());
                list.add(o);
            }
        }
        return list;
    }

    public void save(Orari o) throws SQLException {
        String sql = "INSERT INTO Orari (FemijaID, Dita, ora_hyrjes, ora_daljes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, o.getFemijaID());
            ps.setString(2, o.getDita());
            ps.setTime(3, Time.valueOf(o.getOraHyrjes()));
            ps.setTime(4, Time.valueOf(o.getOraDaljes()));
            ps.executeUpdate();
        }
    }

    public Orari findById(int id) throws SQLException {
        String sql = "SELECT OrariID, FemijaID, Dita, ora_hyrjes, ora_daljes FROM Orari WHERE OrariID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Orari(
                            rs.getInt("OrariID"),
                            rs.getInt("FemijaID"),
                            rs.getString("Dita"),
                            rs.getTime("ora_hyrjes").toLocalTime(),
                            rs.getTime("ora_daljes").toLocalTime()
                    );
                }
            }
        }
        return null;
    }

    public void update(Orari o) throws SQLException {
        String sql = "UPDATE Orari SET FemijaID=?, Dita=?, ora_hyrjes=?, ora_daljes=? WHERE OrariID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, o.getFemijaID());
            ps.setString(2, o.getDita());
            ps.setTime(3, Time.valueOf(o.getOraHyrjes()));
            ps.setTime(4, Time.valueOf(o.getOraDaljes()));
            ps.setInt(5, o.getOrariID());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Orari WHERE OrariID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
