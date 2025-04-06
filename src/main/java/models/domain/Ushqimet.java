package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ushqimet {

    private int ushqimiID;
    private String emriUshqimit;
    private String kategoria;
    private String pershkrimi;

    private Ushqimet(int ushqimiID, String emriUshqimit, String kategoria, String pershkrimi) {
        this.ushqimiID = ushqimiID;
        this.emriUshqimit = emriUshqimit;
        this.kategoria = kategoria;
        this.pershkrimi = pershkrimi;
    }

    public static Ushqimet getInstance(ResultSet result) throws SQLException {
        int ushqimiID = result.getInt("UshqimiID");
        String emriUshqimit = result.getString("EmriUshqimit");
        String kategoria = result.getString("Kategoria");
        String pershkrimi = result.getString("Pershkrimi");
        return new Ushqimet(ushqimiID, emriUshqimit, kategoria, pershkrimi);
    }

    public int getUshqimiID() {
        return ushqimiID;
    }

    public String getEmriUshqimit() {
        return emriUshqimit;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}
