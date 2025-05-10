package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenyjaDitore {

    private int menuID;
    private String dita;
    private int grupiID;
    private int ushqimiID;

    private MenyjaDitore(int menuID, String dita, int grupiID, int ushqimiID) {
        this.menuID = menuID;
        this.dita = dita;
        this.grupiID = grupiID;
        this.ushqimiID = ushqimiID;
    }

    public static MenyjaDitore getInstance(ResultSet result) throws SQLException {
        int menuID = result.getInt("MenuID");
        String dita = result.getString("Dita");
        int grupiID = result.getInt("GrupiID");
        int ushqimiID = result.getInt("UshqimiID");
        return new MenyjaDitore(menuID, dita, grupiID, ushqimiID);
    }

    public int getMenuID() {
        return menuID;
    }

    public String getDita() {
        return dita;
    }

    public int getGrupiID() {
        return grupiID;
    }

    public int getUshqimiID() {
        return ushqimiID;
    }
}