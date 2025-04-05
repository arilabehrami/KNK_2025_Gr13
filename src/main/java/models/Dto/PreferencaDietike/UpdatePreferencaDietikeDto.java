package models.Dto.PreferencaDietike;

public class UpdatePreferencaDietikeDto {


        private int preferencaID;
        private int femijaID;
        private String llojiPreferences;
        private String detaje;

        public UpdatePreferencaDietikeDto(int preferencaID, int femijaID, String llojiPreferences, String detaje) {
            this.preferencaID = preferencaID;
            this.femijaID = femijaID;
            this.llojiPreferences = llojiPreferences;
            this.detaje = detaje;
        }

        public int getPreferencaID() {
            return preferencaID;
        }

        public int getFemijaID() {
            return femijaID;
        }

        public String getLlojiPreferences() {
            return llojiPreferences;
        }

        public String getDetaje() {
            return detaje;
        }


}
