package repository;


import Database.DBConnection;
import models.domain.Femijet;

import java.sql.*;
import java.util.ArrayList;
abstract class BaseRepository<Model, CreateModelDto, UpdateModelDto> {
    protected Connection connection;
    private String tableName;
    private String idColumn; // e re

    public BaseRepository(String tableName, String idColumn){
        this.connection = DBConnection.getConnection();
        this.tableName = tableName;
        this.idColumn = idColumn;
    }

    abstract Model fromResultSet(ResultSet res) throws SQLException;

    public Model getById(int id){
        String query = "SELECT * FROM " + this.tableName + " WHERE " + this.idColumn + " = ?";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            if(res.next()){
                return this.fromResultSet(res);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Model> getAll(){
        ArrayList<Model> models = new ArrayList<>();
        String query = "SELECT * FROM " + this.tableName;
        try{
            Statement stm = this.connection.createStatement();
            ResultSet res = stm.executeQuery(query);
            while(res.next()){
                models.add(this.fromResultSet(res));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return models;
    }

    public boolean delete(int id){
        String query = "DELETE FROM " + this.tableName + " WHERE " + this.idColumn + " = ?";
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    abstract Model create(CreateModelDto createDto);
    abstract Model update(UpdateModelDto updateDto);
}
