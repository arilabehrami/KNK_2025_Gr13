package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;
import services.UserSession;

import java.util.ResourceBundle;

public class SugjerimetMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicializim testues i UserSession
        UserSession.init(1, "admin");

        // Merr përkthimet nga gjuha e caktuar
        ResourceBundle bundle = LanguageManager.getBundle();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SugjerimetView.fxml"), bundle);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(bundle.getString("title.sugjerimet")); // nga fajlli i përkthimeve
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}