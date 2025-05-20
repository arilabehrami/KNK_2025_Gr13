package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.domain.Pagesa;

public class FinancaMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Pagesa.class.getResource("/Views/FinancatView.fxml"));
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Krijimi i Pagesës");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
