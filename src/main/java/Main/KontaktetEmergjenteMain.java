package Main;

import helpers.LanguageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class KontaktetEmergjenteMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/KontaktetEmergjenteView.fxml"), bundle);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(bundle.getString("label.title"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}