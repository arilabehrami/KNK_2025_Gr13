package repository;

import models.domain.Ushqimet;
import models.Dto.Ushqimet.CreateUshqimetDto;
import models.Dto.Ushqimet.UpdateUshqimetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UshqimetRepository extends BaseRepository<Ushqimet, CreateUshqimetDto, UpdateUshqimetDto> {

    public UshqimetRepository() {
        super("ushqimet", "UshqimiID");
    }

    @Override
    Ushqimet fromResultSet(ResultSet res) throws SQLException {
        return Ushqimet.getInstance(res);
    }

    @Override
    public Ushqimet create(CreateUshqimetDto createDto) {
        String query = """
                INSERT INTO ushqimet (EmriUshqimit, Kategoria, Pershkrimi)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getEmriUshqimit());
            pstm.setString(2, createDto.getKategoria());
            pstm.setString(3, createDto.getPershkrimi());
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
    public Ushqimet update(UpdateUshqimetDto updateDto) {
        String query = """
                UPDATE ushqimet
                SET EmriUshqimit = ?, Kategoria = ?, Pershkrimi = ?
                WHERE UshqimiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updateDto.getEmriUshqimit());
            pstm.setString(2, updateDto.getKategoria());
            pstm.setString(3, updateDto.getPershkrimi());
            pstm.setInt(4, updateDto.getUshqimiID());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getUshqimiID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
