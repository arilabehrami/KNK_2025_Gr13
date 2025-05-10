package models.Dto.Ushqimet;

public class UpdateUshqimetDto {


        private int ushqimiID;
        private String emriUshqimit;
        private String kategoria;
        private String pershkrimi;

        public UpdateUshqimetDto(int ushqimiID, String emriUshqimit, String kategoria, String pershkrimi) {
            this.ushqimiID = ushqimiID;
            this.emriUshqimit = emriUshqimit;
            this.kategoria = kategoria;
            this.pershkrimi = pershkrimi;
        }

    public int getUshqimiID() {

            return ushqimiID;
    }

    public String getEmriUshqimit() {

            return emriUshqimit;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getPershkrimi() {

            return pershkrimi;
    }


}