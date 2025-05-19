package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Edukatoret.CreateEdukatoretDto;
import models.Dto.Edukatoret.UpdateEdukatoretDto;
import models.domain.Edukatoret;
import services.EdukatoretService;

public class EdukatoretController {

    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtKontakti;
    @FXML private TextField txtKualifikimet;

    @FXML private TableView<Edukatoret> tableEdukatoret;
    @FXML private TableColumn<Edukatoret, Integer> colID;
    @FXML private TableColumn<Edukatoret, String> colEmri;
    @FXML private TableColumn<Edukatoret, String> colMbiemri;
    @FXML private TableColumn<Edukatoret, String> colKontakti;
    @FXML private TableColumn<Edukatoret, String> colKualifikimet;

    private final EdukatoretService service = new EdukatoretService();
    private final ObservableList<Edukatoret> edukatoretList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getEdukatoriID()).asObject());
        colEmri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmri()));
        colMbiemri.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getMbiemri()));
        colKontakti.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getKontakti()));
        colKualifikimet.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getKualifikimet()));

        tableEdukatoret.setItems(edukatoretList);
        tableEdukatoret.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal));

        loadEdukatoret();
    }

    private void loadEdukatoret() {
        edukatoretList.clear();
        try {
            edukatoretList.addAll(service.getAll());
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void populateFields(Edukatoret edukatori) {
        if (edukatori != null) {
            txtEmri.setText(edukatori.getEmri());
            txtMbiemri.setText(edukatori.getMbiemri());
            txtKontakti.setText(edukatori.getKontakti());
            txtKualifikimet.setText(edukatori.getKualifikimet());
        }
    }

    @FXML
    public void handleShto() {
        try {
            CreateEdukatoretDto dto = new CreateEdukatoretDto(
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtKontakti.getText(),
                    txtKualifikimet.getText()
            );
            Edukatoret edukatori = service.create(dto);
            edukatoretList.add(edukatori);
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handlePerditeso() {
        Edukatoret selected = tableEdukatoret.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një edukator për përditësim.");
            return;
        }

        try {
            UpdateEdukatoretDto dto = new UpdateEdukatoretDto(
                    selected.getEdukatoriID(),
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtKontakti.getText(),
                    txtKualifikimet.getText()
            );
            Edukatoret updated = service.update(dto);
            int index = edukatoretList.indexOf(selected);
            edukatoretList.set(index, updated);
            tableEdukatoret.refresh();
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleLargo() {
        Edukatoret selected = tableEdukatoret.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një edukator për fshirje.");
            return;
        }

        try {
            service.delete(selected.getEdukatoriID());
            edukatoretList.remove(selected);
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearFields() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtKontakti.clear();
        txtKualifikimet.clear();
        tableEdukatoret.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
