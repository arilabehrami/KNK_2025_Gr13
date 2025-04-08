package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Edukatoret {


        private int edukatoriID;
        private String emri;
        private String mbiemri;
        private String kontakti;
        private String kualifikimet;

        private Edukatoret(int edukatoriID, String emri, String mbiemri, String kontakti, String kualifikimet) {
            this.edukatoriID = edukatoriID;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.kontakti = kontakti;
            this.kualifikimet = kualifikimet;
        }

        public static Edukatoret getInstance(ResultSet result) throws SQLException {
            int edukatoriID = result.getInt("EdukatoriID");
            String emri = result.getString("Emri");
            String mbiemri = result.getString("Mbiemri");
            String kontakti = result.getString("Kontakti");
            String kualifikimet = result.getString("Kualifikimet");
            return new Edukatoret(edukatoriID, emri, mbiemri, kontakti, kualifikimet);
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
