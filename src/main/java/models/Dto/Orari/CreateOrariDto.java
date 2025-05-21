package models.Dto.Orari;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class CreateOrariDto {
    private int femijaID;
    private String dita;
    private String oraHyrjes;
    private String oraDaljes;

    public CreateOrariDto(int femijaID, String dita, String oraHyrjes, String oraDaljes) {
        this.femijaID = femijaID;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public int getFemijaID() { return femijaID; }
    public String getDita() { return dita; }
    public String getOraHyrjes() { return oraHyrjes; }
    public String getOraDaljes() { return oraDaljes; }

    public LocalTime getOraHyrjesAsLocalTime() throws DateTimeParseException {
        return LocalTime.parse(oraHyrjes);
    }
    public LocalTime getOraDaljesAsLocalTime() throws DateTimeParseException {
        return LocalTime.parse(oraDaljes);
    }
}