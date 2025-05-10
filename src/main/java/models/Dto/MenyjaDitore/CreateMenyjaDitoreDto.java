package models.Dto.MenyjaDitore;

public class CreateMenyjaDitoreDto {


        private String dita;
        private int grupiID;
        private int ushqimiID;

        public CreateMenyjaDitoreDto(String dita, int grupiID, int ushqimiID) {
            this.dita = dita;
            this.grupiID = grupiID;
            this.ushqimiID = ushqimiID;
        }

        public String getDita() {
            return dita;
        }

        public int getGrupiID() {
            return grupiID;
        }

        public int getUshqimiID() {
            return ushqimiID;
        }


}