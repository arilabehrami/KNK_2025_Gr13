package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import services.FinancaService;
import models.Dto.Financat.CreateFinancatDto;
import models.domain.Financat;

import java.time.LocalDate;

public class FinancaController {

    @FXML
    private TextField txtTeArdhura;

    @FXML
    private TextField txtShpenzime;

    @FXML
    private TextArea txtPershkrimi;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button btnRuaj;

    @FXML
    private Button btnFshij;

    @FXML
    private Button btnKerko;

    @FXML
    private TextField txtId;

    private FinancaService financatService;

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
            // Lexojmë ID (nëse do përdoret)
            int financatID = 0;
            if (txtId != null && !txtId.getText().isEmpty()) {
                financatID = Integer.parseInt(txtId.getText());
            }

            // Kontrollojmë të dhënat tjera
            String teArdhuraStr = txtTeArdhura.getText();
            String shpenzimeStr = txtShpenzime.getText();
            String pershkrimi = txtPershkrimi.getText();
            LocalDate data = datePicker.getValue();

            if (pershkrimi == null || pershkrimi.isEmpty()) {
                throw new Exception("Pershkrimi nuk mund te jete bosh.");
            }

            if (data == null) {
                throw new Exception("Ju lutem zgjidhni nje date.");
            }

            float teArdhura = Float.parseFloat(teArdhuraStr);
            float shpenzime = Float.parseFloat(shpenzimeStr);

            CreateFinancatDto dto = new CreateFinancatDto(
                    financatID,
                    data.toString(),
                    teArdhura,
                    shpenzime,
                    pershkrimi
            );

            Financat financa = financatService.create(dto);
            if (financa != null) {
                showSuccess("Financa u ruajt me sukses!");
            } else {
                showError("Nuk u krye ruajtja e financave.");
            }
        } catch (NumberFormatException ex) {
            showError("Te ardhurat dhe shpenzimet duhet te jene numra.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleFshij() {
        try {
            int id = Integer.parseInt(txtId.getText());
            financatService.delete(id);
            showSuccess("Financa u fshi me sukses.");
        } catch (NumberFormatException e) {
            showError("ID duhet te jete numer.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleKerko() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Financat financa = financatService.getById(id);
            if (financa != null) {
                txtTeArdhura.setText(String.valueOf(financa.getTeArdhura()));
                txtShpenzime.setText(String.valueOf(financa.getShpenzime()));
                txtPershkrimi.setText(financa.getPershkrimi());
                datePicker.setValue(LocalDate.parse(financa.getDate()));
            } else {
                showError("Financa me kete ID nuk u gjet.");
            }
        } catch (NumberFormatException e) {
            showError("ID duhet te jete numer.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
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
