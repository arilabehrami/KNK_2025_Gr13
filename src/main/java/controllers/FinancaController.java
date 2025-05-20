package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Dto.Financat.CreateFinancatDto;
import models.domain.Financat;
import services.FinancaService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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

    @FXML
    private TextArea txtRezultatet;

    private FinancaService financatService;

    public FinancaController() {
        this.financatService = new FinancaService();
    }

    @FXML
    public void initialize() {
        btnRuaj.setOnAction(e -> handleRuaj());
        btnFshij.setOnAction(e -> handleFshij());
        btnKerko.setOnAction(e -> handleKerko());

        // Nuk kemi tabela kështu që nuk bëjmë inicializim tabelash
    }

    private void handleRuaj() {
        try {
            String teArdhuraStr = txtTeArdhura.getText();
            String shpenzimeStr = txtShpenzime.getText();
            String pershkrimi = txtPershkrimi.getText();
            LocalDate data = datePicker.getValue();

            if (pershkrimi == null || pershkrimi.isEmpty()) {
                throw new Exception("Përshkrimi nuk mund të jetë bosh.");
            }

            if (data == null) {
                throw new Exception("Ju lutem zgjidhni një datë.");
            }

            float teArdhura = Float.parseFloat(teArdhuraStr);
            float shpenzime = Float.parseFloat(shpenzimeStr);

            CreateFinancatDto dto = new CreateFinancatDto(
                    data.toString(),
                    teArdhura,
                    shpenzime,
                    pershkrimi
            );

            Financat financa = financatService.create(dto);
            if (financa != null) {
                showSuccess("Financa u ruajt me sukses!");
                // Mund të shtosh ndonjë rifreskim të UI-së nëse dëshiron
            } else {
                showError("Nuk u krye ruajtja e financave.");
            }
        } catch (NumberFormatException ex) {
            showError("Të ardhurat dhe shpenzimet duhet të jenë numra.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleFshij() {
        try {
            String idStr = txtId.getText();
            int id = Integer.parseInt(idStr);
            financatService.delete(id);
            showSuccess("Financa u fshi me sukses.");
            // Mund të rifreskosh UI-në nëse ke nevojë
        } catch (NumberFormatException e) {
            showError("ID duhet të jetë numër.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleKerko() {
        try {
            String input = txtId.getText();
            List<Financat> financatList;

            if (input == null || input.isEmpty()) {
                financatList = financatService.getAllFinancat();
            } else {
                try {
                    int id = Integer.parseInt(input);
                    Financat financa = financatService.getById(id);
                    if (financa != null) {
                        financatList = Collections.singletonList(financa);
                    } else {
                        financatList = Collections.emptyList();
                    }
                } catch (NumberFormatException e) {
                    financatList = Collections.emptyList();
                }
            }

            // Përditëso TextArea-në me listën e financave në tekst
            StringBuilder sb = new StringBuilder();
            if (financatList.isEmpty()) {
                sb.append("Nuk u gjetën të dhëna.");
            } else {
                for (Financat f : financatList) {
                    sb.append(formatFinanca(f)).append("\n\n");
                }
            }
            txtRezultatet.setText(sb.toString());

        } catch (Exception ex) {
            showError("Gabim gjatë kërkimit: " + ex.getMessage());
        }
    }

    // Metodë ndihmëse për formatimin e një objekti Financat në tekst
    private String formatFinanca(Financat f) {
        return "ID: " + f.getFinancatID() +
                "\nData: " + f.getDate() +
                "\nTë Ardhura: " + f.getTeArdhura() +
                "\nShpenzime: " + f.getShpenzime() +
                "\nPërshkrimi: " + f.getPershkrimi();
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
