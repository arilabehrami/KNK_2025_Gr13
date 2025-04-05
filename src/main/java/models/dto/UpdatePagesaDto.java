package models.dto;

public class UpdatePagesaDto {
    private int pagesaId;
    private int femijaId;
    private double shuma;
    private String data;
    private String pershkrimi;
    public UpdatePagesaDto(int pagesaId, int femijaId, double shuma, String data, String pershkrimi){
        this.pagesaId = pagesaId;
        this.femijaId = femijaId;
        this.shuma = shuma;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public int getPagesaId(){
        return pagesaId;
    }

    public void setFemijaId(int femijaId){
        this.femijaId = femijaId;
    }

    public int getFemijaId(){
        return femijaId;
    }

    public void setShuma(double shuma){
        this.shuma = shuma;
    }

    public double getShuma(){
        return shuma;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public void setPershkrimi(String pershkrimi){
        this.pershkrimi = pershkrimi;
    }

    public String getPershkrimi(){
        return pershkrimi;
    }
}
