package models.Dto.Orari;

import java.time.LocalTime;

public class UpdateOrariDto {
    private int orariID;
    private int femijaID;
    private String dita;
    private String oraHyrjes;
    private String oraDaljes;

    public UpdateOrariDto(int orariID, int femijaID, String dita, String oraHyrjes, String oraDaljes) {
        this.orariID = orariID;
        this.femijaID = femijaID;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public int getOrariID() { return orariID; }
    public int getFemijaID() { return femijaID; }
    public String getDita() { return dita; }
    public String getOraHyrjes() { return oraHyrjes; }
    public String getOraDaljes() { return oraDaljes; }

    public LocalTime getOraHyrjesAsLocalTime() {
        return LocalTime.parse(oraHyrjes);
    }
    public LocalTime getOraDaljesAsLocalTime() {
        return LocalTime.parse(oraDaljes);
    }
}