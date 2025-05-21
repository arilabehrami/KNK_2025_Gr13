package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.SceneLocator;

public class SceneManager {

    private static SceneManager sceneManager;
    private static final String DEFAULT_START_PAGE = SceneLocator.MAIN_VIEW;

    private Scene scene;
    private LanguageManager languageManager;
    private String currentPath;
    private Pane currentPane;

    private SceneManager() {
        this.languageManager = LanguageManager.getInstance();
        this.scene = initScene();
    }

    public static SceneManager getInstance() {
        if (sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    private Scene initScene() {
        try {
            return new Scene(getParent(DEFAULT_START_PAGE));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void load(String path) throws Exception {
        if (sceneManager == null)
            throw new Exception("Scene manager is not initialized yet!");
        sceneManager.loadParent(path);
    }

    public static void load(String path, Pane pane) throws Exception {
        if (sceneManager == null)
            throw new Exception("Scene manager is not initialized yet!");
        sceneManager.loadParent(path, pane);
    }

    private void loadParent(String path) throws Exception {
        Parent parent = getParent(path);
        this.currentPath = path;
        scene.setRoot(parent);
    }

    private void loadParent(String path, Pane pane) throws Exception {
        Parent parent = getParent(path);
        pane.getChildren().clear();
        pane.getChildren().add(parent);
        this.currentPath = path;
        this.currentPane = pane;
    }

    private Parent getParent(String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setResources(languageManager.getResourceBundle());
        return loader.load();
    }

    public static void reload() throws Exception {
        if (sceneManager.currentPane != null) {
            load(sceneManager.currentPath, sceneManager.currentPane);
        } else {
            load(sceneManager.currentPath);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
