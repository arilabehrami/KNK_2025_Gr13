package repository;

import models.domain.Donacionet;
import models.Dto.Donacionet.CreateDonacionetDto;
import models.Dto.Donacionet.UpdateDonacionetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DonacionetRepository extends BaseRepository<Donacionet, CreateDonacionetDto, UpdateDonacionetDto> {

    public DonacionetRepository() {
        super("donacionet");
    }

    @Override
    public Donacionet fromResultSet(ResultSet result) throws SQLException {
        return Donacionet.getInstance(result);
    }

    @Override
    public Donacionet create(CreateDonacionetDto dto) {
        String query = """
            INSERT INTO Donacionet (
                EmriOrganizates, LlojiDonatori, Kontakti, Email,
                Adresa, DataDonacionit, Shuma, LlojiDonacionit, Pershkrimi
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmriOrganizates());
            pstm.setString(2, dto.getLlojiDonatori());
            pstm.setString(3, dto.getKontakti());
            pstm.setString(4, dto.getEmail());
            pstm.setString(5, dto.getAdresa());
            pstm.setString(6, dto.getDataDonacionit());
            pstm.setDouble(7, dto.getShuma());
            pstm.setString(8, dto.getLlojiDonacionit());
            pstm.setString(9, dto.getPershkrimi());
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
    public Donacionet update(UpdateDonacionetDto dto) {
        String query = """
            UPDATE Donacionet SET
                EmriOrganizates = ?, LlojiDonatori = ?, Kontakti = ?, Email = ?,
                Adresa = ?, DataDonacionit = ?, Shuma = ?, LlojiDonacionit = ?, Pershkrimi = ?
            WHERE DonacioniID = ?
        """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmriOrganizates());
            pstm.setString(2, dto.getLlojiDonatori());
            pstm.setString(3, dto.getKontakti());
            pstm.setString(4, dto.getEmail());
            pstm.setString(5, dto.getAdresa());
            pstm.setString(6, dto.getDataDonacionit());
            pstm.setDouble(7, dto.getShuma());
            pstm.setString(8, dto.getLlojiDonacionit());
            pstm.setString(9, dto.getPershkrimi());
            pstm.setInt(10, dto.getDonacioniID());


            int updatedRecords = pstm.executeUpdate();
            if (updatedRecords == 1) {
                return this.getById(dto.getDonacioniID());


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
