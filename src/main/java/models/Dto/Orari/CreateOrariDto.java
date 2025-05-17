package models.Dto.Orari;

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

    public int getFemijaID() {
        return femijaID;
    }

    public void setFemijaID(int femijaID) {
        this.femijaID = femijaID;
    }

    public String getDita() {
        return dita;
    }

    public void setDita(String dita) {
        this.dita = dita;
    }

    public String getOraHyrjes() {
        return oraHyrjes;
    }

    public void setOraHyrjes(String oraHyrjes) {
        this.oraHyrjes = oraHyrjes;
    }

    public String getOraDaljes() {
        return oraDaljes;
    }

    public void setOraDaljes(String oraDaljes) {
        this.oraDaljes = oraDaljes;
    }
}