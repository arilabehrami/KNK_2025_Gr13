package Main;

import controllers.OrariController;
import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.OrariRepository;
import services.OrariService;

import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrariMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Merr ResourceBundle për gjuhën
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", Locale.ENGLISH);

        // Ngarko FXML me ResourceBundle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);
        loader.load();

        // Merr controllerin nga FXMLLoader
        OrariController controller = loader.getController();

        // Krijo lidhjen me DB
        Connection connection = DBConnection.getConnection();


        // Krijo dhe shfaq scenën
        stage.setScene(new Scene(loader.getRoot()));
        stage.setTitle(bundle.getString("orari.title.window"));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}