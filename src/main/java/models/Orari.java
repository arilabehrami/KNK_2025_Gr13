package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Orari {

    private int orariID;
    private int femijaID;
    private String dita;
    private Time oraHyrjes;
    private Time oraDaljes;

    private Orari(int orariID, int femijaID, String dita, Time oraHyrjes, Time oraDaljes) {
        this.orariID = orariID;
        this.femijaID = femijaID;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public static Orari getInstance(ResultSet result) throws SQLException {
        int orariID = result.getInt("OrariID");
        int femijaID = result.getInt("FemijaID");
        String dita = result.getString("dita");
        Time oraHyrjes = result.getTime("ora_hyrjes");
        Time oraDaljes = result.getTime("ora_daljes");
        return new Orari(orariID, femijaID, dita, oraHyrjes, oraDaljes);
    }

    public int getOrariID() {
        return orariID;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public String getDita() {
        return dita;
    }

    public Time getOraHyrjes() {
        return oraHyrjes;
    }

    public Time getOraDaljes() {
        return oraDaljes;
    }
}
