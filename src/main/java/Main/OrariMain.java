package Main;

import helpers.LanguageContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class OrariMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle(bundle.getString("label.title2")); // Shkruaje këtë në messages.properties si p.sh. label.title2=Orari
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}