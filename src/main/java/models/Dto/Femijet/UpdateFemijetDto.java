package models.dto.Femijet;

public class UpdateFemijetDto {
    private int femijaID;
    private String emri;
    private String mbiemri;
    private String dataLindjes;
    private boolean gjinia;
    private String adresa;
    private String emriPrindit;
    private String kontaktiPrindit;

    public UpdateFemijetDto(int femijaID, String emri, String mbiemri, String dataLindjes, boolean gjinia, String adresa, String emriPrindit, String kontaktiPrindit) {
        this.femijaID = femijaID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.dataLindjes = dataLindjes;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.emriPrindit = emriPrindit;
        this.kontaktiPrindit = kontaktiPrindit;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public void setFemijaID(int femijaID) {
        this.femijaID = femijaID;
    }

    public String getEmri() {
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

    public String getDataLindjes() {
        return dataLindjes;
    }

    public void setDataLindjes(String dataLindjes) {
        this.dataLindjes = dataLindjes;
    }

    public boolean isGjinia() {
        return gjinia;
    }

    public void setGjinia(boolean gjinia) {
        this.gjinia = gjinia;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmriPrindit() {
        return emriPrindit;
    }

    public void setEmriPrindit(String emriPrindit) {
        this.emriPrindit = emriPrindit;
    }

    public String getKontaktiPrindit() {
        return kontaktiPrindit;
    }

    public void setKontaktiPrindit(String kontaktiPrindit) {
        this.kontaktiPrindit = kontaktiPrindit;
    }
}