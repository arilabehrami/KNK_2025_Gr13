package repository;

import models.domain.MenyjaDitore;
import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import models.Dto.MenyjaDitore.UpdateMenyjaDitoreDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenyjaDitoreRepository extends BaseRepository<MenyjaDitore, CreateMenyjaDitoreDto, UpdateMenyjaDitoreDto> {

    public MenyjaDitoreRepository() {
        super("menyjaditore");
    }

    @Override
    MenyjaDitore fromResultSet(ResultSet res) throws SQLException {
        return MenyjaDitore.getInstance(res);
    }

    @Override
    public MenyjaDitore create(CreateMenyjaDitoreDto createDto) {
        String query = """
                INSERT INTO menyjaditore (Dita, GrupiID, UshqimiID)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getDita());
            pstm.setInt(2, createDto.getGrupiID());
            pstm.setInt(3, createDto.getUshqimiID());
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
    public MenyjaDitore update(UpdateMenyjaDitoreDto updateDto) {
        String query = """
                UPDATE menyjaditore
                SET Dita = ?, GrupiID = ?, UshqimiID = ?
                WHERE MenuID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updateDto.getDita());
            pstm.setInt(2, updateDto.getGrupiID());
            pstm.setInt(3, updateDto.getUshqimiID());
            pstm.setInt(4, updateDto.getMenuID());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getMenuID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
