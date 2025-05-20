package models.Dto.Pagesa;

import java.sql.Date;
import java.time.LocalDate;

public class CreatePagesaDto {
    private int femijaId;
    private double shuma;
    private LocalDate data;
    private String pershkrimi;
    
    public CreatePagesaDto(int femijaId, double shuma, LocalDate data, String pershkrimi){
        this.femijaId = femijaId;
        this.shuma = shuma;
        this.data = data;
        this.pershkrimi = pershkrimi;
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

    public void setData(LocalDate data){
        this.data = data;
    }

    public LocalDate getData(){
        return data;
    }

    public void setPershkrimi(String pershkrimi){
        this.pershkrimi = pershkrimi;
    }

    public String getPershkrimi(){
        return pershkrimi;
    }
}