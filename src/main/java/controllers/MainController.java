package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private Button ushqimetButton;

    @FXML
    private Button preferencatButton;

    @FXML
    private Button sugjerimetButton;

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
        System.out.println("MainController u inicializua.");
    }

    @FXML
    private void handleButton1Click() {
        loadView("/Views/UshqimetView.fxml", "Ushqimet");
    }

    @FXML
    private void handleButton2Click() {
        loadView("/Views/MenyjaDitoreView.fxml", "Menyja Ditore");
    }

    @FXML
    private void handleButton3Click() {
        loadView("/Views/PreferencatDietikeView.fxml", "Preferencat Dietike");
    }

    private void loadView(String viewPath, String title) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource(viewPath));
            contentPane.getChildren().setAll(view);
            titleLabel.setText(title);
        } catch (IOException e) {
            System.out.println("Gabim gjatë ngarkimit të pamjes: " + viewPath);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCloseMenu() {
        System.out.println("Mbyllja e aplikacionit...");
        System.exit(0);
    }

    @FXML
    private void handleDeleteMenu() {
        System.out.println("Fshirja u përzgjodh (logjika për fshirje mund të shtohet këtu).");
    }

    @FXML
    private void handleAboutMenu() {
        System.out.println("Rreth aplikacionit u përzgjodh.");
        // Mund të shtohet dritare modale me informacion
    }
}
