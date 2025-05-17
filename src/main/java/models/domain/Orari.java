package models.domain;

import java.time.LocalTime;

public class Orari {
    private int orariID;
    private int femijaID;
    private String dita;
    private LocalTime oraHyrjes;
    private LocalTime oraDaljes;

    public Orari() {}

    public Orari(int orariID, int femijaID, String dita, LocalTime oraHyrjes, LocalTime oraDaljes) {
        this.orariID = orariID;
        this.femijaID = femijaID;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public int getOrariID() { return orariID; }
    public void setOrariID(int orariID) { this.orariID = orariID; }

    public int getFemijaID() { return femijaID; }
    public void setFemijaID(int femijaID) { this.femijaID = femijaID; }

    public String getDita() { return dita; }
    public void setDita(String dita) { this.dita = dita; }

    public LocalTime getOraHyrjes() { return oraHyrjes; }
    public void setOraHyrjes(LocalTime oraHyrjes) { this.oraHyrjes = oraHyrjes; }

    public LocalTime getOraDaljes() { return oraDaljes; }
    public void setOraDaljes(LocalTime oraDaljes) { this.oraDaljes = oraDaljes; }
}
