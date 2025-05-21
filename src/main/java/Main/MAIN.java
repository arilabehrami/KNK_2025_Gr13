package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.SceneManager;
import utils.SceneLocator;

public class MAIN extends Application {

    @Override

    public void start(Stage primaryStage) throws Exception {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.changeScene(SceneLocator.LOGIN_VIEW);

        //primaryStage.setFullScreen(true);  // kjo e bën full screen
        // ose në vend të fullscreen, mund të përdorësh
        primaryStage.setMaximized(true);  // e zmadhon në maksimum por jo fullscreen
    }



    public static void main(String[] args) {
        launch(args);
    }
}
