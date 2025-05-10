package models.Dto.Prinderit;

public class UpdatePrinderitDto {


        private int prindiID;
        private String emri;
        private String mbiemri;
        private String telefoni;
        private String email;
        private String adresa;
        private String llojiLidhjes;

        public UpdatePrinderitDto(int prindiID, String emri, String mbiemri, String telefoni, String email, String adresa, String llojiLidhjes) {
            this.prindiID = prindiID;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.telefoni = telefoni;
            this.email = email;
            this.adresa = adresa;
            this.llojiLidhjes = llojiLidhjes;
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