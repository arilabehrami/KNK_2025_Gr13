package models.dto.Donacionet;

public class UpdateDonacionetDto {
    private String emriOrganizates;
    private String llojiDonatori;
    private String kontakti;
    private String email;
    private String adresa;
    private String dataDonacionit;
    private Double shuma;
    private String llojiDonacionit;
    private String pershkrimi;

    public UpdateDonacionetDto(String emriOrganizates, String llojiDonatori, String kontakti,
                               String email, String adresa, String dataDonacionit,
                               Double shuma, String llojiDonacionit, String pershkrimi) {
        this.emriOrganizates = emriOrganizates;
        this.llojiDonatori = llojiDonatori;
        this.kontakti = kontakti;
        this.email = email;
        this.adresa = adresa;
        this.dataDonacionit = dataDonacionit;
        this.shuma = shuma;
        this.llojiDonacionit = llojiDonacionit;
        this.pershkrimi = pershkrimi;
    }

    public String getEmriOrganizates() {
        return emriOrganizates;
    }

    public void setEmriOrganizates(String emriOrganizates) {
        this.emriOrganizates = emriOrganizates;
    }

    public String getLlojiDonatori() {
        return llojiDonatori;
    }

    public void setLlojiDonatori(String llojiDonatori) {
        this.llojiDonatori = llojiDonatori;
    }

    public String getKontakti() {
        return kontakti;
    }

    public void setKontakti(String kontakti) {
        this.kontakti = kontakti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDataDonacionit() {
        return dataDonacionit;
    }

    public void setDataDonacionit(String dataDonacionit) {
        this.dataDonacionit = dataDonacionit;
    }

    public Double getShuma() {
        return shuma;
    }

    public void setShuma(Double shuma) {
        this.shuma = shuma;
    }

    public String getLlojiDonacionit() {
        return llojiDonacionit;
    }

    public void setLlojiDonacionit(String llojiDonacionit) {
        this.llojiDonacionit = llojiDonacionit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}
