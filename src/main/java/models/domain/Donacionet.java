package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Donacionet {
    private int donacioniId;
    private String emriOrganizates;
    private String llojiDonatori;
    private String kontakti;
    private String email;
    private String adresa;
    private String dataDonacionit;
    private double shuma;
    private String llojiDonacionit;
    private String pershkrimi;

    public Donacionet(int donacioniId, String emriOrganizates, String llojiDonatori, String kontakti,
                      String email, String adresa, String dataDonacionit, double shuma,
                      String llojiDonacionit, String pershkrimi) {
        this.donacioniId = donacioniId;
        this.emriOrganizates = emriOrganizates;
        this.llojiDonatori = llojiDonatori;
        this.kontakti = kontakti;
        this.email = email;
        this.adresa = adresa;
        this.dataDonacionit = dataDonacionit;
        this.shuma = shuma;
        this.llojiDonacionit = llojiDonacionit;
        this.pershkrimi = pershkrimi;
    }

    public static Donacionet getInstance(ResultSet result) throws SQLException {
        int donacioniId = result.getInt("DonacioniID");
        String emriOrganizates = result.getString("EmriOrganizates");
        String llojiDonatori = result.getString("LlojiDonatori");
        String kontakti = result.getString("Kontakti");
        String email = result.getString("Email");
        String adresa = result.getString("Adresa");
        String dataDonacionit = result.getString("DataDonacionit");
        double shuma = result.getDouble("Shuma");
        String llojiDonacionit = result.getString("LlojiDonacionit");
        String pershkrimi = result.getString("Pershkrimi");

        return new Donacionet(donacioniId, emriOrganizates, llojiDonatori, kontakti, email,
                adresa, dataDonacionit, shuma, llojiDonacionit, pershkrimi);
    }

    public int getDonacioniId() {
        return donacioniId;
    }

    public String getEmriOrganizates() {
        return emriOrganizates;
    }

    public String getLlojiDonatori() {
        return llojiDonatori;
    }

    public String getKontakti() {
        return kontakti;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getDataDonacionit() {
        return dataDonacionit;
    }

    public double getShuma() {
        return shuma;
    }

    public String getLlojiDonacionit() {
        return llojiDonacionit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public int getDonacioniID() {
        return donacioniId;
    }
}
