package repository;

import models.domain.PreferencaDietike;
import models.Dto.PreferencaDietike.CreatePreferencaDietikeDto;
import models.Dto.PreferencaDietike.UpdatePreferencaDietikeDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreferencaDietikeRepository extends BaseRepository<PreferencaDietike, CreatePreferencaDietikeDto, UpdatePreferencaDietikeDto> {

    public PreferencaDietikeRepository() {
        super("preferencadietike", "PreferencaID");
    }

    @Override
    PreferencaDietike fromResultSet(ResultSet res) throws SQLException {
        return PreferencaDietike.getInstance(res);
    }

    @Override
    public PreferencaDietike create(CreatePreferencaDietikeDto createDto) {
        String query = """
                INSERT INTO preferencadietike (FemijaID, LlojiPreferences, Detaje)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getFemijaID());
            pstm.setString(2, createDto.getLlojiPreferences());
            pstm.setString(3, createDto.getDetaje());
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
    public PreferencaDietike update(UpdatePreferencaDietikeDto updateDto) {
        String query = """
                UPDATE preferencadietike
                SET FemijaID = ?, LlojiPreferences = ?, Detaje = ?
                WHERE PreferencaID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getFemijaID());
            pstm.setString(2, updateDto.getLlojiPreferences());
            pstm.setString(3, updateDto.getDetaje());
            pstm.setInt(4, updateDto.getPreferencaID());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getPreferencaID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
