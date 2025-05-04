package repository;

import models.domain.PagatEPunetoreve;
import models.dto.PagatEPunetoreve.CreatePagatEPunetoreveDto;
import models.dto.PagatEPunetoreve.UpdatePagatEPunetoreveDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagatEPunetoreveRepository extends BaseRepository<PagatEPunetoreve, CreatePagatEPunetoreveDto, UpdatePagatEPunetoreveDto> {
    public PagatEPunetoreveRepository(){
        super("paga");
    }

    PagatEPunetoreve fromResultSet(ResultSet res) throws SQLException{
        return PagatEPunetoreve.getInstance(res);
    }

    public PagatEPunetoreve create(CreatePagatEPunetoreveDto createDto){
        String query = """
                INSERT INTO PagatEPunetoreve(pagaID, edukatoriID, muaji, viti, shumaPaga, dataEPageses)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getEdukatoriID());
            pstm.setString(2, createDto.getMuaji());
            pstm.setInt(3, createDto.getViti());
            pstm.setDouble(4, createDto.getShumaPaga());
            pstm.setString(5, createDto.getDataEPageses());
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
    public PagatEPunetoreve update(UpdatePagatEPunetoreveDto updateDto){
        String query = """
                UPDATE PagatEPunetoreve
                SET edukatoriID = ?, muaji = ?, viti = ?, shumaPaga = ?, dataEPageses = ?
                WHERE pagaID = ?
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getEdukatoriID());
            pstm.setString(2, updateDto.getMuaji());
            pstm.setInt(3, updateDto.getViti());
            pstm.setDouble(4, updateDto.getShumaPaga());
            pstm.setString(5, updateDto.getDataEPageses());
            pstm.setInt(6, updateDto.getPagaID());
            int updatedPaga = pstm.executeUpdate();
            if(updatedPaga == 1){
                return this.getById(updateDto.getPagaID());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
