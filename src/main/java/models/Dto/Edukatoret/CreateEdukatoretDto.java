package models.Dto.Edukatoret;

public class CreateEdukatoretDto {

        private String emri;
        private String mbiemri;
        private String kontakti;
        private String kualifikimet;

        public CreateEdukatoretDto(String emri, String mbiemri, String kontakti, String kualifikimet) {
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.kontakti = kontakti;
            this.kualifikimet = kualifikimet;
        }

        public String getEmri() {
            return emri;
        }

        public String getMbiemri() {
            return mbiemri;
        }

        public String getKontakti() {
            return kontakti;
        }

        public String getKualifikimet() {
            return kualifikimet;
        }


}