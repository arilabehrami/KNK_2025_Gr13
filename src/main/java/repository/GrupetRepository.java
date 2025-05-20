package repository;

import models.domain.Grupet;
import models.Dto.Grupet.CreateGrupetDto;
import models.Dto.Grupet.UpdateGrupetDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupetRepository extends BaseRepository<Grupet, CreateGrupetDto, UpdateGrupetDto> {

    public GrupetRepository() {
        super("grupet", "GrupiID"); // siguro që "GrupiID" është emri i saktë i kolonës primary key në DB
    }

    @Override
    public Grupet fromResultSet(ResultSet result) throws SQLException {
        return Grupet.getInstance(result);
    }

    // Merr grupin nga DB sipas ID-së
    public Grupet getById(int id) {
        String query = "SELECT * FROM grupet WHERE GrupiID = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return fromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Merr të gjitha grupet
    public ArrayList<Grupet> getAll() {
        List<Grupet> list = new ArrayList<>();
        String query = "SELECT * FROM grupet";
        try (PreparedStatement pstm = this.connection.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Grupet>) list;
    }

    @Override
    public Grupet create(CreateGrupetDto dto) {
        String query = """
            INSERT INTO grupet (EdukatoriId, MoshaMin, MoshaMax, EmriGrupit)
            VALUES (?, ?, ?, ?)
        """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getEdukatoriId());
            pstm.setInt(2, dto.getMoshaMin());
            pstm.setInt(3, dto.getMoshaMax());
            pstm.setString(4, dto.getEmriGrupit());
            pstm.executeUpdate();

            try (ResultSet res = pstm.getGeneratedKeys()) {
                if (res.next()) {
                    int id = res.getInt(1);
                    return this.getById(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Grupet update(UpdateGrupetDto dto) {
        String query = """
            UPDATE grupet
            SET EmriGrupit = ?, MoshaMin = ?, MoshaMax = ?, EdukatoriId = ?
            WHERE GrupiID = ?
        """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
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

    // Fshin grupin me ID të dhënë
    public boolean delete(int id) {
        String query = "DELETE FROM grupet WHERE GrupiID = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            int affected = pstm.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
