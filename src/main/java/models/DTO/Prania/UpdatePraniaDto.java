package models.DTO.Prania;

public class UpdatePraniaDto {
    private int praniaId;
    private int femijaId;
    private String data;
    private String statusi;

    public UpdatePraniaDto(int praniaId, int femijaId, String data, String statusi) {
        this.praniaId = praniaId;
        this.femijaId = femijaId;
        this.data = data;
        this.statusi = statusi;
    }

    public int getPraniaId() {
        return praniaId;
    }

    public void setPraniaId(int praniaId) {
        this.praniaId = praniaId;
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
