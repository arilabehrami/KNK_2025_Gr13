package repository;

import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShenimetShendetesoreRepository extends BaseRepository<ShenimetShendetesore, CreateShenimetShendetsoreDto, UpdateShenimetShendetsoreDto> {

    public ShenimetShendetesoreRepository() {
        super("ShenimetShendetesore", "ShenimiID");
    }

    @Override
    public ShenimetShendetesore fromResultSet(ResultSet result) throws SQLException {
        return ShenimetShendetesore.getInstance(result);
    }

    public ShenimetShendetesore create(CreateShenimetShendetsoreDto shenimiDto) {
        String query = """
                INSERT INTO ShenimetShendetesore (FemijaID, Data, Pershkrimi)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, shenimiDto.getFemijaId());
            pstm.setDate(2, java.sql.Date.valueOf(shenimiDto.getData()));
            pstm.setString(3, shenimiDto.getPershkrimi());
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

    public ShenimetShendetesore update(UpdateShenimetShendetsoreDto shenimiDto) {
        String query = """
                UPDATE ShenimetShendetesore 
                SET Data = ?, Pershkrimi = ?
                WHERE ShenimiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setDate(1, java.sql.Date.valueOf(shenimiDto.getData()));
            pstm.setString(2, shenimiDto.getPershkrimi());
            pstm.setInt(3, shenimiDto.getShenimiId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(shenimiDto.getShenimiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

