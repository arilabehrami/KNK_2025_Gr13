package models.Dto.ShenimetShendetsore;

public class CreateShenimetShendetsoreDto {
    private int femijaId;
    private String data;
    private String pershkrimi;

    public CreateShenimetShendetsoreDto(int femijaId, String data, String pershkrimi) {
        this.femijaId = femijaId;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public int getFemijaId() {
        return femijaId;
    }

    public void setFemijaId(int femijaId) {
        this.femijaId = femijaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}
