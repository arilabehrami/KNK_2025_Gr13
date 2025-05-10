package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KontaktiEmergjent {

    private int kontaktiID;
    private int femijaID;
    private String emri;
    private String mbiemri;
    private String telefoni;

    private KontaktiEmergjent(int kontaktiID, int femijaID, String emri, String mbiemri, String telefoni) {
        this.kontaktiID = kontaktiID;
        this.femijaID = femijaID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.telefoni = telefoni;
    }

    public static KontaktiEmergjent getInstance(ResultSet result) throws SQLException {
        int kontaktiID = result.getInt("KontaktiID");
        int femijaID = result.getInt("FemijaID");
        String emri = result.getString("Emri");
        String mbiemri = result.getString("Mbiemri");
        String telefoni = result.getString("Telefoni");
        return new KontaktiEmergjent(kontaktiID, femijaID, emri, mbiemri, telefoni);
    }

    public int getKontaktiID() {
        return kontaktiID;
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

    public String getTelefoni() {
        return telefoni;
    }
}