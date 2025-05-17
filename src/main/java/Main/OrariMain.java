package Main;

import controllers.OrariController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.OrariService;

import java.util.Locale;
import java.util.ResourceBundle;

public class OrariMain extends Application {

    private OrariService orariService = new OrariService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Vendosja e gjuhës fillestare - shqip
        Locale locale = new Locale("sq");
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OrariView.fxml"), bundle);
        Scene scene = new Scene(loader.load());

        OrariController controller = loader.getController();
        controller.setService(orariService);
        controller.setResources(bundle);

        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("orari.title.window"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
