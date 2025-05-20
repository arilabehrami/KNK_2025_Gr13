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
                INSERT INTO Financa (financatID, date, teArdhura, shpenzime, pershkrimi)
                VALUES (?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getFinancatID());
            pstm.setString(2, createDto.getDate());
            pstm.setFloat(3, createDto.getTeArdhura());
            pstm.setFloat(4, createDto.getShpenzime());
            pstm.setString(5, createDto.getPershkrimi());
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
