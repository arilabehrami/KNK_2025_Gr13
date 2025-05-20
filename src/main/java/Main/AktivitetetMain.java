package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.domain.Pagesa;

import java.util.Locale;
import java.util.ResourceBundle;

public class AktivitetetMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.ENGLISH); // opsional, për testim mund të përdorësh Locale.forLanguageTag("sq") për shqip

        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", Locale.getDefault());

        FXMLLoader loader = new FXMLLoader(Pagesa.class.getResource("/Views/AktivitetetView.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Krijimi i Pagesës");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
