package models.Dto.PreferencaDietike;

public class CreatePreferencaDietikeDto {


        private int femijaID;
        private String llojiPreferences;
        private String detaje;

        public CreatePreferencaDietikeDto(int femijaID, String llojiPreferences, String detaje) {
            this.femijaID = femijaID;
            this.llojiPreferences = llojiPreferences;
            this.detaje = detaje;
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
