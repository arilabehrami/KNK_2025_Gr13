package models.Dto.Aktivitetet;

public class UpdateAktivitetetDto {
    private int aktivitetiId;
    private String emriAktivitetit;
    private String pershkrimi;
    private String data;
    private int grupiId;

    public UpdateAktivitetetDto(int aktivitetiId, String emriAktivitetit, String pershkrimi, String data, int grupiId) {
        this.aktivitetiId = aktivitetiId;
        this.emriAktivitetit = emriAktivitetit;
        this.pershkrimi = pershkrimi;
        this.data = data;
        this.grupiId = grupiId;
    }

    public int getAktivitetiId() {
        return aktivitetiId;
    }

    public void setAktivitetiId(int aktivitetiId) {
        this.aktivitetiId = aktivitetiId;
    }

    public String getEmriAktivitetit() {
        return emriAktivitetit;
    }

    public void setEmriAktivitetit(String emriAktivitetit) {
        this.emriAktivitetit = emriAktivitetit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getGrupiId() {
        return grupiId;
    }

    public void setGrupiId(int grupiId) {
        this.grupiId = grupiId;
    }
}
