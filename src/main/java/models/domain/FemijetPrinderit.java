package models.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FemijetPrinderit {

        private int id;
        private int femijaID;
        private int prindiID;

        private FemijetPrinderit(int id, int femijaID, int prindiID) {
            this.id = id;
            this.femijaID = femijaID;
            this.prindiID = prindiID;
        }

        public static FemijetPrinderit getInstance(ResultSet result) throws SQLException {
            int id = result.getInt("ID");
            int femijaID = result.getInt("FemijaID");
            int prindiID = result.getInt("PrindiID");
            return new FemijetPrinderit(id, femijaID, prindiID);
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
