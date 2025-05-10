package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sugjerimet {

    private int sugjerimiID;
    private String emriSugjeruesit;
    private String roli;
    private java.sql.Date data;
    private String pershkrimi;

    private Sugjerimet(int sugjerimiID, String emriSugjeruesit, String roli, java.sql.Date data, String pershkrimi) {
        this.sugjerimiID = sugjerimiID;
        this.emriSugjeruesit = emriSugjeruesit;
        this.roli = roli;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public static Sugjerimet getInstance(ResultSet result) throws SQLException {
        int sugjerimiID = result.getInt("SugjerimiID");
        String emriSugjeruesit = result.getString("EmriSugjeruesit");
        String roli = result.getString("Roli");
        java.sql.Date data = result.getDate("Data");
        String pershkrimi = result.getString("Pershkrimi");
        return new Sugjerimet(sugjerimiID, emriSugjeruesit, roli, data, pershkrimi);
    }

    // Getters
    public int getSugjerimiID() {
        return sugjerimiID;
    }

    public String getEmriSugjeruesit() {
        return emriSugjeruesit;
    }

    public String getRoli() {
        return roli;
    }

    public java.sql.Date getData() {
        return data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}