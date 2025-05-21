package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import models.domain.Orari;
import services.OrariService;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class OrariController {

    @FXML
    private TableView<Orari> tableOrari;

    @FXML
    private TableColumn<Orari, Integer> colOrariID;

    @FXML
    private TableColumn<Orari, Integer> colFemijaID;

    @FXML
    private TableColumn<Orari, String> colDita;

    @FXML
    private TableColumn<Orari, String> colOraHyrjes;

    @FXML
    private TableColumn<Orari, String> colOraDaljes;

    @FXML
    private TextField tfFemijaID;

    @FXML
    private TextField tfDita;

    @FXML
    private TextField tfOraHyrjes;

    @FXML
    private TextField tfOraDaljes;

    @FXML
    private Button btnAdd, btnUpdate, btnDelete, btnClear;

    private final OrariService orariService = new OrariService();

    private ObservableList<Orari> orariList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Setup table columns
        colOrariID.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getOrariID()).asObject()
        );
        colFemijaID.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFemijaID()).asObject()
        );
        colDita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDita()));
        colOraHyrjes.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOraHyrjes().toString()));
        colOraDaljes.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOraDaljes().toString()));

        // Load data from DB
        loadOrariData();

        // Handle table row selection to populate form
        tableOrari.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateForm(newSelection);
            }
        });

        // Initially disable update and delete buttons (nothing selected)
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadOrariData() {
        List<Orari> allOrari = orariService.getAllOrari();
        orariList.setAll(allOrari);
        tableOrari.setItems(orariList);
    }

    private void populateForm(Orari orari) {
        tfFemijaID.setText(String.valueOf(orari.getFemijaID()));
        tfDita.setText(orari.getDita());
        tfOraHyrjes.setText(orari.getOraHyrjes().toString());
        tfOraDaljes.setText(orari.getOraDaljes().toString());

        // Enable update and delete buttons when a row is selected
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);

        // Disable add button to avoid duplicate inserts
        btnAdd.setDisable(true);
    }

    @FXML
    private void clearForm() {
        tfFemijaID.clear();
        tfDita.clear();
        tfOraHyrjes.clear();
        tfOraDaljes.clear();

        // Clear selection in table
        tableOrari.getSelectionModel().clearSelection();

        // Enable add button, disable update/delete
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void addOrari() {
        try {
            int femijaID = Integer.parseInt(tfFemijaID.getText().trim());
            String dita = tfDita.getText().trim();
            LocalTime oraHyrjes = LocalTime.parse(tfOraHyrjes.getText().trim());
            LocalTime oraDaljes = LocalTime.parse(tfOraDaljes.getText().trim());

            if (dita.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Dita nuk mund të jetë e zbrazët.");
                return;
            }

            CreateOrariDto dto = new CreateOrariDto(femijaID, dita, oraHyrjes.toString(), oraDaljes.toString());
            Orari created = orariService.addOrari(dto);
            if (created != null) {
                orariList.add(created);
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u shtua me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk mund të shtohet Orari.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Femija ID duhet të jetë numër i vlefshëm.");
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Format i gabuar për Ora Hyrjes ose Ora Daljes (duhet HH:mm:ss).");
        }
    }

    @FXML
    private void updateOrari() {
        Orari selected = tableOrari.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Paralajmërim", "Asnjë Orar i përzgjedhur për përditësim.");
            return;
        }

        try {
            int femijaID = Integer.parseInt(tfFemijaID.getText().trim());
            String dita = tfDita.getText().trim();
            LocalTime oraHyrjes = LocalTime.parse(tfOraHyrjes.getText().trim());
            LocalTime oraDaljes = LocalTime.parse(tfOraDaljes.getText().trim());

            if (dita.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Dita nuk mund të jetë e zbrazët.");
                return;
            }

            UpdateOrariDto dto = new UpdateOrariDto(selected.getOrariID(), femijaID, dita, oraHyrjes.toString(), oraDaljes.toString());
            Orari updated = orariService.updateOrari(dto);
            if (updated != null) {
                int idx = orariList.indexOf(selected);
                orariList.set(idx, updated);
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u përditësua me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk mund të përditësohet Orari.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Femija ID duhet të jetë numër i vlefshëm.");
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Format i gabuar për Ora Hyrjes ose Ora Daljes (duhet HH:mm:ss).");
        }
    }

    @FXML
    private void deleteOrari() {
        Orari selected = tableOrari.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Paralajmërim", "Asnjë Orar i përzgjedhur për fshirje.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmo fshirjen");
        confirm.setHeaderText(null);
        confirm.setContentText("A jeni të sigurt që dëshironi të fshini Orarin me ID: " + selected.getOrariID() + "?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = orariService.deleteOrari(selected.getOrariID());
            if (deleted) {
                orariList.remove(selected);
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u fshi me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk mund të fshihet Orari.");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
