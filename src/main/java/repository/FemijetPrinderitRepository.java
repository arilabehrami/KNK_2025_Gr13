package repository;

import models.Dto.FemijetPrinderit.CreateFemijetPrinderitDto;
import models.Dto.FemijetPrinderit.UpdateFemijetPrinderitDto;
import models.domain.FemijetPrinderit;

import java.sql.*;

public class FemijetPrinderitRepository extends BaseRepository<FemijetPrinderit, CreateFemijetPrinderitDto, UpdateFemijetPrinderitDto> {

    public FemijetPrinderitRepository() {
        super("FemijetPrinderit");
    }

    @Override
    public FemijetPrinderit fromResultSet(ResultSet result) throws SQLException {
        return FemijetPrinderit.getInstance(result);
    }

    @Override
    public FemijetPrinderit create(CreateFemijetPrinderitDto dto) {
        String query = """
                INSERT INTO FemijetPrinderit (FemijaID, PrindiID)
                VALUES (?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getFemijaID());
            pstm.setInt(2, dto.getPrindiID());
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
    public FemijetPrinderit update(UpdateFemijetPrinderitDto dto) {
        String query = """
                UPDATE FemijetPrinderit
                SET FemijaID = ?, PrindiID = ?
                WHERE ID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, dto.getFemijaID());
            pstm.setInt(2, dto.getPrindiID());
            pstm.setInt(3, dto.getId());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
