package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private DatePicker datePicker;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Label titleLabel;
    @FXML
    private AnchorPane contentPane;


    @FXML
    private void initialize() {
        System.out.println("Kontrolluesi u inicializua.");
    }

    @FXML

    private void handleButton1Click() {
        try {
            System.out.println("Klikimi i butonit Ushqimet po funksionon.");
            AnchorPane ushqimetView = FXMLLoader.load(getClass().getResource("/Views/UshqimetView.fxml"));
            contentPane.getChildren().setAll(ushqimetView);
        } catch (IOException e) {
            System.out.println("Gabim gjatë ngarkimit të UshqimetView.fxml");
            e.printStackTrace();
        }
    }




    @FXML
    private void handleButton2Click() {
        System.out.println("Button 2 u klikua");
    }

    @FXML
    private void handleButton3Click() {
        System.out.println("Button 3 u klikua");
    }

    @FXML
    private void handleCloseMenu() {
        System.out.println("Close u përzgjodh");
        System.exit(0);
    }

    @FXML
    private void handleDeleteMenu() {
        System.out.println("Delete u përzgjodh");
    }

    @FXML
    private void handleAboutMenu() {
        System.out.println("About u përzgjodh");
    }
}
