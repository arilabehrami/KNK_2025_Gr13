package Main;

import helpers.LanguageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;
import services.UserSession;

import java.util.ResourceBundle;

public class ShenimetShendetesoreMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Inicializimi i sesionit (nëse përdoret në controller)
        UserSession.init(1, "admin");

        // Merr përkthimin aktual nga LanguageManager
        ResourceBundle bundle = LanguageManager.getBundle();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ShenimetShendetesoreView.fxml"), bundle);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle(bundle.getString("title.shenimet")); // Merr titullin nga fajlli i përkthimeve
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}