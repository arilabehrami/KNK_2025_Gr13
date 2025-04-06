package models.Dto.ShenimetShendetsore;

public class UpdateShenimetShendetsoreDto {
    private int shenimiId;
    private String pershkrimi;

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
}
