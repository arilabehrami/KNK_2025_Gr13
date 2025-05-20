package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Femijet.CreateFemijetDto;
import models.Dto.Femijet.UpdateFemijetDto;
import models.domain.Femijet;
import services.FemijetService;

public class CreateFemijetController {

    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtDitelindja;
    @FXML private ComboBox<String> cmbGjinia;
    @FXML private TextField txtAdresa;
    @FXML private TextField txtEmriPrindit;
    @FXML private TextField txtKontaktiPrindit;

    @FXML private TableView<Femijet> tableFemijet;
    @FXML private TableColumn<Femijet, Integer> colID;
    @FXML private TableColumn<Femijet, String> colEmri;
    @FXML private TableColumn<Femijet, String> colMbiemri;
    @FXML private TableColumn<Femijet, String> colDitelindja;
    @FXML private TableColumn<Femijet, String> colGjinia;
    @FXML private TableColumn<Femijet, String> colAdresa;
    @FXML private TableColumn<Femijet, String> colEmriPrindit;
    @FXML private TableColumn<Femijet, String> colKontaktiPrindit;

    private final FemijetService service = new FemijetService();
    private final ObservableList<Femijet> femijetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getFemijaID()).asObject());
        colEmri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmri()));
        colMbiemri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getMbiemri()));
        colDitelindja.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDataLindjes()));
        colGjinia.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getGjinia()));
        colAdresa.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAdresa()));
        colEmriPrindit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmriPrindit()));
        colKontaktiPrindit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getKontaktiPrindit()));

        cmbGjinia.setItems(FXCollections.observableArrayList("Mashkull", "Femër"));
        tableFemijet.setItems(femijetList);
        tableFemijet.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal));

        loadFemijet();
    }

    private void loadFemijet() {
        femijetList.clear();
        try {
            femijetList.addAll(service.getAll());
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void populateFields(Femijet femija) {
        if (femija != null) {
            txtEmri.setText(femija.getEmri());
            txtMbiemri.setText(femija.getMbiemri());
            txtDitelindja.setText(femija.getDataLindjes());
            cmbGjinia.setValue(femija.getGjinia());
            txtAdresa.setText(femija.getAdresa());
            txtEmriPrindit.setText(femija.getEmriPrindit());
            txtKontaktiPrindit.setText(femija.getKontaktiPrindit());
        }
    }

    @FXML
    public void handleShto() {
        try {
            CreateFemijetDto dto = new CreateFemijetDto(
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtDitelindja.getText(),
                    cmbGjinia.getValue(),
                    txtAdresa.getText(),
                    txtEmriPrindit.getText(),
                    txtKontaktiPrindit.getText()
            );
            Femijet femija = service.create(dto);
            femijetList.add(femija);
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handlePerditeso() {
        Femijet selected = tableFemijet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një fëmijë për përditësim.");
            return;
        }

        try {
            UpdateFemijetDto dto = new UpdateFemijetDto(
                    selected.getFemijaID(),
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtDitelindja.getText(),
                    cmbGjinia.getValue(),
                    txtAdresa.getText(),
                    txtEmriPrindit.getText(),
                    txtKontaktiPrindit.getText()
            );
            Femijet updated = service.update(dto);
            int index = femijetList.indexOf(selected);
            femijetList.set(index, updated);
            tableFemijet.refresh();
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleLargo() {
        Femijet selected = tableFemijet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një fëmijë për fshirje.");
            return;
        }

        try {
            service.delete(selected.getFemijaID());
            femijetList.remove(selected);
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearFields() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtDitelindja.clear();
        cmbGjinia.setValue(null);
        txtAdresa.clear();
        txtEmriPrindit.clear();
        txtKontaktiPrindit.clear();
        tableFemijet.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
