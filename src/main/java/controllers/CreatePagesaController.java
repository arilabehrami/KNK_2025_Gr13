package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Pagesa.CreatePagesaDto;
import models.domain.Pagesa;
import services.PagesaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreatePagesaController {

    @FXML
    private TextField txtFemijaId;      // ID e fëmijës (për krijim)
    @FXML
    private TextField txtPershkrimi;
    @FXML
    private TextField txtShuma;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button ruajButton;

    @FXML
    private TextField txtFemijaId1;     // ID për kërkim (ID pagesës)
    @FXML
    private Button kerkoButton;
    @FXML
    private TableView<Pagesa> tablePagesat;
    @FXML
    private TableColumn<Pagesa, Integer> colPagesaId;
    @FXML
    private TableColumn<Pagesa, Integer> colFemijaId;
    @FXML
    private TableColumn<Pagesa, String> colPershkrimi;
    @FXML
    private TableColumn<Pagesa, String> colData;
    @FXML
    private TableColumn<Pagesa, Double> colShuma;


    private PagesaService pagesaService;

    @FXML
    public void initialize() {
        colPagesaId.setCellValueFactory(new PropertyValueFactory<>("pagesaId"));
        colFemijaId.setCellValueFactory(new PropertyValueFactory<>("femijaId"));
        colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colShuma.setCellValueFactory(new PropertyValueFactory<>("shuma"));
    }


    public CreatePagesaController() {
        pagesaService = new PagesaService();
    }

    @FXML
    private void handleRuaj() {
        try {
            if(txtFemijaId.getText().isBlank() || txtShuma.getText().isBlank() || datePicker.getValue() == null) {
                showAlert(AlertType.ERROR, "Gabim", "Ju lutem plotësoni fushat ID e fëmijës, Shuma dhe Data.");
                return;
            }

            int femijaId = Integer.parseInt(txtFemijaId.getText().trim());
            double shuma = Double.parseDouble(txtShuma.getText().trim());
            LocalDate data = datePicker.getValue();
            String pershkrimi = txtPershkrimi.getText().trim();

            CreatePagesaDto dto = new CreatePagesaDto(femijaId, shuma, data, pershkrimi);
            Pagesa krijuar = pagesaService.create(dto);

            showAlert(AlertType.INFORMATION, "Sukses", "Pagesa u ruajt me sukses me ID: " + krijuar.getPagesaId());

            // Pas ruajtjes, fshij fusha
            clearFields();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Gabim", "ID dhe Shuma duhet të jenë numra validë.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Gabim", "Ndodhi një gabim gjatë ruajtjes së pagesës.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKerko() {
        String idText = txtFemijaId1.getText().trim();

        if(idText.isEmpty()) {
            List<Pagesa> teGjithaPagesat = pagesaService.getAll();
            if(teGjithaPagesat.isEmpty()) {
                showAlert(AlertType.INFORMATION, "Informacion", "Nuk ka pagesa në sistem.");
                tablePagesat.getItems().clear();
            } else {
                // Vendos listën në TableView
                ObservableList<Pagesa> listaObservable = FXCollections.observableArrayList(teGjithaPagesat);
                tablePagesat.setItems(listaObservable);
                showAlert(AlertType.INFORMATION, "Rezultat", "U gjetën " + teGjithaPagesat.size() + " pagesa.");
            }
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Pagesa pagesa = pagesaService.getById(id);
            if (pagesa == null) {
                showAlert(AlertType.WARNING, "Kërkim", "Pagesa me ID " + id + " nuk u gjet.");
                tablePagesat.getItems().clear();
            } else {
                // Vendos të dhënat e pagesës në fushat e tekstit
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

                // Vendos vetëm këtë pagesë në tabelë (ose pastron tabelën)
                tablePagesat.setItems(FXCollections.observableArrayList(pagesa));

                showAlert(AlertType.INFORMATION, "Kërkim", "Pagesa u gjet me sukses.");
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Gabim", "ID-ja duhet të jetë numër i saktë.");
            tablePagesat.getItems().clear();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Gabim", "Ndodhi një gabim gjatë kërkimit.");
            e.printStackTrace();
            tablePagesat.getItems().clear();
        }
    }


    private void clearFields() {
        txtFemijaId.clear();
        txtPershkrimi.clear();
        txtShuma.clear();
        datePicker.setValue(null);
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
