package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Pagesa.CreatePagesaDto;
import models.domain.Pagesa;
import services.PagesaService;
import services.UserSession;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class CreatePagesaController implements Initializable {

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

    private String username;
    private int userId;

    private ResourceBundle resources;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resources = resourceBundle;
        pagesaService = new PagesaService();

        username = UserSession.getInstance().getUsername();
        userId = UserSession.getInstance().getUserId();

        colPagesaId.setCellValueFactory(new PropertyValueFactory<>("pagesaId"));
        colFemijaId.setCellValueFactory(new PropertyValueFactory<>("femijaId"));
        colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colShuma.setCellValueFactory(new PropertyValueFactory<>("shuma"));

        tablePagesat.setItems(pagesaList);
        refreshTable();
    }

    @FXML
    private void handleRuaj() {
        if (txtFemijaId.getText().isBlank() || txtShuma.getText().isBlank() || datePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, get("gabim"), get("kompletoni.fushat"));
            return;
        }

        try {
            int femijaId = Integer.parseInt(txtFemijaId.getText().trim());
            double shuma = Double.parseDouble(txtShuma.getText().trim());
            LocalDate data = datePicker.getValue();
            String pershkrimi = txtPershkrimi.getText().trim();

            CreatePagesaDto dto = new CreatePagesaDto(femijaId, shuma, data, pershkrimi);
            Pagesa krijuar = pagesaService.create(dto);

            showAlert(Alert.AlertType.INFORMATION, get("sukses"), get("pagesa.ruajtur") + krijuar.getPagesaId());

            clearFields();
            refreshTable();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, get("gabim"), get("id.dhe.shuma.numra"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, get("gabim"), get("gabim.gjate.ruajtjes"));
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
                showAlert(Alert.AlertType.INFORMATION, get("informacion"), get("asnje.pagesa"));
            } else {
                showAlert(Alert.AlertType.INFORMATION, get("rezultat"), get("gjendur.pagesa") + teGjithaPagesat.size());
            }
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Pagesa pagesa = pagesaService.getById(id);

            if (pagesa == null) {
                showAlert(Alert.AlertType.WARNING, get("kerkesa"), get("nuk.u.gjet.pagesa") + id);
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
                showAlert(Alert.AlertType.INFORMATION, get("kerkesa"), get("pagesa.u.gjet"));
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, get("gabim"), get("id.duhet.numri"));
            pagesaList.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, get("gabim"), get("gabim.gjate.kerkeses"));
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

    private String get(String key) {
        return resources != null ? resources.getString(key) : key;
    }
}
