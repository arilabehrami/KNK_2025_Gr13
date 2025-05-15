package controllers;

import helpers.LanguageContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;
import services.ShenimetShendetesoreService;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShenimetShendetesoreController {

    @FXML private ComboBox<String> languageSelector;
    @FXML private TextField femijaIdField;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea pershkrimiField;
    @FXML private Label statusLabel;

    private final ShenimetShendetesoreService service = new ShenimetShendetesoreService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue(LanguageContext.currentLocale.getLanguage().equals("en") ? "English" : "Shqip");
    }

    @FXML
    public void onShtoShenim() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            String data = dataPicker.getValue().toString();
            String pershkrimi = pershkrimiField.getText();

            var dto = new CreateShenimetShendetsoreDto(femijaId, data, pershkrimi);
            ShenimetShendetesore shenimi = service.create(dto);

            statusLabel.setText("✔ ID: " + shenimi.getShenimiID());
        } catch (Exception e) {
            statusLabel.setText("❌ " + e.getMessage());
        }
    }

    @FXML
    private void onLanguageChange() {
        String selected = languageSelector.getValue();
        Locale locale = selected.equals("English") ? new Locale("en") : new Locale("sq");
        LanguageContext.currentLocale = locale;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ShenimetShendetesoreView.fxml"), bundle);
            Parent root = loader.load();
            Stage stage = (Stage) languageSelector.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}