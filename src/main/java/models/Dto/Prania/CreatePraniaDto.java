package models.Dto.Prania;

public class CreatePraniaDto {
    private int femijaId;
    private String data;
    private String statusi;

    public CreatePraniaDto(int femijaId, String data, String statusi) {
        this.femijaId = femijaId;
        this.data = data;
        this.statusi = statusi;
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

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
