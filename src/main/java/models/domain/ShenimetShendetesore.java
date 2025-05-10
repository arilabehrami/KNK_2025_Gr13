package models.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShenimetShendetesore {

    private int shenimiID;
    private int femijaID;
    private Date data;
    private String pershkrimi;

    private ShenimetShendetesore(int shenimiID, int femijaID, Date data, String pershkrimi) {
        this.shenimiID = shenimiID;
        this.femijaID = femijaID;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public static ShenimetShendetesore getInstance(ResultSet result) throws SQLException {
        int shenimiID = result.getInt("ShenimiID");
        int femijaID = result.getInt("FemijaID");
        Date data = result.getDate("Data");
        String pershkrimi = result.getString("Pershkrimi");
        return new ShenimetShendetesore(shenimiID, femijaID, data, pershkrimi);
    }

    public int getShenimiID() {
        return shenimiID;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public Date getData() {
        return data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}