package repository;

import models.domain.Orari;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrariRepository extends BaseRepository<Orari, CreateOrariDto, UpdateOrariDto> {

    public List<Orari> getAll() {
        List<Orari> list = new ArrayList<>();
        String query = "SELECT * FROM Orari";
        try (PreparedStatement stmt = this.connection.prepareStatement(query);
             ResultSet res = stmt.executeQuery()) {
            while (res.next()) {
                list.add(fromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public OrariRepository() {
        super("Orari");
    }

    @Override
    Orari fromResultSet(ResultSet res) throws SQLException {
        return Orari.getInstance(res);
    }

    @Override
    public Orari create(CreateOrariDto createDto) {
        String query = """
                INSERT INTO Orari (FemijaID, Dita, OraHyrjes, OraDaljes)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getFemijaId());
            pstm.setString(2, createDto.getDita());
            pstm.setString(3, createDto.getOraHyrjes());
            pstm.setString(4, createDto.getOraDaljes());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orari update(UpdateOrariDto updateDto) {
        String query = """
                UPDATE Orari
                SET FemijaID = ?, Dita = ?, OraHyrjes = ?, OraDaljes = ?
                WHERE OrariID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getFemijaId());
            pstm.setString(2, updateDto.getDita());
            pstm.setString(3, updateDto.getOraHyrjes());
            pstm.setString(4, updateDto.getOraDaljes());
            pstm.setInt(5, updateDto.getOrariId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getOrariId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
