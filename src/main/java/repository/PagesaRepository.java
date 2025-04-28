package repository;

import models.domain.Pagesa;
import models.dto.Pagesa.CreatePagesaDto;
import models.dto.Pagesa.UpdatePagesaDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagesaRepository extends BaseRepository<Pagesa, CreatePagesaDto, UpdatePagesaDto>{
    public PagesaRepository(){
        super("pagesa");
    }

    Pagesa fromResultSet(ResultSet res) throws SQLException{
        return Pagesa.getInstance(res);
    }

    public Pagesa create(CreatePagesaDto createPagesaDto){
        String query = """
                INSERT INTO Pagesat (PagesaID, FemijaID, Shuma, DataPageses, Pershkrimi)
                VALUES (?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createPagesaDto.getFemijaId());
            pstm.setDouble(2, createPagesaDto.getShuma());
            pstm.setString(3, createPagesaDto.getData());
            pstm.setString(4, createPagesaDto.getPershkrimi());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                        return this.getById(id);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Pagesa update(UpdatePagesaDto updatePagesaDto){
        String query = """
                UPDATE Pagesat
                SET FemijaID = ?, Shuma = ?, DataPageses = ?, Pershkrimi = ?
                WHERE PagesaID = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updatePagesaDto.getFemijaId());
            pstm.setDouble(2, updatePagesaDto.getShuma());
            pstm.setString(3, updatePagesaDto.getData());
            pstm.setString(4, updatePagesaDto.getPershkrimi());
            pstm.setInt(5, updatePagesaDto.getPagesaId());
            int updatedPagesa = pstm.executeUpdate();
            if(updatedPagesa == 1){
                return this.getById(updatePagesaDto.getPagesaId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
