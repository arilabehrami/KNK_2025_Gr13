package models.dto.Grupet;

public class CreateGrupetDto {
    private int grupiId;
    private String emriGrupit;
    private int  moshaMin;
    private int moshaMax;
    private int edukatoriId;

    public CreateGrupetDto(int grupiId, String emriGrupit, int moshaMin, int moshaMax, int edukatoriId) {
        this.grupiId = grupiId;
        this.emriGrupit = emriGrupit;
        this.moshaMin = moshaMin;
        this.moshaMax = moshaMax;
        this.edukatoriId = edukatoriId;
    }

    public int getGrupiId() {
        return grupiId;
    }

    public void setGrupiId(int grupiId) {
        this.grupiId = grupiId;
    }

    public String getEmriGrupit() {
        return emriGrupit;
    }

    public void setEmriGrupit(String emriGrupit) {
        this.emriGrupit = emriGrupit;
    }

    public int getMoshaMin() {
        return moshaMin;
    }

    public void setMoshaMin(int moshaMin) {
        this.moshaMin = moshaMin;
    }

    public int getMoshaMax() {
        return moshaMax;
    }

    public void setMoshaMax(int moshaMax) {
        this.moshaMax = moshaMax;
    }

    public int getEdukatoriId() {
        return edukatoriId;
    }

    public void setEdukatoriId(int edukatoriId) {
        this.edukatoriId = edukatoriId;
    }
}