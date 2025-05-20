package repository;

import models.domain.Orari;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;

import java.sql.*;
import java.util.ArrayList;

public class OrariRepository extends BaseRepository<Orari, CreateOrariDto, UpdateOrariDto> {

    public OrariRepository() {
        super("Orari", "OrariID");
    }

    @Override
    public Orari fromResultSet(ResultSet rs) throws SQLException {
        return new Orari(
                rs.getInt("OrariID"),
                rs.getInt("FemijaID"),
                rs.getString("Dita"),
                rs.getTime("ora_hyrjes").toLocalTime(),
                rs.getTime("ora_daljes").toLocalTime()
        );
    }

    @Override
    public Orari create(CreateOrariDto dto) {
        String sql = "INSERT INTO Orari (FemijaID, Dita, ora_hyrjes, ora_daljes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, dto.getFemijaID());
            ps.setString(2, dto.getDita());
            ps.setTime(3, Time.valueOf(dto.getOraHyrjesAsLocalTime()));
            ps.setTime(4, Time.valueOf(dto.getOraDaljesAsLocalTime()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return getById(id); // ose krijo objektin manualisht
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orari update(UpdateOrariDto dto) {
        String sql = "UPDATE Orari SET FemijaID = ?, Dita = ?, ora_hyrjes = ?, ora_daljes = ? WHERE OrariID = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, dto.getFemijaID());
            ps.setString(2, dto.getDita());
            ps.setTime(3, Time.valueOf(dto.getOraHyrjesAsLocalTime()));
            ps.setTime(4, Time.valueOf(dto.getOraDaljesAsLocalTime()));
            ps.setInt(5, dto.getOrariID());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return getById(dto.getOrariID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}