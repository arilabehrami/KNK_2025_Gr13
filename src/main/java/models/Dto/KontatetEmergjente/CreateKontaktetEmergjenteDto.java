package models.Dto.KontatetEmergjente;

public class CreateKontaktetEmergjenteDto {
    private int femijaId;
    private String emri;
    private String mbiemri;
    private String telefoni;

    public CreateKontaktetEmergjenteDto(int femijaId, String emri, String mbiemri, String telefoni) {
        this.femijaId = femijaId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.telefoni = telefoni;
    }

    public int getFemijaId() {
        return femijaId;
    }

    public void setFemijaId(int femijaId) {
        this.femijaId = femijaId;
    }

    public  String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }
}
