package models.Dto.FemijetPrinderit;

public class UpdateFemijetPrinderitDto {

        private int id;
        private int femijaID;
        private int prindiID;

        public UpdateFemijetPrinderitDto(int id, int femijaID, int prindiID) {
            this.id = id;
            this.femijaID = femijaID;
            this.prindiID = prindiID;
        }

        public int getId() {
            return id;
        }
        public int getFemijaID() {
            return femijaID;
        }
        public int getPrindiID() {
            return prindiID;
        }


}
