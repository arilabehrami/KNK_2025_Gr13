package models.Dto.Prania;

import java.time.LocalDate;

public class CreatePraniaDto {
    private int femijaId;
    private LocalDate data;
    private String statusi;

    public int getFemijaId() { return femijaId; }
    public void setFemijaId(int femijaId) { this.femijaId = femijaId; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getStatusi() { return statusi; }
    public void setStatusi(String statusi) { this.statusi = statusi; }
}
