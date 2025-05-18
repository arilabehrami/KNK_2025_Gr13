package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Dto.Financat.CreateFinancatDto;
import models.domain.Financat;
import services.FinancaService;

import java.time.LocalDate;

public class FinancaController {

    @FXML
    private TextField txtId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtTeArdhura;

    @FXML
    private TextField txtShpenzime;

    @FXML
    private TextArea txtPershkrimi;

    @FXML
    private Button btnRuaj;

    @FXML
    private Button btnFshij;

    @FXML
    private Button btnKerko;

    private final FinancaService financatService;

    public FinancaController() {
        this.financatService = new FinancaService();
    }

    @FXML
    public void initialize() {
        btnRuaj.setOnAction(e -> handleRuaj());
        btnFshij.setOnAction(e -> handleFshij());
        btnKerko.setOnAction(e -> handleKerko());
    }

    private void handleRuaj() {
        try {
            int id = Integer.parseInt(txtId.getText());
            float teArdhura = Float.parseFloat(txtTeArdhura.getText());
            float shpenzime = Float.parseFloat(txtShpenzime.getText());
            String pershkrimi = txtPershkrimi.getText();
            LocalDate date = datePicker.getValue();

            if (pershkrimi == null || pershkrimi.trim().isEmpty()) {
                throw new Exception("Pershkrimi nuk mund te jete bosh.");
            }

            if (date == null) {
                throw new Exception("Ju lutem zgjidhni nje date.");
            }

            CreateFinancatDto dto = new CreateFinancatDto(
                    id,
                    date.toString(),
                    teArdhura,
                    shpenzime,
                    pershkrimi
            );

            financatService.create(dto);
            showSuccess("Financa u ruajt me sukses!");

        } catch (NumberFormatException ex) {
            showError("ID, Të ardhurat dhe Shpenzimet duhet te jene numra.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleFshij() {
        try {
            int id = Integer.parseInt(txtId.getText());
            financatService.delete(id);
            showSuccess("Financa u fshi me sukses!");
            clearFields();
        } catch (NumberFormatException ex) {
            showError("ID duhet te jete numër.");
        } catch (Exception ex) {
            showError("Nuk u arrit te fshihet financa: " + ex.getMessage());
        }
    }

    private void handleKerko() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Financat financa = financatService.getById(id);
            if (financa == null) {
                showError("Nuk u gjet asnje finance me kete ID.");
                return;
            }

            txtTeArdhura.setText(String.valueOf(financa.getTeArdhura()));
            txtShpenzime.setText(String.valueOf(financa.getShpenzime()));
            txtPershkrimi.setText(financa.getPershkrimi());
            datePicker.setValue(LocalDate.parse(financa.getDate()));
            showSuccess("Financa u gjet me sukses!");

        } catch (NumberFormatException ex) {
            showError("ID duhet te jete numer.");
        } catch (Exception ex) {
            showError("Gabim gjate kerkimit: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtId.clear();
        txtTeArdhura.clear();
        txtShpenzime.clear();
        txtPershkrimi.clear();
        datePicker.setValue(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
