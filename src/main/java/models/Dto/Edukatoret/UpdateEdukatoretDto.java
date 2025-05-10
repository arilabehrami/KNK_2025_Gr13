package models.Dto.Edukatoret;

public class UpdateEdukatoretDto {


        private int edukatoriID;
        private String emri;
        private String mbiemri;
        private String kontakti;
        private String kualifikimet;

        public UpdateEdukatoretDto(int edukatoriID, String emri, String mbiemri, String kontakti, String kualifikimet) {
            this.edukatoriID = edukatoriID;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.kontakti = kontakti;
            this.kualifikimet = kualifikimet;
        }

        public int getEdukatoriID() {
            return edukatoriID;
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