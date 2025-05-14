package models.dto.PagatEPunetoreve;

public class UpdatePagatEPunetoreveDto {

    private int pagaID;
    private int edukatoriID;
    private String muaji;
    private int viti;
    private Double shumaPaga;
    private String dataEPageses;

    public UpdatePagatEPunetoreveDto(int pagaID, int edukatoriID, String muaji, int viti, Double shumaPaga, String dataEPageses) {
        this.pagaID = pagaID;
        this.edukatoriID = edukatoriID;
        this.muaji = muaji;
        this.viti = viti;
        this.shumaPaga = shumaPaga;
        this.dataEPageses = dataEPageses;
    }

    public int getPagaID() {
        return pagaID;
    }

    public int getEdukatoriID() {
        return edukatoriID;
    }

    public void setEdukatoriID(int edukatoriID) {
        this.edukatoriID = edukatoriID;
    }

    public String getMuaji() {
        return muaji;
    }

    public void setMuaji(String muaji) {
        this.muaji = muaji;
    }

    public int getViti() {
        return viti;
    }

    public void setViti(int viti) {
        this.viti = viti;
    }

    public Double getShumaPaga() {
        return shumaPaga;
    }

    public void setShumaPaga(Double shumaPaga) {
        this.shumaPaga = shumaPaga;
    }

    public String getDataEPageses() {
        return dataEPageses;
    }

    public void setDataEPageses(String dataEPageses) {
        this.dataEPageses = dataEPageses;
    }
}
