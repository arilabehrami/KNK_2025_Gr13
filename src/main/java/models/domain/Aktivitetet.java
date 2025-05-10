package models.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aktivitetet {

    private int aktivitetiID;
    private String emriAktivitetit;
    private String pershkrimi;
    private Date data;
    private int grupiID;

    private Aktivitetet(int aktivitetiID, String emriAktivitetit, String pershkrimi, Date data, int grupiID) {
        this.aktivitetiID = aktivitetiID;
        this.emriAktivitetit = emriAktivitetit;
        this.pershkrimi = pershkrimi;
        this.data = data;
        this.grupiID = grupiID;
    }

    public static Aktivitetet getInstance(ResultSet result) throws SQLException {
        int aktivitetiID = result.getInt("AktivitetiID");
        String emriAktivitetit = result.getString("EmriAktivitetit");
        String pershkrimi = result.getString("Pershkrimi");
        Date data = result.getDate("Data");
        int grupiID = result.getInt("GrupiID");
        return new Aktivitetet(aktivitetiID, emriAktivitetit, pershkrimi, data, grupiID);
    }

    public int getAktivitetiID() {
        return aktivitetiID;
    }

    public String getEmriAktivitetit() {
        return emriAktivitetit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public Date getData() {
        return data;
    }

    public int getGrupiID() {
        return grupiID;
    }
}