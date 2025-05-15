package Main;

import helpers.LanguageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class SugjerimetMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SugjerimetView.fxml"), bundle);

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle(bundle.getString("label.title1"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}