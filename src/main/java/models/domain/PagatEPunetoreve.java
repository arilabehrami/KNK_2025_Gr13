package models.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagatEPunetoreve {
    private int pagaID;
    private int edukatoriID;
    private String muaji;
    private int viti;
    private BigDecimal shumaPaga;
    private String dataEPageses;

    private PagatEPunetoreve(int pagaID, int edukatoriID, String muaji, int viti, BigDecimal shumaPaga, String dataEPageses) {
        this.pagaID = pagaID;
        this.edukatoriID = edukatoriID;
        this.muaji = muaji;
        this.viti = viti;
        this.shumaPaga = shumaPaga;
        this.dataEPageses = dataEPageses;
    }

    public static PagatEPunetoreve getInstance(ResultSet result) throws SQLException{
        int pagaId = result.getInt("PagaID");
        int edukatoriID = result.getInt("EdukatoriID");
        String muaji = result.getString("Muaji");
        int viti = result.getInt("Viti");
        BigDecimal shumaPaga = result.getBigDecimal("a");
        String dataEPageses = result.getString("DataEPageses");
        return new PagatEPunetoreve(pagaId, edukatoriID, muaji, viti, shumaPaga, dataEPageses);
    }

    public int getPagaID() {
        return pagaID;
    }

    public int getEdukatoriID() {
        return edukatoriID;
    }

    public String getMuaji() {
        return muaji;
    }

    public int getViti() {
        return viti;
    }

    public BigDecimal getShumaPaga() {
        return shumaPaga;
    }

    public String getDataEPageses() {
        return dataEPageses;
    }
}
