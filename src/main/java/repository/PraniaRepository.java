package repository;

import models.domain.Prania;
import models.Dto.Prania.CreatePraniaDto;
import models.Dto.Prania.UpdatePraniaDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PraniaRepository extends BaseRepository<Prania, CreatePraniaDto, UpdatePraniaDto> {

    public PraniaRepository() {
        super("prania");
    }

    @Override
    Prania fromResultSet(ResultSet res) throws SQLException {
        return Prania.getInstance(res);
    }

    @Override
    public Prania create(CreatePraniaDto createDto) {
        String query = """
                INSERT INTO prania (FemijaID, Data, Statusi)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getFemijaId());
            pstm.setString(2, createDto.getData());
            pstm.setString(3, createDto.getStatusi());
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
    public Prania update(UpdatePraniaDto updateDto) {
        String query = """
                UPDATE prania
                SET FemijaID = ?, Data = ?, Statusi = ?
                WHERE PraniaID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getFemijaId());
            pstm.setString(2, updateDto.getData());
            pstm.setString(3, updateDto.getStatusi());
            pstm.setInt(4, updateDto.getPraniaId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getPraniaId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
