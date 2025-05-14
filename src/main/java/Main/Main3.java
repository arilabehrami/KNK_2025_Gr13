package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main3 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("sq"); // ose "en" për anglisht
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        primaryStage.setTitle("Kopshti i Fëmijëve");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
