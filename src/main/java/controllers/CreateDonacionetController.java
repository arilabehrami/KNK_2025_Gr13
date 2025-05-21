package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Donacionet.CreateDonacionetDto;
import models.Dto.Donacionet.UpdateDonacionetDto;
import models.domain.Donacionet;
import services.DonacionetService;
import services.UserSession;


public class CreateDonacionetController {

    @FXML private TextField emriOrganizatesField;
    @FXML private ComboBox<String> llojiDonatoriCombo;
    @FXML private TextField kontaktiField;
    @FXML private TextField emailField;
    @FXML private TextField adresaField;
    @FXML private DatePicker dataDonacionitPicker;
    @FXML private TextField shumaField;
    @FXML private ComboBox<String> llojiDonacionitCombo;
    @FXML private TextArea pershkrimiArea;

    @FXML private TableView<Donacionet> tableDonacionet;
    @FXML private TableColumn<Donacionet, Integer> colID;
    @FXML private TableColumn<Donacionet, String> colEmriOrganizates;
    @FXML private TableColumn<Donacionet, String> colLlojiDonatori;
    @FXML private TableColumn<Donacionet, String> colKontakti;
    @FXML private TableColumn<Donacionet, String> colEmail;
    @FXML private TableColumn<Donacionet, String> colAdresa;
    @FXML private TableColumn<Donacionet, String> colDataDonacionit;
    @FXML private TableColumn<Donacionet, Double> colShuma;
    @FXML private TableColumn<Donacionet, String> colLlojiDonacionit;
    @FXML private TableColumn<Donacionet, String> colPershkrimi;

    private final DonacionetService service = new DonacionetService();
    private final ObservableList<Donacionet> donacionetList = FXCollections.observableArrayList();
    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML
    public void initialize() {
        llojiDonatoriCombo.getItems().addAll("Organizate", "Qeveri", "Individ", "Biznes", "Tjeter");
        llojiDonacionitCombo.getItems().addAll("Financiar", "Material", "Sherbim");

        colID.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getDonacioniID()).asObject());
        colEmriOrganizates.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmriOrganizates()));
        colLlojiDonatori.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getLlojiDonatori()));
        colKontakti.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getKontakti()));
        colEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        colAdresa.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAdresa()));
        colDataDonacionit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDataDonacionit()));
        colShuma.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getShuma()).asObject());
        colLlojiDonacionit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getLlojiDonacionit()));
        colPershkrimi.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getPershkrimi()));

        tableDonacionet.setItems(donacionetList);

        tableDonacionet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> populateFields(newSelection));

        loadDonacionet();
    }

    private void loadDonacionet() {
        donacionetList.clear();
        try {
            donacionetList.addAll(service.getAll());
        } catch (Exception e) {
            showError("Gabim gjatë ngarkimit të donacioneve: " + e.getMessage());
        }
    }

    private void populateFields(Donacionet donacioni) {
        if (donacioni != null) {
            emriOrganizatesField.setText(donacioni.getEmriOrganizates());
            llojiDonatoriCombo.setValue(donacioni.getLlojiDonatori());
            kontaktiField.setText(donacioni.getKontakti());
            emailField.setText(donacioni.getEmail());
            adresaField.setText(donacioni.getAdresa());
            if (donacioni.getDataDonacionit() != null && !donacioni.getDataDonacionit().isEmpty()) {
                dataDonacionitPicker.setValue(java.time.LocalDate.parse(donacioni.getDataDonacionit()));
            } else {
                dataDonacionitPicker.setValue(null);
            }
            shumaField.setText(String.valueOf(donacioni.getShuma()));
            llojiDonacionitCombo.setValue(donacioni.getLlojiDonacionit());
            pershkrimiArea.setText(donacioni.getPershkrimi());
        }
    }

    @FXML
    public void ruajDonacionin() {
        try {
            CreateDonacionetDto dto = new CreateDonacionetDto(
                    null,
                    emriOrganizatesField.getText(),
                    llojiDonatoriCombo.getValue(),
                    kontaktiField.getText(),
                    emailField.getText(),
                    adresaField.getText(),
                    dataDonacionitPicker.getValue() != null ? dataDonacionitPicker.getValue().toString() : null,
                    Double.parseDouble(shumaField.getText()),
                    llojiDonacionitCombo.getValue(),
                    pershkrimiArea.getText()
            );

            Donacionet donacioni = service.create(dto);
            donacionetList.add(donacioni);
            clearFields();
            showInfo("Donacioni u shtua me sukses!");
        } catch (Exception e) {
            showError("Gabim gjatë shtimit të donacionit: " + e.getMessage());
        }
    }

    @FXML
    public void perditesoDonacionin() {
        Donacionet selected = tableDonacionet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një donacion për përditësim.");
            return;
        }
        try {
            UpdateDonacionetDto dto = new UpdateDonacionetDto(
                    selected.getDonacioniID(),
                    emriOrganizatesField.getText(),
                    llojiDonatoriCombo.getValue(),
                    kontaktiField.getText(),
                    emailField.getText(),
                    adresaField.getText(),
                    dataDonacionitPicker.getValue() != null ? dataDonacionitPicker.getValue().toString() : null,
                    Double.parseDouble(shumaField.getText()),
                    llojiDonacionitCombo.getValue(),
                    pershkrimiArea.getText()
            );

            Donacionet updated = service.update(dto);
            int index = donacionetList.indexOf(selected);
            donacionetList.set(index, updated);
            tableDonacionet.refresh();
            clearFields();
            showInfo("Donacioni u përditësua me sukses!");
        } catch (Exception e) {
            showError("Gabim gjatë përditësimit të donacionit: " + e.getMessage());
        }
    }

    @FXML
    public void largoDonacionin() {
        Donacionet selected = tableDonacionet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një donacion për fshirje.");
            return;
        }
        try {
            service.delete(selected.getDonacioniID());
            donacionetList.remove(selected);
            clearFields();
            showInfo("Donacioni u fshi me sukses!");
        } catch (Exception e) {
            showError("Gabim gjatë fshirjes së donacionit: " + e.getMessage());
        }
    }

    @FXML
    public void anulo() {
        clearFields();
    }

    private void clearFields() {
        emriOrganizatesField.clear();
        llojiDonatoriCombo.setValue(null);
        kontaktiField.clear();
        emailField.clear();
        adresaField.clear();
        dataDonacionitPicker.setValue(null);
        shumaField.clear();
        llojiDonacionitCombo.setValue(null);
        pershkrimiArea.clear();
        tableDonacionet.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
