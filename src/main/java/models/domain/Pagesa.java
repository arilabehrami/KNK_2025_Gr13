package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pagesa {
    private int pagesaId;
    private int femijaId;
    private double shuma;
    private String data;
    private String pershkrimi;

    private Pagesa(int pagesaId, int femijaId, double shuma, String data, String pershkrimi){
        this.pagesaId = pagesaId;
        this.femijaId = femijaId;
        this.shuma = shuma;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public static Pagesa getInstance(ResultSet result) throws SQLException{
        int pagesaId = result.getInt("PagesaID");
        int femijaId = result.getInt("FemijaID");
        double shuma = result.getDouble("Shuma");
        String data = result.getString("DataPageses");
        String pershkrimi = result.getString("Pershkrimi");
        return new Pagesa(pagesaId, femijaId, shuma, data, pershkrimi);
    }

    public int getPagesaId(){
        return pagesaId;
    }

    public int getFemijaId(){
        return femijaId;
    }

    public double getShuma(){
        return shuma;
    }

    public String getData(){
        return data;
    }

    public String getPershkrimi(){
        return pershkrimi;
    }
}
