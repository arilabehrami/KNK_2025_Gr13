package repository;

import models.Dto.Financat.CreateFinancatDto;
import models.Dto.Financat.UpdateFinancatDto;
import models.domain.Financat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FinancatRepository extends BaseRepository<Financat, CreateFinancatDto, UpdateFinancatDto> {
    public FinancatRepository(){
        super("financa", "FinancatID");
    }

    @Override
    Financat fromResultSet(ResultSet res) throws SQLException {
        return Financat.getInstance(res);
    }

    @Override
    public Financat create(CreateFinancatDto createDto) {
        String query = """
                INSERT INTO Financat (Data, teArdhura, Shpenzime, Pershkrimi
                VALUES (?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getDate());
            pstm.setFloat(2, createDto.getTeArdhura());
            pstm.setFloat(3, createDto.getShpenzime());
            pstm.setString(4, createDto.getPershkrimi());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Financat update(UpdateFinancatDto updateDto) {
        String query = """
                UPDATE Financa
                SET date = ?, teArdhura = ?, shpenzime = ?, Pershkrimi = ?
                WHERE FinancatID = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updateDto.getDate());
            pstm.setFloat(2, updateDto.getTeArdhura());
            pstm.setFloat(3, updateDto.getShpenzime());
            pstm.setString(4, updateDto.getPershkrimi());
            pstm.setInt(5, updateDto.getFinancatID());
            int updatedFinanca = pstm.executeUpdate();
            if(updatedFinanca == 1){
                return this.getById(updateDto.getFinancatID());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
