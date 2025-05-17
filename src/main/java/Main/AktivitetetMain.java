package Main;

import Database.DBConnection;
import repository.AktivitetetRepository;
import services.AktivitetetService;
import controllers.AktivitetetController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class AktivitetetMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("en");

        // 1. Krijojmë lidhjen me DB dhe inicjalizojmë repository dhe service
        Connection conn = DBConnection.getConnection();
        AktivitetetRepository repo = new AktivitetetRepository(conn);
        AktivitetetService service = new AktivitetetService(repo);

        // 2. Ngarkojmë view-in (FXML) me resource bundle për gjuhë
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AktivitetetView.fxml"));
        loader.setResources(ResourceBundle.getBundle("languages.messages", locale));
        Parent root = loader.load();

        // 3. Merrim controller-in nga loader dhe i japim service dhe resource bundle
        AktivitetetController controller = loader.getController();
        controller.setService(service);
        controller.setResources(loader.getResources());

        // 4. Krijojmë skenën dhe e shfaqim
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(loader.getResources().getString("title.Aktiviteti"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
