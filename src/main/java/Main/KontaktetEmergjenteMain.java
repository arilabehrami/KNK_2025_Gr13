package Main;

import helpers.LanguageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;

import java.util.ResourceBundle;

public class KontaktetEmergjenteMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Inicializo gjuhën që është caktuar paraprakisht
        ResourceBundle bundle = LanguageManager.getBundle();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/KontaktetEmergjenteView.fxml"), bundle);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(bundle.getString("title.kontaktet")); // sigurohu që e ke në messages.properties
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}