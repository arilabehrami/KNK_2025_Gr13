package controllers;

import services.UserSession;
import helpers.LanguageContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.Sugjerimet.CreateSugjerimetDto;
import models.Dto.Sugjerimet.UpdateSugjerimetDto;
import models.domain.Sugjerimet;
import services.SugjerimetService;

import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class SugjerimetController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML private ComboBox<String> languageSelector;
    @FXML private TextField emriSugjeruesitField;
    @FXML private ComboBox<String> roliComboBox;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea pershkrimiField;
    @FXML private Label statusLabel;
    @FXML private TableView<Sugjerimet> sugjerimetTable;
    @FXML private TableColumn<Sugjerimet, Number> colID;
    @FXML private TableColumn<Sugjerimet, String> colEmri;
    @FXML private TableColumn<Sugjerimet, String> colRoli;
    @FXML private TableColumn<Sugjerimet, Date> colData;
    @FXML private TableColumn<Sugjerimet, String> colPershkrimi;

    private final SugjerimetService sugjerimetService = new SugjerimetService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue(LanguageContext.currentLocale.getLanguage().equals("en") ? "English" : "Shqip");
        roliComboBox.getItems().clear();
        roliComboBox.getItems().addAll(
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.prind"),
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.edukator"),
                ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("roli.staf")
        );
        colID.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getSugjerimiID()));
        colEmri.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmriSugjeruesit()));
        colRoli.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoli()));
        colData.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getData()));
        colPershkrimi.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPershkrimi()));
        sugjerimetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                emriSugjeruesitField.setText(newSel.getEmriSugjeruesit());
                roliComboBox.setValue(newSel.getRoli());
                dataPicker.setValue(newSel.getData().toLocalDate());
                pershkrimiField.setText(newSel.getPershkrimi());
            }
        });
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

    @FXML
    private void onNgarkoSugjerimet() {
        try {
            sugjerimetTable.getItems().setAll(sugjerimetService.getAll());
            statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("success.loaded"));
        } catch (Exception e) {
            statusLabel.setText("Gabim gjatë ngarkimit.");
        }
    }

    @FXML
    private void onFshijSugjerim() {
        Sugjerimet iZgjedhur = sugjerimetTable.getSelectionModel().getSelectedItem();
        if (iZgjedhur == null) {
            statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("warning.select_note"));
            return;
        }

        try {
            boolean sukses = sugjerimetService.delete(iZgjedhur.getSugjerimiID());
            if (sukses) {
                onNgarkoSugjerimet();
                statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("success.deleted"));
            }
        } catch (Exception e) {
            statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("error.delete_failed"));
        }
    }

    @FXML
    private void onPastroFushatETabelen() {
        clearFields();
        sugjerimetTable.getSelectionModel().clearSelection();
        sugjerimetTable.getItems().clear();
    }

    @FXML
    private void onPerditesoSugjerim() {
        Sugjerimet iZgjedhur = sugjerimetTable.getSelectionModel().getSelectedItem();
        if (iZgjedhur == null) {
            statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("warning.select_note"));
            return;
        }

        try {
            String emri = emriSugjeruesitField.getText();
            String roli = roliComboBox.getValue();
            Date data = Date.valueOf(dataPicker.getValue());
            String pershkrimi = pershkrimiField.getText();

            UpdateSugjerimetDto dto = new UpdateSugjerimetDto(
                    iZgjedhur.getSugjerimiID(),
                    emri,
                    roli,
                    data.toString(),
                    pershkrimi
            );

            sugjerimetService.update(dto);
            onNgarkoSugjerimet();
            statusLabel.setText(ResourceBundle.getBundle("languages.messages", LanguageContext.currentLocale).getString("success.note_updated"));

        } catch (Exception e) {
            statusLabel.setText("Gabim: " + e.getMessage());
        }
    }
}