package models.DTO.Orari;

public class UpdateOrariDto {
    private int orariId;
    private int femijaId;
    private String dita;
    private String oraHyrjes;
    private String oraDaljes;

    public UpdateOrariDto(int orariId, int femijaId, String dita, String oraHyrjes, String oraDaljes) {
        this.orariId = orariId;
        this.femijaId = femijaId;
        this.dita = dita;
        this.oraHyrjes = oraHyrjes;
        this.oraDaljes = oraDaljes;
    }

    public int getOrariId() {
        return orariId;
    }

    public void setOrariId(int orariId) {
        this.orariId = orariId;
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
