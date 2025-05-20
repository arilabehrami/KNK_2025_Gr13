package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;
import services.ShenimetShendetesoreService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import services.FemijetService;

public class ShenimetShendetesoreController {

    @FXML private ComboBox<String> languageSelector;
    @FXML private Label languageLabel;

    @FXML private Label femijaIdLabel;
    @FXML private TextField femijaIdField;

    @FXML private Label dataLabel;
    @FXML private DatePicker dataPicker;

    @FXML private Label pershkrimiLabel;
    @FXML private TextArea pershkrimiField;

    @FXML private Button shtoButton;
    @FXML private Label statusLabel;

    private ResourceBundle bundle;
    private ShenimetShendetesoreService service;
    private FemijetService femijetService;

    @FXML
    public void initialize() {
        service = new ShenimetShendetesoreService();
        femijetService = new FemijetService();

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
    private void onLanguageChange(ActionEvent event) {
        String selectedLang = languageSelector.getValue();
        if ("English".equals(selectedLang)) {
            loadLanguage("en");
        } else {
            loadLanguage("sq");
        }
    }

    private void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("languages.messages", locale);

        // Update labels and buttons manually
        languageLabel.setText(bundle.getString("label.language"));
        femijaIdLabel.setText(bundle.getString("label.femijaid"));
        dataLabel.setText(bundle.getString("label.data"));
        pershkrimiLabel.setText(bundle.getString("label.pershkrimi"));
        shtoButton.setText(bundle.getString("button.shtoshenim"));
        statusLabel.setText("");
    }

    private void clearFields() {
        femijaIdField.clear();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }

    @FXML
    private void onShtoShenim() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());

            // Kontrollo nëse fusha e ID-së është e zbrazët
            if (femijaIdField.getText().isEmpty()) {
                showSimpleAlert("Ju lutem shkruani ID-në e fëmijës!");
                return;
            }

            // Kontrollo ekzistencën e fëmijës
            if (!femijetService.checkIfFemijaExists(femijaId)) {
                showSimpleAlert("Fëmija me ID " + femijaId + " nuk ekziston në sistem!");
                return;
            }

            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (date == null) {
                showSimpleAlert("Ju lutem zgjidhni një datë!");
                return;
            }

            if (pershkrimi.isEmpty()) {
                showSimpleAlert("Ju lutem shkruani një përshkrim!");
                return;
            }

            CreateShenimetShendetsoreDto dto = new CreateShenimetShendetsoreDto(
                    femijaId,
                    date.toString(),
                    pershkrimi
            );

            ShenimetShendetesore shenimi = service.create(dto);

            showSuccessAlert("Shënimi u krijua me sukses!\nID: " + shenimi.getShenimiID());
            clearFields();
        } catch (NumberFormatException e) {
            showSimpleAlert("ID e fëmijës duhet të jetë një numër!");
        } catch (Exception e) {
            showSimpleAlert("Gabim: " + e.getMessage());
        }
    }

    // Metodë për alert të thjeshtë gabimi
    private void showSimpleAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Metodë për alert suksesi
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}