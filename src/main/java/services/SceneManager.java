package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class SceneManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setMaximized(true);  // e vendos dritaren në maximized që në fillim
        // ose primaryStage.setFullScreen(true); nëse preferon full screen
    }

    public static void changeScene(String fxmlPath) {
        try {
            Locale locale = LanguageManager.getCurrentLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath), bundle);
            Parent root = loader.load();

            // Merr madhësinë ekzistuese për të ruajtur
            double width = primaryStage.getWidth();
            double height = primaryStage.getHeight();

            // Krijo scene me madhësinë ekzistuese të dritares
            Scene scene = new Scene(root, width, height);

            primaryStage.setScene(scene);

            // Nëse do, mund të vendosësh përsëri maximized ose fullscreen
            primaryStage.setMaximized(true);
            // ose primaryStage.setFullScreen(true);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
