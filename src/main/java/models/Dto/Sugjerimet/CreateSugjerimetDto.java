package models.Dto.Sugjerimet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateSugjerimetDto {

    private int sugjerimiID;
    private String emriSugjeruesit;
    private String roli;
    private java.sql.Date data;
    private String pershkrimi;

    private CreateSugjerimetDto(int sugjerimiID, String emriSugjeruesit, String roli, java.sql.Date data, String pershkrimi) {
        this.sugjerimiID = sugjerimiID;
        this.emriSugjeruesit = emriSugjeruesit;
        this.roli = roli;
        this.data = data;
        this.pershkrimi = pershkrimi;
    }

    public static CreateSugjerimetDto getInstance(ResultSet result) throws SQLException {
        int sugjerimiID = result.getInt("SugjerimiID");
        String emriSugjeruesit = result.getString("EmriSugjeruesit");
        String roli = result.getString("Roli");
        java.sql.Date data = result.getDate("Data");
        String pershkrimi = result.getString("Pershkrimi");
        return new CreateSugjerimetDto(sugjerimiID, emriSugjeruesit, roli, data, pershkrimi);
    }

    // Getters
    public int getSugjerimiID() {
        return sugjerimiID;
    }

    public String getEmriSugjeruesit() {
        return emriSugjeruesit;
    }

    public String getRoli() {
        return roli;
    }

    public java.sql.Date getData() {
        return data;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }
}