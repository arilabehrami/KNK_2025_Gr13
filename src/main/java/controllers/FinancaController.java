package controllers;

import services.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Dto.Financat.CreateFinancatDto;
import models.domain.Financat;
import models.domain.Pagesa;
import services.FinancaService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class FinancaController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();

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

    @FXML
    private Label lblInfo;

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

        tabelaFinancat.setRowFactory(tv -> {
            TableRow<Financat> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Financat financa = row.getItem();
                    populateFields(financa);
                    if (lblInfo != null) {
                        lblInfo.setText(formatFinanca(financa));
                    }
                }
            });
            return row;
        });

        tabelaFinancat.getItems().setAll(financatService.getAllFinancat());
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
                tabelaFinancat.getItems().setAll(financatService.getAllFinancat());
                clearFields();
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
            tabelaFinancat.getItems().setAll(financatService.getAllFinancat());
            clearFields();
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
            showError("Gabim gjatë kërkimt: " + ex.getMessage());
        }
    }

    private void populateFields(Financat financa) {
        txtId.setText(String.valueOf(financa.getFinancatID()));
        txtTeArdhura.setText(String.valueOf(financa.getTeArdhura()));
        txtShpenzime.setText(String.valueOf(financa.getShpenzime()));
        txtPershkrimi.setText(financa.getPershkrimi());
        datePicker.setValue(LocalDate.parse(financa.getDate()));

        if (lblInfo != null) {
            lblInfo.setText(formatFinanca(financa));
        }
    }

    private void clearFields() {
        txtId.clear();
        txtTeArdhura.clear();
        txtShpenzime.clear();
        txtPershkrimi.clear();
        datePicker.setValue(null);
        if (lblInfo != null) lblInfo.setText("");
    }

    private String formatFinanca(Financat f) {
        return ":: ID: " + f.getFinancatID() +
                "\n:: Data: " + f.getDate() +
                "\n:: Të Ardhura: " + f.getTeArdhura() +
                "\n:: Shpenzime: " + f.getShpenzime() +
                "\n:: Përshkrimi: " + f.getPershkrimi();
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
