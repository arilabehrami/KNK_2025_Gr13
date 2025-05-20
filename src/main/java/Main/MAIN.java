package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MAIN extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));
        Scene scene = new Scene(root,800,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menaxhimi i Grupeve");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
