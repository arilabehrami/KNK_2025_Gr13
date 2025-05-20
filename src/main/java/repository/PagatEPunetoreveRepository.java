package repository;

import models.domain.PagatEPunetoreve;
import models.Dto.PagatEPunetoreve.CreatePagatEPunetoreveDto;
import models.Dto.PagatEPunetoreve.UpdatePagatEPunetoreveDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PagatEPunetoreveRepository extends BaseRepository<PagatEPunetoreve, CreatePagatEPunetoreveDto, UpdatePagatEPunetoreveDto> {
    public PagatEPunetoreveRepository(){
        super("PagatEPunetoreve", "PagaID");
    }

    PagatEPunetoreve fromResultSet(ResultSet res) throws SQLException{
        return PagatEPunetoreve.getInstance(res);
    }

    public PagatEPunetoreve create(CreatePagatEPunetoreveDto createDto){
        String query = """
                INSERT INTO PagatEPunetoreve(edukatoriID, muaji, viti, shumaPaga, DataPageses)
                VALUES (?, ?, ?, ?, ?)
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
                SET edukatoriID = ?, muaji = ?, viti = ?, shumaPaga = ?, DataPageses = ?
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

    public ArrayList<PagatEPunetoreve> getAll(){
        ArrayList<PagatEPunetoreve> pagat = new ArrayList<>();
        String query = "SELECT * FROM PagatEPunetoreve";
        try{
            Statement stm = this.connection.createStatement();
            ResultSet res = stm.executeQuery(query);
            while(res.next()){
                pagat.add(fromResultSet(res));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pagat;
    }

    public boolean delete(int pagaID){
        String query = "DELETE FROM PagatEPunetoreve WHERE PagaID = ?";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, pagaID);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected == 1;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
