package models.Dto.Femijet;

public class CreateFemijetDto {
    private int FemijaID;
    private String Emri;
    private String Mbiemri;
    private String DataLindjes;
    private String Gjinia;
    private String Adresa;
    private String EmriPrindit;
    private String KontaktiPrindit;

    public CreateFemijetDto(String emri, String mbiemri, String dataLindjes, String gjinia, String adresa, String emriPrindit, String kontaktiPrindit) {
        FemijaID = FemijaID;
        Emri = emri;
        Mbiemri = mbiemri;
        DataLindjes = dataLindjes;
        Gjinia = gjinia;
        Adresa = adresa;
        EmriPrindit = emriPrindit;
        KontaktiPrindit = kontaktiPrindit;
    }

    public int getFemijaID() {
        return FemijaID;
    }

    public void setFemijaID(int femijaID) {
        FemijaID = femijaID;
    }

    public String getEmri() {
        return Emri;
    }

    public void setEmri(String emri) {
        Emri = emri;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        Mbiemri = mbiemri;
    }

    public String getDataLindjes() {
        return DataLindjes;
    }

    public void setDataLindjes(String dataLindjes) {
        DataLindjes = dataLindjes;
    }

    public String isGjinia() {
        return Gjinia;
    }

    public void setGjinia(String gjinia) {
        Gjinia = gjinia;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getEmriPrindit() {
        return EmriPrindit;
    }

    public void setEmriPrindit(String emriPrindit) {
        EmriPrindit = emriPrindit;
    }

    public String getKontaktiPrindit() {
        return KontaktiPrindit;
    }

    public void setKontaktiPrindit(String kontaktiPrindit) {
        KontaktiPrindit = kontaktiPrindit;
    }
}