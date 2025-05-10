package models.Dto.ShenimetShendetsore;

public class UpdateShenimetShendetsoreDto {
    private int shenimiId;
    private String pershkrimi;
    private String data;

    public UpdateShenimetShendetsoreDto(int shenimiId, String pershkrimi) {
        this.shenimiId = shenimiId;
        this.pershkrimi = pershkrimi;
    }

    public int getShenimiId() {
        return shenimiId;
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
}