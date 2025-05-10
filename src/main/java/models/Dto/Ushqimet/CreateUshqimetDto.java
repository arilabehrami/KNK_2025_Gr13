package models.Dto.Ushqimet;

public class CreateUshqimetDto {

        private String emriUshqimit;
        private String kategoria;
        private String pershkrimi;

        public CreateUshqimetDto(String emriUshqimit, String kategoria, String pershkrimi) {
            this.emriUshqimit = emriUshqimit;
            this.kategoria = kategoria;
            this.pershkrimi = pershkrimi;
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