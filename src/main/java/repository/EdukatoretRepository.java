package repository;

import models.Dto.Edukatoret.CreateEdukatoretDto;
import models.Dto.Edukatoret.UpdateEdukatoretDto;
import models.domain.Edukatoret;

import java.sql.*;

public class EdukatoretRepository extends CustomBaseRepository<Edukatoret, CreateEdukatoretDto, UpdateEdukatoretDto> {

    public EdukatoretRepository() {
        super("Edukatoret", "EdukatoriID");
    }

    @Override
    public Edukatoret fromResultSet(ResultSet result) throws SQLException {
        return Edukatoret.getInstance(result);
    }

    @Override
    public Edukatoret create(CreateEdukatoretDto dto) {
        String query = """
                INSERT INTO Edukatoret (Emri, Mbiemri, Kontakti, Kualifikimet)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri());
            pstm.setString(2, dto.getMbiemri());
            pstm.setString(3, dto.getKontakti());
            pstm.setString(4, dto.getKualifikimet());
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
    public Edukatoret update(UpdateEdukatoretDto dto) {
        String query = """
                UPDATE Edukatoret
                SET Emri = ?, Mbiemri = ?, Kontakti = ?, Kualifikimet = ?
                WHERE EdukatoriID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri());
            pstm.setString(2, dto.getMbiemri());
            pstm.setString(3, dto.getKontakti());
            pstm.setString(4, dto.getKualifikimet());
            pstm.setInt(5, dto.getEdukatoriID());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getEdukatoriID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
