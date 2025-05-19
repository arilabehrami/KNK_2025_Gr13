package Main;

import controllers.OrariController;
import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        // 1. Krijo lidhjen me DB
        Connection connection = DBConnection.getConnection();

        // 2. Krijo repo dhe service
        OrariRepository repo = new OrariRepository(connection);
        OrariService service = new OrariService(repo);
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", Locale.ENGLISH);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);

        Parent root = loader.load();
        OrariController controller = loader.getController();
        controller.setService(service);
        controller.setResources(bundle);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("orari.title.window"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
