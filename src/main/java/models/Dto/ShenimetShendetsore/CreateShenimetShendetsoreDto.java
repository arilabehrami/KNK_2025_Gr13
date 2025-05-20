package models.Dto.ShenimetShendetsore;

import java.sql.Date;

public class CreateShenimetShendetsoreDto {
    private int femijaId;
    private Date data;
    private String pershkrimi;

    public CreateShenimetShendetsoreDto(int femijaId, Date data, String pershkrimi) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }
}