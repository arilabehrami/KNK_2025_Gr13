package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Grupet {
    private int grupiId;
    private String emriGrupit;
    private int moshaMin;
    private int moshaMax;
    private int edukatoriId;

    public Grupet(int edukatoriId, int moshaMax, int moshaMin, String emriGrupit, int grupiId) {
        this.edukatoriId = edukatoriId;
        this.moshaMax = moshaMax;
        this.moshaMin = moshaMin;
        this.emriGrupit = emriGrupit;
        this.grupiId = grupiId;
    }

    public static Grupet getInstance(ResultSet result) throws SQLException {
        int grupiId = result.getInt("GrupiId");
        String emriGrupit = result.getString("EmriGrupit");
        int moshaMin = result.getInt("MoshaMin");
        int moshaMax = result.getInt("MoshaMax");
        int edukatoriId = result.getInt("EdukatoriId");
        return new Grupet(edukatoriId,  moshaMin, moshaMax,emriGrupit, grupiId);
    }


    public int getGrupiId() {
        return grupiId;
    }

    public String getEmriGrupit() {
        return emriGrupit;
    }

    public int getMoshaMin() {
        return moshaMin;
    }

    public int getMoshaMax() {
        return moshaMax;
    }

    public int getEdukatoriId() {
        return edukatoriId;
    }
}
