package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Femijet {
    private int FemijaID;
    private String Emri;
    private String Mbiemri;
    private String DataLindjes;
    private boolean Gjinia;
    private String Adresa;
    private String EmriPrindit;
    private String KontaktiPrindit;

    public Femijet(int femijaID, String emri, String mbiemri, String dataLindjes, boolean gjinia, String adresa, String emriPrindit, String kontaktiPrindit) {
        FemijaID = femijaID;
        Emri = emri;
        Mbiemri = mbiemri;
        DataLindjes = dataLindjes;
        Gjinia = gjinia;
        Adresa = adresa;
        EmriPrindit = emriPrindit;
        KontaktiPrindit = kontaktiPrindit;
    }
    public static Femijet getInstance(ResultSet result) throws SQLException {
        int femijaId = result.getInt("FemijaId");
        String emri = result.getString("Emri");
        String mbiemri = result.getString("Mbiemri");
        String datalindjes=result.getString("Datalindjes");
        Boolean gjinia = result.getBoolean("Gjinia");
        String adresa = result.getString("Adresa");
        String emriPrindit=result.getString("EmriPrindit");
        String kontaktiPrindit = result.getString("KontaktiPrindit");
        return new Femijet(femijaId, emri,mbiemri,datalindjes,gjinia,adresa,emriPrindit,kontaktiPrindit);

    }

    public int getFemijaID() {
        return FemijaID;
    }

    public String getEmri() {
        return Emri;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public String getDataLindjes() {
        return DataLindjes;
    }

    public boolean isGjinia() {
        return Gjinia;
    }

    public String getAdresa() {
        return Adresa;
    }

    public String getEmriPrindit() {
        return EmriPrindit;
    }

    public String getKontaktiPrindit() {
        return KontaktiPrindit;
    }
}


