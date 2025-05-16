package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class AktivitetetMain extends Application {

    private static Locale locale = new Locale("sq"); // ose "en"

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AktivitetetView.fxml"), bundle);
        Parent root = loader.load();

        primaryStage.setTitle("Menaxhimi i Aktivitetet");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
