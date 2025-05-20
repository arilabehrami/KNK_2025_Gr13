package repository;

import models.Dto.KontatetEmergjente.CreateKontaktetEmergjenteDto;
import models.Dto.KontatetEmergjente.UpdateKontaktetEmergjenteDto;
import models.domain.KontaktiEmergjent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KontaktiEmergjentRepository extends BaseRepository<KontaktiEmergjent, CreateKontaktetEmergjenteDto, UpdateKontaktetEmergjenteDto>{

    public KontaktiEmergjentRepository() {
        super("KontaktetEmergjente", "KontaktiID");
    }

    @Override
    public KontaktiEmergjent fromResultSet(ResultSet result) throws SQLException {
        return KontaktiEmergjent.getInstance(result);
    }

    public KontaktiEmergjent create(CreateKontaktetEmergjenteDto kontaktiDto) {
        String query = """
                INSERT INTO KontaktetEmergjente (FemijaID, Emri, Mbiemri, Telefoni)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, kontaktiDto.getFemijaId());
            pstm.setString(2, kontaktiDto.getEmri());
            pstm.setString(3, kontaktiDto.getMbiemri());
            pstm.setString(4, kontaktiDto.getTelefoni());
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

    public KontaktiEmergjent update(UpdateKontaktetEmergjenteDto kontaktiDto) {
        String query = """
                UPDATE KontaktetEmergjente 
                SET Emri = ?, Mbiemri = ?, Telefoni = ?
                WHERE KontaktiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, kontaktiDto.getEmri());
            pstm.setString(2, kontaktiDto.getMbiemri());
            pstm.setString(3, kontaktiDto.getTelefoni());
            pstm.setInt(4, kontaktiDto.getKontaktiId());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(kontaktiDto.getKontaktiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

