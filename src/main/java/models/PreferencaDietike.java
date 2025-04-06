package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreferencaDietike {

    // pyetje private final int preferencaID;
    private int preferencaID;
    private int femijaID;
    private String llojiPreferences;
    private String detaje;

    private PreferencaDietike(int preferencaID, int femijaID, String llojiPreferences, String detaje) {
        this.preferencaID = preferencaID;
        this.femijaID = femijaID;
        this.llojiPreferences = llojiPreferences;
        this.detaje = detaje;
    }

    public static PreferencaDietike getInstance(ResultSet result) throws SQLException {
        int preferencaID = result.getInt("PreferencaID");
        int femijaID = result.getInt("FemijaID");
        String llojiPreferences = result.getString("LlojiPreferences");
        String detaje = result.getString("Detaje");
        return new PreferencaDietike(preferencaID, femijaID, llojiPreferences, detaje);
    }

    public int getPreferencaID() {
        return preferencaID;
    }

    public int getFemijaID() {
        return femijaID;
    }

    public String getLlojiPreferences() {
        return llojiPreferences;
    }

    public String getDetaje() {
        return detaje;
    }
}
