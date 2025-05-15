package controllers;

import helpers.LanguageContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.Sugjerimet.CreateSugjerimetDto;
import models.domain.Sugjerimet;
import services.SugjerimetService;

import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class SugjerimetController {

    @FXML private ComboBox<String> languageSelector;
    @FXML private TextField emriSugjeruesitField;
    @FXML private ComboBox<String> roliComboBox;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea pershkrimiField;
    @FXML private Label statusLabel;

    private final SugjerimetService sugjerimetService = new SugjerimetService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue(LanguageContext.currentLocale.getLanguage().equals("en") ? "English" : "Shqip");

        // Setup roliComboBox me vlerat per translation
        roliComboBox.getItems().clear();
        roliComboBox.getItems().addAll(
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.prind"),
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.edukator"),
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.staf")
        );
    }

    @FXML
    public void onShtoSugjerim() {
        try {
            String emri = emriSugjeruesitField.getText();
            String roli = roliComboBox.getValue();
            Date data = Date.valueOf(dataPicker.getValue());
            String pershkrimi = pershkrimiField.getText();

            CreateSugjerimetDto dto = new CreateSugjerimetDto(0, emri, roli, data, pershkrimi);
            Sugjerimet sugjerimi = sugjerimetService.create(dto);

            statusLabel.setText("Sugjerimi u shtua me sukses (ID: " + sugjerimi.getSugjerimiID() + ")");
            clearFields();
        } catch (Exception e) {
            statusLabel.setText("Gabim: " + e.getMessage());
        }
    }

    @FXML
    private void clearFields() {
        emriSugjeruesitField.clear();
        roliComboBox.getSelectionModel().clearSelection();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }

    @FXML
    private void onLanguageChange() {
        String selected = languageSelector.getValue();
        Locale locale = selected.equals("English") ? new Locale("en") : new Locale("sq");
        LanguageContext.currentLocale = locale;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SugjerimetView.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = (Stage) languageSelector.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(bundle.getString("label.title1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}