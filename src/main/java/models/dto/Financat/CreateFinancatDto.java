package models.Dto.Financat;

public class CreateFinancatDto {
    private int financatID;
    private String date;
    private float teArdhura;
    private float shpenzime;
    private String pershkrimi;

    public CreateFinancatDto(int financatID, String date, float teArdhura, float shpenzime, String pershkrimi) {
        this.financatID = financatID;
        this.date = date;
        this.teArdhura = teArdhura;
        this.shpenzime = shpenzime;
        this.pershkrimi = pershkrimi;
    }

    public int getFinancatID() {
        return financatID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTeArdhura() {
        return teArdhura;
    }

    public void setTeArdhura(float teArdhura) {
        this.teArdhura = teArdhura;
    }

    public float getShpenzime() {
        return shpenzime;
    }

    public void setShpenzime(float shpenzime) {
        this.shpenzime = shpenzime;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}