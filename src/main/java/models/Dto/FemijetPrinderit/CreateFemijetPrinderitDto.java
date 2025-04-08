package models.Dto.FemijetPrinderit;

public class CreateFemijetPrinderitDto {

        private int femijaID;
        private int prindiID;

        public CreateFemijetPrinderitDto(int femijaID, int prindiID) {
            this.femijaID = femijaID;
            this.prindiID = prindiID;
        }

        public int getFemijaID() {
            return femijaID;
        }
        public int getPrindiID() {
            return prindiID;
        }


}
