package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Pagesa.CreatePagesaDto;
import models.domain.Pagesa;
import services.PagesaService;
import services.UserSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class CreatePagesaController {

    @FXML private TextField txtFemijaId;
    @FXML private TextField txtPershkrimi;
    @FXML private TextField txtShuma;
    @FXML private DatePicker datePicker;
    @FXML private Button ruajButton;

    @FXML private TextField txtFemijaId1;
    @FXML private Button kerkoButton;

    @FXML private TableView<Pagesa> tablePagesat;
    @FXML private TableColumn<Pagesa, Integer> colPagesaId;
    @FXML private TableColumn<Pagesa, Integer> colFemijaId;
    @FXML private TableColumn<Pagesa, String> colPershkrimi;
    @FXML private TableColumn<Pagesa, String> colData;
    @FXML private TableColumn<Pagesa, Double> colShuma;

    private PagesaService pagesaService;
    private ObservableList<Pagesa> pagesaList = FXCollections.observableArrayList();

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();

    @FXML private ResourceBundle resources;

    @FXML
    public void initialize() {
        pagesaService = new PagesaService();

        colPagesaId.setCellValueFactory(new PropertyValueFactory<>("pagesaId"));
        colFemijaId.setCellValueFactory(new PropertyValueFactory<>("femijaId"));
        colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colShuma.setCellValueFactory(new PropertyValueFactory<>("shuma"));

        tablePagesat.setItems(pagesaList);
    }

    @FXML
    private void handleRuaj() {
        if (txtFemijaId.getText().isBlank() || txtShuma.getText().isBlank() || datePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, resources.getString("gabim"), resources.getString("kompletoni.fushat"));
            return;
        }

        try {
            int femijaId = Integer.parseInt(txtFemijaId.getText().trim());
            double shuma = Double.parseDouble(txtShuma.getText().trim());
            LocalDate data = datePicker.getValue();
            String pershkrimi = txtPershkrimi.getText().trim();

            CreatePagesaDto dto = new CreatePagesaDto(femijaId, shuma, data, pershkrimi);
            Pagesa krijuar = pagesaService.create(dto);

            showAlert(Alert.AlertType.INFORMATION, resources.getString("sukses"), resources.getString("pagesa.ruajtur") + krijuar.getPagesaId());

            clearFields();
            refreshTable();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("gabim"), resources.getString("id.dhe.shuma.numra"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("gabim"), resources.getString("gabim.gjate.ruajtjes"));
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKerko() {
        String idText = txtFemijaId1.getText().trim();

        if (idText.isEmpty()) {
            List<Pagesa> teGjithaPagesat = pagesaService.getAll();
            pagesaList.setAll(teGjithaPagesat);

            if (teGjithaPagesat.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, resources.getString("informacion"), resources.getString("asnje.pagesa"));
            } else {
                showAlert(Alert.AlertType.INFORMATION, resources.getString("rezultat"), resources.getString("gjendur.pagesa") + teGjithaPagesat.size());
            }
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Pagesa pagesa = pagesaService.getById(id);

            if (pagesa == null) {
                showAlert(Alert.AlertType.WARNING, resources.getString("kerkesa"), resources.getString("nuk.u.gjet.pagesa") + id);
                pagesaList.clear();
            } else {
                txtFemijaId.setText(String.valueOf(pagesa.getFemijaId()));
                txtPershkrimi.setText(pagesa.getPershkrimi());
                txtShuma.setText(String.valueOf(pagesa.getShuma()));

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(pagesa.getData(), formatter);
                    datePicker.setValue(localDate);
                } catch (DateTimeParseException e) {
                    datePicker.setValue(null);
                }

                pagesaList.setAll(pagesa);
                showAlert(Alert.AlertType.INFORMATION, resources.getString("kerkesa"), resources.getString("pagesa.u.gjet"));
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("gabim"), resources.getString("id.duhet.numri"));
            pagesaList.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("gabim"), resources.getString("gabim.gjate.kerkeses"));
            e.printStackTrace();
            pagesaList.clear();
        }
    }

    private void refreshTable() {
        List<Pagesa> lista = pagesaService.getAll();
        pagesaList.setAll(lista);
    }

    private void clearFields() {
        txtFemijaId.clear();
        txtPershkrimi.clear();
        txtShuma.clear();
        datePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
