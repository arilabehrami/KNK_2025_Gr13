package repository;

import models.domain.Femijet;
import models.Dto.Femijet.CreateFemijetDto;
import models.Dto.Femijet.UpdateFemijetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class FemijetRepository extends BaseRepository<Femijet, CreateFemijetDto, UpdateFemijetDto> {

    public FemijetRepository() {
        super("femijet", "FemijaID");
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
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, femijetDto.getEmri());
            pstm.setString(2, femijetDto.getMbiemri());

            // Konverto LocalDate në java.sql.Date
            LocalDate dataLindjes = LocalDate.parse(femijetDto.getDataLindjes()); // Sigurohu që kthen LocalDate
            pstm.setDate(3, java.sql.Date.valueOf(dataLindjes));

            // Nëse gjinia është String, përdore setString, nëse boolean → përdor setBoolean
            pstm.setString(4, femijetDto.isGjinia()); // ose setBoolean(4, femijetDto.isGjinia());

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
                SET Emri = ?, Mbiemri = ?, DataLindjes = ?, Gjinia = ?, Adresa = ?, EmriPrindit = ?, KontaktiPrindit = ?
                WHERE FemijaID = ?
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, femijetDto.getEmri());
            pstm.setString(2, femijetDto.getMbiemri());

            // Konverto LocalDate në java.sql.Date
            LocalDate dataLindjes = LocalDate.parse(femijetDto.getDataLindjes()); // Sigurohu që kthen LocalDate
            pstm.setDate(3, java.sql.Date.valueOf(dataLindjes));

            pstm.setString(4, femijetDto.isGjinia()); // ose setBoolean nëse është boolean
            pstm.setString(5, femijetDto.getAdresa());
            pstm.setString(6, femijetDto.getEmriPrindit());
            pstm.setString(7, femijetDto.getKontaktiPrindit());
            pstm.setInt(8, femijetDto.getFemijaID());

            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(femijetDto.getFemijaID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Femijet> getAll() {
        ArrayList<Femijet> femijetList = new ArrayList<>();
        String query = "SELECT * FROM FEMIJET";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                femijetList.add(fromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return femijetList;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM FEMIJET WHERE FemijaID = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
