package models.Dto.MenyjaDitore;

public class UpdateMenyjaDitoreDto {


        private int menuID;
        private String dita;
        private int grupiID;
        private int ushqimiID;

        public UpdateMenyjaDitoreDto(int menuID, String dita, int grupiID, int ushqimiID) {
            this.menuID = menuID;
            this.dita = dita;
            this.grupiID = grupiID;
            this.ushqimiID = ushqimiID;
        }

        public int getMenuID() {
            return menuID;
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
