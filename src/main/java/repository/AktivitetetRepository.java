package repository;

import models.domain.Aktivitetet;
import models.Dto.Aktivitetet.CreateAktivitetetDto;
import models.Dto.Aktivitetet.UpdateAktivitetetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AktivitetetRepository extends BaseRepository<Aktivitetet, CreateAktivitetetDto, UpdateAktivitetetDto> {

    public AktivitetetRepository() {
        super("Aktivitetet");
    }

    @Override
    Aktivitetet fromResultSet(ResultSet res) throws SQLException {
        return Aktivitetet.getInstance(res);  // Nevojiten detajet për implementimin e kësaj metode.
    }

    @Override
    public Aktivitetet create(CreateAktivitetetDto createDto) {
        String query = """
                INSERT INTO Aktivitetet (EmriAktivitetit, Pershkrimi, Data, GrupiID)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getEmriAktivitetit());
            pstm.setString(2, createDto.getPershkrimi());
            pstm.setString(3, createDto.getData());
            pstm.setInt(4, createDto.getGrupiId());
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
    public Aktivitetet update(UpdateAktivitetetDto updateDto) {
        String query = """
                UPDATE Aktivitetet
                SET EmriAktivitetit = ?, Pershkrimi = ?, Data = ?, GrupiID = ?
                WHERE AktivitetiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updateDto.getEmriAktivitetit());
            pstm.setString(2, updateDto.getPershkrimi());
            pstm.setString(3, updateDto.getData());
            pstm.setInt(4, updateDto.getGrupiId());
            pstm.setInt(5, updateDto.getAktivitetiId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(updateDto.getAktivitetiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
