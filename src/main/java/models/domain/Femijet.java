package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Femijet {
    private int femijaID;
    private String emri;
    private String mbiemri;
    private String dataLindjes;
    private String gjinia;
    private String adresa;
    private String emriPrindit;
    private String kontaktiPrindit;

    public Femijet(int femijaID, String emri, String mbiemri, String dataLindjes,
                   String gjinia, String adresa, String emriPrindit, String kontaktiPrindit) {
        this.femijaID = femijaID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.dataLindjes = dataLindjes;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.emriPrindit = emriPrindit;
        this.kontaktiPrindit = kontaktiPrindit;
    }

    public static Femijet getInstance(ResultSet result) throws SQLException {
        int femijaId = result.getInt("FemijaID");
        String emri = result.getString("Emri");
        String mbiemri = result.getString("Mbiemri");
        String datalindjes = result.getString("DataLindjes");
        String gjinia = result.getString("Gjinia");
        String adresa = result.getString("Adresa");
        String emriPrindit = result.getString("EmriPrindit");
        String kontaktiPrindit = result.getString("KontaktiPrindit");

        return new Femijet(femijaId, emri, mbiemri, datalindjes, gjinia, adresa, emriPrindit, kontaktiPrindit);
    }

    public int getFemijaID() {
        return femijaID;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getDataLindjes() {
        return dataLindjes;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmriPrindit() {
        return emriPrindit;
    }

    public String getKontaktiPrindit() {
        return kontaktiPrindit;
    }
}