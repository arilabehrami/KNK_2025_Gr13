package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreferencaDietike {

    private int preferencaID;
    private int femijaID;
    private String llojiPreferencës;
    private String detaje;

    private PreferencaDietike(int preferencaID, int femijaID, String llojiPreferencës, String detaje) {
        this.preferencaID = preferencaID;
        this.femijaID = femijaID;
        this.llojiPreferencës = llojiPreferencës;
        this.detaje = detaje;
    }

    public static PreferencaDietike getInstance(ResultSet result) throws SQLException {
        int preferencaID = result.getInt("PreferencaID");
        int femijaID = result.getInt("FemijaID");
        String llojiPreferencës = result.getString("LlojiPreferencës");
        String detaje = result.getString("Detaje");
        return new PreferencaDietike(preferencaID, femijaID, llojiPreferencës, detaje);
    }

    public int getPreferencaID() {
        return preferencaID;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public String getLlojiPreferencës() {
        return llojiPreferencës;
    }

    public String getDetaje() {
        return detaje;
    }
}
