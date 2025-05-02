package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Financat {
    private int financatID;
    private String date;
    private float teArdhura;
    private float shpenzime;
    private String pershkrimi;

    public Financat(int financatID, String date, float teArdhura, float shpenzime, String pershkrimi) {
        financatID = financatID;
        this.date = date;
        this.teArdhura = teArdhura;
        this.shpenzime = shpenzime;
        this.pershkrimi = pershkrimi;
    }

    public static Financat getInstance(ResultSet result) throws SQLException{
        int financatID = result.getInt("FinancatID");
        String date = result.getString("Data");
        float teArdhura = result.getFloat("teArdhura");
        float shpenzime = result.getFloat("Shpenzime");
        String pershkrimi = result.getString("Pershkrimi");
        return new Financat(financatID, date, teArdhura, shpenzime, pershkrimi);
    }

    public int getFinancatID() {
        return financatID;
    }

    public String getDate() {
        return date;
    }

    public float getTeArdhura() {
        return teArdhura;
    }

    public float getShpenzime() {
        return shpenzime;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}
