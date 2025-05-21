package repository;

import models.Dto.Sugjerimet.CreateSugjerimetDto;
import models.Dto.Sugjerimet.UpdateSugjerimetDto;
import models.domain.Sugjerimet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SugjerimetRepository extends BaseRepository<Sugjerimet, CreateSugjerimetDto, UpdateSugjerimetDto> {

    public SugjerimetRepository() {
        super("Sugjerimet", "SugjerimiID");
    }

    @Override
    public Sugjerimet fromResultSet(ResultSet result) throws SQLException {
        return Sugjerimet.getInstance(result);
    }

    public Sugjerimet create(CreateSugjerimetDto sugjerimiDto) {
        String query = """
                INSERT INTO Sugjerimet (EmriSugjeruesit, Roli, Data, Pershkrimi)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, sugjerimiDto.getEmriSugjeruesit());
            pstm.setString(2, sugjerimiDto.getRoli());
            pstm.setDate(3, java.sql.Date.valueOf(sugjerimiDto.getData().toLocalDate()));
            pstm.setString(4, sugjerimiDto.getPershkrimi());
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

    public Sugjerimet update(UpdateSugjerimetDto sugjerimiDto) {
        String query = """
                UPDATE Sugjerimet
                SET EmriSugjeruesit = ?, Roli = ?, Data = ?, Pershkrimi = ?
                WHERE SugjerimiID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, sugjerimiDto.getEmriSugjeruesit());
            pstm.setString(2, sugjerimiDto.getRoli());
            pstm.setDate(3, java.sql.Date.valueOf(sugjerimiDto.getData().toLocalDate()));
            pstm.setString(4, sugjerimiDto.getPershkrimi());
            pstm.setInt(5, sugjerimiDto.getSugjerimiId());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(sugjerimiDto.getSugjerimiId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sugjerimet> getAll() {
        List<Sugjerimet> sugjerimet = new ArrayList<>();
        String query = "SELECT * FROM Sugjerimet ORDER BY SugjerimiID DESC";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Sugjerimet s = fromResultSet(resultSet);
                System.out.println("Ngarkuar: " + s.getEmriSugjeruesit());
                sugjerimet.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Sugjerimet>) sugjerimet;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM Sugjerimet WHERE SugjerimiID = ?";
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