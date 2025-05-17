package models.Dto.Prania;

import java.time.LocalDate;

public class UpdatePraniaDto {
    private int praniaId;
    private int femijaId;
    private LocalDate data;
    private String statusi;

    public int getPraniaId() { return praniaId; }
    public void setPraniaId(int praniaId) { this.praniaId = praniaId; }

    public int getFemijaId() { return femijaId; }
    public void setFemijaId(int femijaId) { this.femijaId = femijaId; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getStatusi() { return statusi; }
    public void setStatusi(String statusi) { this.statusi = statusi; }
}
