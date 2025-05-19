package repository;

import models.domain.Prinderit;
import models.Dto.Prinderit.CreatePrinderitDto;
import models.Dto.Prinderit.UpdatePrinderitDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrinderitRepository extends BaseRepository<Prinderit, CreatePrinderitDto, UpdatePrinderitDto> {

    public PrinderitRepository() {
        super("Prinderit");
    }

    @Override
    public Prinderit fromResultSet(ResultSet result) throws SQLException {
        return Prinderit.getInstance(result);
    }

    @Override
    public Prinderit getById(int id) {
        String query = "SELECT * FROM Prinderit WHERE PrindiID = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return this.fromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Prinderit WHERE PrindiID = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Prinderit create(CreatePrinderitDto dto) {
        String query = """
                INSERT INTO Prinderit (Emri, Mbiemri, Telefoni, Email, Adresa, LlojiLidhjes)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri());
            pstm.setString(2, dto.getMbiemri());
            pstm.setString(3, dto.getTelefoni());
            pstm.setString(4, dto.getEmail());
            pstm.setString(5, dto.getAdresa());
            pstm.setString(6, dto.getLlojiLidhjes());
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
    public Prinderit update(UpdatePrinderitDto dto) {
        String query = """
                UPDATE Prinderit
                SET Emri = ?, Mbiemri = ?, Telefoni = ?, Email = ?, Adresa = ?, LlojiLidhjes = ?
                WHERE PrindiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri());
            pstm.setString(2, dto.getMbiemri());
            pstm.setString(3, dto.getTelefoni());
            pstm.setString(4, dto.getEmail());
            pstm.setString(5, dto.getAdresa());
            pstm.setString(6, dto.getLlojiLidhjes());
            pstm.setInt(7, dto.getPrindiID());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getPrindiID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Prinderit> getAll() {
        List<Prinderit> list = new ArrayList<>();
        String query = "SELECT * FROM Prinderit ORDER BY PrindiID";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                list.add(fromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Prinderit>) list;
    }

}
