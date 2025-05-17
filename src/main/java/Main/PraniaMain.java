package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class PraniaMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("sq"); 
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/PraniaView.fxml"), bundle);
        BorderPane root = loader.load();

        primaryStage.setTitle("Menaxhimi i Pranisë");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
