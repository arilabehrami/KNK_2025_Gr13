package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Financat.CreateFinancatDto;
import models.domain.Financat;
import models.domain.Pagesa;
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
    private TableView<Pagesa> tablePagesat;
    @FXML
    private TableView<Financat> tabelaFinancat;
    @FXML
    private TableColumn<Financat, Integer> colId;
    @FXML
    private TableColumn<Financat, String> colData;
    @FXML
    private TableColumn<Financat, Float> colTeArdhura;
    @FXML
    private TableColumn<Financat, Float> colShpenzime;
    @FXML
    private TableColumn<Financat, String> colPershkrimi;


    private FinancaService financatService;

    public FinancaController() {
        this.financatService = new FinancaService();
    }

    @FXML
    public void initialize() {
        btnRuaj.setOnAction(e -> handleRuaj());
        btnFshij.setOnAction(e -> handleFshij());
        btnKerko.setOnAction(e -> handleKerko());
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFinancatID()).asObject());
        colData.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate()));
        colTeArdhura.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty(data.getValue().getTeArdhura()).asObject());
        colShpenzime.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty(data.getValue().getShpenzime()).asObject());
        colPershkrimi.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPershkrimi()));

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

            if (financatList.isEmpty()) {
                showError("Nuk u gjetën të dhëna.");
                tabelaFinancat.getItems().clear();
            } else {
                tabelaFinancat.getItems().setAll(financatList);
            }


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
