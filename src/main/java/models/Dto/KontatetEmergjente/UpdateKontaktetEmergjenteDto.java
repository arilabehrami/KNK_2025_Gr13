package models.Dto.KontatetEmergjente;

public class UpdateKontaktetEmergjenteDto {
    private int kontaktiId;
    private String telefoni;

    public UpdateKontaktetEmergjenteDto(int kontaktiId, String telefoni) {
        this.kontaktiId = kontaktiId;
        this.telefoni = telefoni;
    }

    public int getKontaktiId() {
        return kontaktiId;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }
}
