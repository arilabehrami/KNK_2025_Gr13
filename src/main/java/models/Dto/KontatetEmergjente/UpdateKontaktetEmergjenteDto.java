package models.Dto.KontatetEmergjente;

public class UpdateKontaktetEmergjenteDto {
    private int kontaktiId;
    private String emri;
    private String mbiemri;
    private String telefoni;

    public UpdateKontaktetEmergjenteDto(int kontaktiId, String emri, String mbiemri, String telefoni) {
        this.kontaktiId = kontaktiId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.telefoni = telefoni;
    }

    public int getKontaktiId() {
        return kontaktiId;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }
}