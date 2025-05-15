package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main4 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SugjerimetView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Sugjerimet");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}