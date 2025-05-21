package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.LanguageManager;
import services.UserSession;

import java.util.ResourceBundle;

public class OrariMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        UserSession.init(1, "admin");

        ResourceBundle bundle = LanguageManager.getBundle();

        // Kjo linjë duhet ta gjej .fxml-in
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);
        Parent root = loader.load();

        stage.setTitle(bundle.getString("title.orari")); // nga messages.properties
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}