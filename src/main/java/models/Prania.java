package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prania {

    private int praniaID;
    private int femijaID;
    private Date data;
    private String statusi;

    private Prania(int praniaID, int femijaID, Date data, String statusi) {
        this.praniaID = praniaID;
        this.femijaID = femijaID;
        this.data = data;
        this.statusi = statusi;
    }

    public static Prania getInstance(ResultSet result) throws SQLException {
        int praniaID = result.getInt("PraniaID");
        int femijaID = result.getInt("FemijaID");
        Date data = result.getDate("Data");
        String statusi = result.getString("Statusi");
        return new Prania(praniaID, femijaID, data, statusi);
    }

    public int getPraniaID() {
        return praniaID;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public Date getData() {
        return data;
    }

    public String getStatusi() {
        return statusi;
    }
}
