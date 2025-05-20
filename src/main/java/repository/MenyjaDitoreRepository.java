package repository;

import models.domain.MenyjaDitore;
import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import models.Dto.MenyjaDitore.UpdateMenyjaDitoreDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenyjaDitoreRepository extends BaseRepository<MenyjaDitore, CreateMenyjaDitoreDto, UpdateMenyjaDitoreDto> {

    public MenyjaDitoreRepository() {
        super("menyjaditore", "MenuID");
    }

    @Override
    MenyjaDitore fromResultSet(ResultSet res) throws SQLException {
        return MenyjaDitore.getInstance(res);
    }

    @Override
    public MenyjaDitore create(CreateMenyjaDitoreDto dto) {
        String query = "INSERT INTO menyjaditore (Dita, GrupiID, UshqimiID) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getDita());
            pstm.setInt(2, dto.getGrupiID());
            pstm.setInt(3, dto.getUshqimiID());
            pstm.execute();

            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MenyjaDitore update(UpdateMenyjaDitoreDto dto) {
        String query = "UPDATE menyjaditore SET Dita = ?, GrupiID = ?, UshqimiID = ? WHERE MenuID = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getDita());
            pstm.setInt(2, dto.getGrupiID());
            pstm.setInt(3, dto.getUshqimiID());
            pstm.setInt(4, dto.getMenuID());
            int rows = pstm.executeUpdate();
            if (rows == 1) return getById(dto.getMenuID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<MenyjaDitore> getAll() {
        List<MenyjaDitore> list = new ArrayList<>();
        String query = "SELECT * FROM menyjaditore";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                list.add(fromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<MenyjaDitore>) list;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM menyjaditore WHERE MenuID = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
