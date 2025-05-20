package repository;

import models.domain.Femijet;
import models.Dto.Femijet.CreateFemijetDto;
import models.Dto.Femijet.UpdateFemijetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FemijetRepository extends BaseRepository<Femijet, CreateFemijetDto, UpdateFemijetDto> {
    public FemijetRepository() {
<<<<<<< HEAD
        super("femijet", "FemijaID");
=======
        super("femijet","FemijaId");
>>>>>>> 7c21c2b827342a1185de7ec55bc9781bbe876811
    }

    @Override
    public Femijet fromResultSet(ResultSet result) throws SQLException {
        return Femijet.getInstance(result);
    }

    @Override
    public Femijet create(CreateFemijetDto femijetDto) {
        String query = """
            INSERT INTO
            FEMIJET (Emri, Mbiemri, DataLindjes, Gjinia, Adresa, EmriPrindit, KontaktiPrindit)
            VALUES (?, ? , ?, ?, ?, ?, ?)
            """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, femijetDto.getEmri());
            pstm.setString(2, femijetDto.getMbiemri());
            pstm.setString(3, femijetDto.getDataLindjes());
            pstm.setString(4, femijetDto.isGjinia());

            pstm.setString(5, femijetDto.getAdresa());
            pstm.setString(6, femijetDto.getEmriPrindit());
            pstm.setString(7, femijetDto.getKontaktiPrindit());
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
    public Femijet update(UpdateFemijetDto femijetDto) {
        String query = """
                UPDATE FEMIJET
                SET Emri = ?
                WHERE FemijaID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, femijetDto.getEmri());
            pstm.setInt(2, femijetDto.getFemijaID());
            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(femijetDto.getFemijaID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

