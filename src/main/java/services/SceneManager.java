package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utils.SceneLocator;

public class SceneManager {

    private static SceneManager instance;

    private Scene scene;

    private SceneManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneLocator.MAIN_VIEW));
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
