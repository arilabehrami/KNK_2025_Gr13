package controllers;

import helpers.LanguageContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrariController {

    @FXML private MenuItem switchLanguageItem;
    @FXML private Menu fileMenu, editMenu, helpMenu, languageMenu;
    @FXML private TableColumn<?, ?> ditaColumn, oraHyrjesColumn, oraDaljesColumn, femijaIdColumn;
    @FXML private TableView<?> orariTable;
    @FXML private Button backButton;

    @FXML
    public void initialize() {
        // Optional: initialize table data or other components
    }

    @FXML
    private void onSwitchLanguage() {
        // Ndërron gjuhën dhe rifreskon skenën
        Locale newLocale = LanguageContext.currentLocale.getLanguage().equals("en") ? new Locale("sq") : new Locale("en");
        LanguageContext.currentLocale = newLocale;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", newLocale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = (Stage) switchLanguageItem.getParentPopup().getOwnerWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(bundle.getString("label.title2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}