package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.util.StringConverter;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;
import services.ShenimetShendetesoreService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShenimetShendetesoreController {

    @FXML
    private ComboBox<String> languageSelector;

    @FXML
    private TextField femijaIdField;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextArea pershkrimiField;

    @FXML
    private Label statusLabel;

    private ResourceBundle bundle;

    private ShenimetShendetesoreService service;

    @FXML
    public void initialize() {
        service = new ShenimetShendetesoreService();

        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue("Shqip"); // Default
        loadLanguage("sq");

        dataPicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? date.toString() : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string) : null;
            }
        });
    }

    @FXML
    private void onLanguageChange(InputEvent event) {
        String selectedLang = languageSelector.getValue();
        if (selectedLang.equals("English")) {
            loadLanguage("en");
        } else {
            loadLanguage("sq");
        }
    }

    private void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        ResourceBundle.getBundle("languages.messages", locale);
        // You can reload the UI elements manually here or reload the entire scene if dynamic refresh is complex
        languageSelector.setPromptText(bundle.getString("label.language"));
        statusLabel.setText("");
    }

    @FXML
    private void onShtoShenim() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (date == null) {
                throw new Exception(bundle.getString("error.date_required"));
            }

            CreateShenimetShendetsoreDto dto = new CreateShenimetShendetsoreDto(
                    femijaId,
                    date.toString(),
                    pershkrimi
            );

            ShenimetShendetesore shenimi = service.create(dto);

            statusLabel.setText(bundle.getString("success.created") + " ID: " + shenimi.getShenimiID());
            clearFields();
        } catch (NumberFormatException e) {
            statusLabel.setText(bundle.getString("error.invalid_id"));
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }

    private void clearFields() {
        femijaIdField.clear();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }
}