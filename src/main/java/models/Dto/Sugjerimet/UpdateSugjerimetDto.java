package models.Dto.Sugjerimet;

public class UpdateSugjerimetDto {
    private int sugjerimiId;
    private String emriSugjeruesit;
    private String roli;
    private String data;
    private String pershkrimi;

    public UpdateSugjerimetDto(int sugjerimiId, String emriSugjeruesit, String roli, String data, String pershkrimi) {
        this.sugjerimiId = sugjerimiId;
        this.emriSugjeruesit = emriSugjeruesit;
        this.roli = roli;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public int getSugjerimiId() {
        return sugjerimiId;
    }

    public String getEmriSugjeruesit() {
        return emriSugjeruesit;
    }

    public void setEmriSugjeruesit(String emriSugjeruesit) {
        this.emriSugjeruesit = emriSugjeruesit;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}