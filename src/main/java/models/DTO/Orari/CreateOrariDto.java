package models.DTO.Orari;

public class CreateOrariDto {
    private int femijaId;
    private String dita;
    private String oraHyrjes;
    private String oraDaljes;

    public CreateOrariDto(int femijaId, String dita, String oraHyrjes, String oraDaljes) {
        this.femijaId = femijaId;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public int getFemijaId() {
        return femijaId;
    }

    public void setFemijaId(int femijaId) {
        this.femijaId = femijaId;
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
