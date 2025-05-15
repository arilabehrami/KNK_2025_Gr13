package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Sugjerimet.CreateSugjerimetDto;
import services.SugjerimetService;
import models.domain.Sugjerimet;

import java.sql.Date;

public class SugjerimetController {

    @FXML
    private TextField emriSugjeruesitField;

    @FXML
    private ComboBox<String> roliComboBox;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextArea pershkrimiField;

    @FXML
    private Label statusLabel;

    private final SugjerimetService sugjerimetService = new SugjerimetService();

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

    private void clearFields() {
        emriSugjeruesitField.clear();
        roliComboBox.getSelectionModel().clearSelection();
        dataPicker.setValue(null);
        pershkrimiField.clear();
    }
}