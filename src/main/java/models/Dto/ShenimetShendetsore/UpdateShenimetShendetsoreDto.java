package models.Dto.ShenimetShendetsore;

import java.sql.Date;

public class UpdateShenimetShendetsoreDto {
    private int shenimiID;
    private String pershkrimi;
    private Date data;
    private int femijaId;

    public UpdateShenimetShendetsoreDto(int shenimiId, int femijaId, String pershkrimi, Date data) {
        this.shenimiID = shenimiId;
        this.femijaId = femijaId;
        this.pershkrimi = pershkrimi;
        this.data = data;
    }

    public int getShenimiID() {
        return shenimiID;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getFemijaId() {
        return femijaId;
    }

    public void setFemijaId(int femijaId) {
        this.femijaId = femijaId;
    }
}