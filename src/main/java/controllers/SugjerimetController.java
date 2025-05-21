package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Sugjerimet.CreateSugjerimetDto;
import models.Dto.Sugjerimet.UpdateSugjerimetDto;
import models.domain.Sugjerimet;
import services.LanguageManager;
import services.SugjerimetService;

import java.sql.Date;
import java.time.LocalDate;

public class SugjerimetController extends BaseController {

    @FXML private TextField emriSugjeruesitField;
    @FXML private ComboBox<String> roliComboBox;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea pershkrimiField;

    @FXML private Button shtoButton, perditesoButton, fshijButton, pastroButton;
    @FXML private Label statusLabel;

    @FXML private TableView<Sugjerimet> sugjerimetTable;
    @FXML private TableColumn<Sugjerimet, Integer> colID;
    @FXML private TableColumn<Sugjerimet, String> colEmri, colRoli, colPershkrimi;
    @FXML private TableColumn<Sugjerimet, Date> colData;

    private final SugjerimetService sugjerimetService = new SugjerimetService();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("sugjerimiID"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emriSugjeruesit"));
        colRoli.setCellValueFactory(new PropertyValueFactory<>("roli"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));

        sugjerimetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                emriSugjeruesitField.setText(newSel.getEmriSugjeruesit());
                roliComboBox.setValue(newSel.getRoli());
                dataPicker.setValue(newSel.getData().toLocalDate());
                pershkrimiField.setText(newSel.getPershkrimi());
            }
        });

        refreshLanguage();
        onNgarkoSugjerimet();
    }

    @Override
    protected void refreshLanguage() {
        resources = LanguageManager.getBundle();

        emriSugjeruesitField.setPromptText(resources.getString("label.emrisugjeruesit"));
        roliComboBox.setPromptText(resources.getString("label.roli"));
        dataPicker.setPromptText(resources.getString("label.data"));
        pershkrimiField.setPromptText(resources.getString("label.pershkrimi"));

        colID.setText(resources.getString("table.id"));
        colEmri.setText(resources.getString("label.emrisugjeruesit"));
        colRoli.setText(resources.getString("label.roli"));
        colData.setText(resources.getString("label.data"));
        colPershkrimi.setText(resources.getString("label.pershkrimi"));

        shtoButton.setText(resources.getString("button.shto"));
        perditesoButton.setText(resources.getString("button.update_note"));
        fshijButton.setText(resources.getString("button.delete_note"));
        pastroButton.setText(resources.getString("button.clear"));

        roliComboBox.getItems().setAll(
                resources.getString("roli.prind"),
                resources.getString("roli.edukator"),
                resources.getString("roli.staf")
        );
    }

    @FXML
    private void onShtoSugjerim() {
        try {
            String emri = emriSugjeruesitField.getText();
            String roli = roliComboBox.getValue();
            LocalDate localDate = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (emri.isBlank() || roli == null || localDate == null || pershkrimi.isBlank()) {
                statusLabel.setText(resources.getString("message.ploteso_te_dhenat"));
                return;
            }

            CreateSugjerimetDto dto = new CreateSugjerimetDto(0, emri, roli, Date.valueOf(localDate), pershkrimi);
            sugjerimetService.create(dto);

            statusLabel.setText(resources.getString("message.shtuar_sukses"));
            clearFields();
            onNgarkoSugjerimet();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onPerditesoSugjerim() {
        Sugjerimet selected = sugjerimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        try {
            String emri = emriSugjeruesitField.getText();
            String roli = roliComboBox.getValue();
            LocalDate localDate = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            if (emri.isBlank() || roli == null || localDate == null || pershkrimi.isBlank()) {
                statusLabel.setText(resources.getString("message.ploteso_te_dhenat"));
                return;
            }

            UpdateSugjerimetDto dto = new UpdateSugjerimetDto(
                    selected.getSugjerimiID(), emri, roli, Date.valueOf(localDate), pershkrimi
            );

            sugjerimetService.update(dto);
            statusLabel.setText(resources.getString("message.perditesuar_sukses"));
            clearFields();
            onNgarkoSugjerimet();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onFshijSugjerim() {
        Sugjerimet selected = sugjerimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        try {
            boolean deleted = sugjerimetService.delete(selected.getSugjerimiID());
            if (deleted) {
                statusLabel.setText(resources.getString("message.fshire_sukses"));
                onNgarkoSugjerimet();
            } else {
                statusLabel.setText(resources.getString("message.fshirja_deshtoi"));
            }
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onPastroFushat() {
        clearFields();
        sugjerimetTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void onNgarkoSugjerimet() {
        sugjerimetTable.getItems().setAll(sugjerimetService.getAll());
    }

    private void clearFields() {
        emriSugjeruesitField.clear();
        roliComboBox.getSelectionModel().clearSelection();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }
}