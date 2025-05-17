package models.Dto.Aktivitetet;

public class UpdateAktivitetetDto {
    private int aktivitetiID;
    private String emriAktivitetit;
    private String pershkrimi;
    private String data;
    private int grupiID;

    public UpdateAktivitetetDto(int aktivitetiID, String emriAktivitetit, String pershkrimi, String data, int grupiID) {
        this.aktivitetiID = aktivitetiID;
        this.emriAktivitetit = emriAktivitetit;
        this.pershkrimi = pershkrimi;
        this.data = data;
        this.grupiID = grupiID;
    }

    public int getAktivitetiID() {
        return aktivitetiID;
    }

    public void setAktivitetiID(int aktivitetiID) {
        this.aktivitetiID = aktivitetiID;
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

    public int getGrupiID() {
        return grupiID;
    }

    public void setGrupiID(int grupiID) {
        this.grupiID = grupiID;
    }
}
