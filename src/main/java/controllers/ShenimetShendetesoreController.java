package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import models.domain.ShenimetShendetesore;
import models.Dto.ShenimetShendetsore.CreateShenimetShendetsoreDto;
import models.Dto.ShenimetShendetsore.UpdateShenimetShendetsoreDto;
import services.FemijetService;
import services.LanguageManager;
import services.ShenimetShendetesoreService;
import services.UserSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ShenimetShendetesoreController extends BaseController {

    @FXML private TextField femijaIdField;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea pershkrimiField;
    @FXML private Label statusLabel;

    @FXML private Button shtoButton, perditesoButton, fshijButton, pastroButton;

    @FXML private TableView<ShenimetShendetesore> shenimetTable;
    @FXML private TableColumn<ShenimetShendetesore, Integer> idColumn, femijaIdColumn;
    @FXML private TableColumn<ShenimetShendetesore, String> dataColumn, pershkrimiColumn;

    private ShenimetShendetesoreService service;
    private FemijetService femijetService;

    @FXML
    public void initialize() {
        service = new ShenimetShendetesoreService();
        femijetService = new FemijetService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("shenimiID"));
        femijaIdColumn.setCellValueFactory(new PropertyValueFactory<>("femijaID"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        pershkrimiColumn.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));

        dataPicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? date.toString() : "";
            }
            @Override
            public LocalDate fromString(String string) {
                return (string == null || string.isEmpty()) ? null : LocalDate.parse(string);
            }
        });

        shenimetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                femijaIdField.setText(String.valueOf(newSel.getFemijaID()));
                dataPicker.setValue(newSel.getData().toLocalDate());
                pershkrimiField.setText(newSel.getPershkrimi());
            }
        });

        ngarkoShenimet();
        refreshLanguage();
    }

    @Override
    protected void refreshLanguage() {
        resources = LanguageManager.getBundle();

        femijaIdField.setPromptText(resources.getString("label.femijaid"));
        dataPicker.setPromptText(resources.getString("label.data"));
        pershkrimiField.setPromptText(resources.getString("label.pershkrimi"));

        idColumn.setText(resources.getString("table.id"));
        femijaIdColumn.setText(resources.getString("label.femijaid"));
        dataColumn.setText(resources.getString("label.data"));
        pershkrimiColumn.setText(resources.getString("label.pershkrimi"));

        shtoButton.setText(resources.getString("button.shtoshenim"));
        perditesoButton.setText(resources.getString("button.update_note"));
        fshijButton.setText(resources.getString("button.delete_note"));
        pastroButton.setText(resources.getString("button.clear"));
    }

    @FXML
    private void onShtoShenim() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            if (!femijetService.checkIfFemijaExists(femijaId)) {
                statusLabel.setText(resources.getString("message.femija_nuk_ekziston"));
                return;
            }

            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            var dto = new CreateShenimetShendetsoreDto(femijaId, Date.valueOf(date), pershkrimi);
            service.create(dto);
            statusLabel.setText(resources.getString("message.shtuar_sukses"));
            clearFields();
            ngarkoShenimet();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onPerditesoShenim() {
        var selected = shenimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            LocalDate date = dataPicker.getValue();
            String pershkrimi = pershkrimiField.getText();

            var dto = new UpdateShenimetShendetsoreDto(selected.getShenimiID(), femijaId, pershkrimi, Date.valueOf(date));
            service.update(dto);
            statusLabel.setText(resources.getString("message.perditesuar_sukses"));
            clearFields();
            ngarkoShenimet();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onFshijShenim() {
        var selected = shenimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmo fshirjen");
        confirm.setHeaderText(null);
        confirm.setContentText(resources.getString("message.konfirmo_fshirjen"));
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = service.delete(selected.getShenimiID());
            if (deleted) {
                statusLabel.setText(resources.getString("message.fshire_sukses"));
                ngarkoShenimet();
            } else {
                statusLabel.setText(resources.getString("message.fshirja_deshtoi"));
            }
        }
    }

    @FXML
    private void onPastroFushat() {
        clearFields();
        shenimetTable.getSelectionModel().clearSelection();
    }

    private void clearFields() {
        femijaIdField.clear();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }

    private void ngarkoShenimet() {
        shenimetTable.getItems().setAll(service.getAll());
    }
}