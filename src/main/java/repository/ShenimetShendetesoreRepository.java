package repository;

import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;

import java.sql.*;

public class ShenimetShendetesoreRepository
        extends BaseRepository<ShenimetShendetesore, CreateShenimetShendetsoreDto, UpdateShenimetShendetsoreDto> {

    public ShenimetShendetesoreRepository() {
        super("ShenimetShendetesore", "ShenimiID");
    }

    @Override
    public ShenimetShendetesore fromResultSet(ResultSet result) throws SQLException {
        return ShenimetShendetesore.getInstance(result);
    }

    @Override
    public ShenimetShendetesore create(CreateShenimetShendetsoreDto dto) {
        String query = """
            INSERT INTO ShenimetShendetesore (FemijaID, Data, Pershkrimi)
            VALUES (?, ?, ?)
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getFemijaId());
            pstm.setDate(2, dto.getData());
            pstm.setString(3, dto.getPershkrimi());
            pstm.execute();

            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                return getById(res.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShenimetShendetesore update(UpdateShenimetShendetsoreDto dto) {
        try {
            String query = """
            UPDATE ShenimetShendetesore 
            SET Data = ?, Pershkrimi = ?, FemijaID = ? 
            WHERE ShenimiID = ?
        """;

            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setDate(1, dto.getData());
            pstm.setString(2, dto.getPershkrimi());
            pstm.setInt(3, dto.getFemijaId());
            pstm.setInt(4, dto.getShenimiID());

            pstm.executeUpdate();

            return getById(dto.getShenimiID());

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null; // ose handle më mirë nëse dëshiron
        }
    }
}