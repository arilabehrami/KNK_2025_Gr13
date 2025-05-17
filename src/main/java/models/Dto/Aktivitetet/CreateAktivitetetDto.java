package models.Dto.Aktivitetet;

public class CreateAktivitetetDto {
    private String emriAktivitetit;
    private String pershkrimi;
    private String data;
    private int grupiID;

    public CreateAktivitetetDto(String emriAktivitetit, String pershkrimi, String data, int grupiID) {
        this.emriAktivitetit = emriAktivitetit;
        this.pershkrimi = pershkrimi;
        this.data = data;
        this.grupiID = grupiID;
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
