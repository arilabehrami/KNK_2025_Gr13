package models.domain;

import java.time.LocalDate;

public class Aktivitetet {
    private int aktivitetiID;
    private String emriAktivitetit;
    private String pershkrimi;
    private LocalDate data;
    private int grupiID;

    // Konstruktor bosh për raste të veçanta (opsionale)
    public Aktivitetet() {
    }

    public Aktivitetet(int aktivitetiID, String emriAktivitetit, String pershkrimi, LocalDate data, int grupiID) {
        this.aktivitetiID = aktivitetiID;
        this.emriAktivitetit = emriAktivitetit;
        this.pershkrimi = pershkrimi;
        this.data = data;
        this.grupiID = grupiID;
    }

    public Aktivitetet(String emriAktivitetit, String pershkrimi, LocalDate data, int grupiID) {
        this(0, emriAktivitetit, pershkrimi, data, grupiID);
    }

    // Getters & Setters
    public int getAktivitetiID() { return aktivitetiID; }
    public void setAktivitetiID(int aktivitetiID) { this.aktivitetiID = aktivitetiID; }
    public String getEmriAktivitetit() { return emriAktivitetit; }
    public void setEmriAktivitetit(String emriAktivitetit) { this.emriAktivitetit = emriAktivitetit; }
    public String getPershkrimi() { return pershkrimi; }
    public void setPershkrimi(String pershkrimi) { this.pershkrimi = pershkrimi; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public int getGrupiID() { return grupiID; }
    public void setGrupiID(int grupiID) { this.grupiID = grupiID; }
}
