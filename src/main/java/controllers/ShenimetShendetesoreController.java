package controllers;

import services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.domain.ShenimetShendetesore;
import services.FemijetService;
import services.ShenimetShendetesoreService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ShenimetShendetesoreController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
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
    @FXML private TableView<ShenimetShendetesore> shenimetTable;
    @FXML private TableColumn<ShenimetShendetesore, Integer> idColumn;
    @FXML private TableColumn<ShenimetShendetesore, Integer> femijaIdColumn;
    @FXML private TableColumn<ShenimetShendetesore, String> dataColumn;
    @FXML private TableColumn<ShenimetShendetesore, String> pershkrimiColumn;
    @FXML private Button ngarkoButton;
    @FXML private Button fshijButton;
    @FXML private Button perditesoButton; // Shtuar për butonin e përditësimit

    private ResourceBundle bundle;
    private ShenimetShendetesoreService service;
    private FemijetService femijetService;

    @FXML
    public void initialize() {
        service = new ShenimetShendetesoreService();
        femijetService = new FemijetService();

        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue("Shqip");
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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("shenimiID"));
        femijaIdColumn.setCellValueFactory(new PropertyValueFactory<>("femijaID"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        pershkrimiColumn.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
    }

    private void loadLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("languages.messages", locale);

        languageLabel.setText(bundle.getString("label.language"));
        femijaIdLabel.setText(bundle.getString("label.femijaid"));
        dataLabel.setText(bundle.getString("label.data"));
        pershkrimiLabel.setText(bundle.getString("label.pershkrimi"));
        shtoButton.setText(bundle.getString("button.shtoshenim"));
        statusLabel.setText("");

        idColumn.setText(bundle.getString("table.id"));
        femijaIdColumn.setText(bundle.getString("label.femijaid"));
        dataColumn.setText(bundle.getString("label.data"));
        pershkrimiColumn.setText(bundle.getString("label.pershkrimi"));

        ngarkoButton.setText(bundle.getString("button.load_all"));
        fshijButton.setText(bundle.getString("button.delete_note"));
        perditesoButton.setText(bundle.getString("button.update_note")); // Shtuar për butonin e përditësimit
    }

    @FXML
    private void onLanguageChange(ActionEvent event) {
        String selectedLang = languageSelector.getValue();
        loadLanguage(selectedLang.equals("English") ? "en" : "sq");
    }

    @FXML
    private void onShtoShenim() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());

            if (femijaIdField.getText().isEmpty()) {
                showSimpleAlert("error.empty_child_id");
                return;
            }

            if (!femijetService.checkIfFemijaExists(femijaId)) {
                showSimpleAlert("error.child_not_found");
                return;
            }

            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (date == null) {
                showSimpleAlert("error.date_required");
                return;
            }

            if (pershkrimi.isEmpty()) {
                showSimpleAlert("error.description_required");
                return;
            }

            CreateShenimetShendetsoreDto dto = new CreateShenimetShendetsoreDto(
                    femijaId,
                    Date.valueOf(date),
                    pershkrimi
            );

            ShenimetShendetesore shenimi = service.create(dto);
            showSuccessAlert("success.note_added");
            clearFields();
            onNgarkoShenimet();
        } catch (NumberFormatException e) {
            showSimpleAlert("error.invalid_id");
        } catch (Exception e) {
            showSimpleAlert("error.general");
        }
    }

    @FXML
    private void onNgarkoShenimet() {
        List<ShenimetShendetesore> lista = service.getAll();
        shenimetTable.getItems().setAll(lista);
    }

    @FXML
    private void onFshijShenim() {
        ShenimetShendetesore shenimi = shenimetTable.getSelectionModel().getSelectedItem();
        if (shenimi == null) {
            showSimpleAlert("warning.select_note");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle(bundle.getString("alert.confirm"));
        confirm.setHeaderText(null);
        confirm.setContentText(bundle.getString("confirm.delete"));

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean uFshi = service.delete(shenimi.getShenimiID());
            if (uFshi) {
                showSuccessAlert("success.deleted");
                onNgarkoShenimet();
            } else {
                showSimpleAlert("error.delete_failed");
            }
        }
    }

    private void clearFields() {
        femijaIdField.clear();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }

    private void showSimpleAlert(String messageKey) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("alert.error"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(messageKey));
        alert.showAndWait();
    }

    private void showSuccessAlert(String messageKey) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("alert.success"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(messageKey));
        alert.showAndWait();
    }

    @FXML
    private void onPerditesoShenim() {
        ShenimetShendetesore shenimi = shenimetTable.getSelectionModel().getSelectedItem();
        if (shenimi == null) {
            showSimpleAlert("warning.select_note");
            return;
        }

        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());

            if (!femijetService.checkIfFemijaExists(femijaId)) {
                showSimpleAlert("error.child_not_found");
                return;
            }

            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (date == null || pershkrimi.isEmpty()) {
                showSimpleAlert("error.missing_fields");
                return;
            }

            var dto = new models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto(
                    shenimi.getShenimiID(),
                    femijaId,
                    pershkrimi,
                    Date.valueOf(date)
            );

            service.update(dto);
            showSuccessAlert("success.note_updated");
            clearFields();
            onNgarkoShenimet();

        } catch (NumberFormatException e) {
            showSimpleAlert("error.invalid_id");
        } catch (Exception e) {
            e.printStackTrace();
            showSimpleAlert("error.general");
        }
    }
}