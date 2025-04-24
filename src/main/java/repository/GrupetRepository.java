package repository;

import models.domain.Grupet;
import models.dto.Grupet.CreateGrupetDto;
import models.dto.Grupet.UpdateGrupetDto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GrupetRepository extends BaseRepository<Grupet, CreateGrupetDto, UpdateGrupetDto> {

    public GrupetRepository() {
        super("grupet");
    }

    @Override
    public Grupet fromResultSet(ResultSet result) throws SQLException {
        return Grupet.getInstance(result);
    }

    @Override
    public Grupet create(CreateGrupetDto dto) {
        String query = """
            INSERT INTO GRUPET (EdukatoriId, MoshaMin, MoshaMax, EmriGrupit)
            VALUES (?, ?, ?, ?)
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getEdukatoriId());
            pstm.setInt(2, dto.getMoshaMin());
            pstm.setInt(3, dto.getMoshaMax());
            pstm.setString(4, dto.getEmriGrupit());
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
    public Grupet update(UpdateGrupetDto dto) {
        String query = """
            UPDATE GRUPET
            SET EmriGrupit = ?, MoshaMin = ?, MoshaMax = ?, EdukatoriId = ?
            WHERE GrupiId = ?
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmriGrupit());
            pstm.setInt(2, dto.getMoshaMin());
            pstm.setInt(3, dto.getMoshaMax());
            pstm.setInt(4, dto.getEdukatoriId());
            pstm.setInt(5, dto.getGrupiId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getGrupiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
