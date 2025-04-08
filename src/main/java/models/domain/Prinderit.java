package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prinderit {


        private int prindiID;
        private String emri;
        private String mbiemri;
        private String telefoni;
        private String email;
        private String adresa;
        private String llojiLidhjes;

        private Prinderit(int prindiID, String emri, String mbiemri, String telefoni, String email, String adresa, String llojiLidhjes) {
            this.prindiID = prindiID;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.telefoni = telefoni;
            this.email = email;
            this.adresa = adresa;
            this.llojiLidhjes = llojiLidhjes;
        }

        public static Prinderit getInstance(ResultSet result) throws SQLException {
            int prindiID = result.getInt("PrindiID");
            String emri = result.getString("Emri");
            String mbiemri = result.getString("Mbiemri");
            String telefoni = result.getString("Telefoni");
            String email = result.getString("Email");
            String adresa = result.getString("Adresa");
            String llojiLidhjes = result.getString("LlojiLidhjes");
            return new Prinderit(prindiID, emri, mbiemri, telefoni, email, adresa, llojiLidhjes);
        }

        public int getPrindiID() {
            return prindiID;
        }

        public String getEmri() {
            return emri;
        }

        public String getMbiemri() {
            return mbiemri;
        }

        public String getTelefoni() {
            return telefoni;
        }

        public String getEmail() {
            return email;
        }

        public String getAdresa() {
            return adresa;
        }

        public String getLlojiLidhjes() {
            return llojiLidhjes;
        }


}
