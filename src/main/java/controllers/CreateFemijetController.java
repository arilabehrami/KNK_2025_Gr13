package controllers;

import models.Dto.Femijet.CreateFemijetDto;
import models.Dto.Femijet.UpdateFemijetDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import models.domain.Femijet;
import services.FemijetService;
import services.UserSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateFemijetController {

    @FXML private TableView<Femijet> tableFemijet;
    @FXML private TableColumn<Femijet, Integer> colID;
    @FXML private TableColumn<Femijet, String> colEmri;
    @FXML private TableColumn<Femijet, String> colMbiemri;
    @FXML private TableColumn<Femijet, String> colDitelindja;
    @FXML private TableColumn<Femijet, String> colGjinia;
    @FXML private TableColumn<Femijet, String> colAdresa;
    @FXML private TableColumn<Femijet, String> colEmriPrindit;
    @FXML private TableColumn<Femijet, String> colKontaktiPrindit;

    @FXML private TextField txtEmri;
    @FXML private TextField txtMbiemri;
    @FXML private TextField txtDitelindja;
    @FXML private ComboBox<String> cmbGjinia;
    @FXML private TextField txtAdresa;
    @FXML private TextField txtEmriPrindit;
    @FXML private TextField txtKontaktiPrindit;

    @FXML private Label lblEmri;
    @FXML private Label lblMbiemri;
    @FXML private Label lblDitelindja;
    @FXML private Label lblGjinia;
    @FXML private Label lblAdresa;
    @FXML private Label lblEmriPrindit;
    @FXML private Label lblKontaktiPrindit;

    @FXML private Button btnShto;
    @FXML private Button btnPerditeso;
    @FXML private Button btnFshij;
    @FXML private Button btnPastro;

    @FXML private Button btnSwitchAlbanian;
    @FXML private Button btnSwitchEnglish;
    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();private final FemijetService femijetService = new FemijetService();
    private ResourceBundle resources;
    private int selectedFemijaId = -1;

    @FXML
    public void initialize() {
        resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));

        colID.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getFemijaID()).asObject());
        colEmri.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmri()));
        colMbiemri.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMbiemri()));
        colDitelindja.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDataLindjes()));
        colGjinia.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGjinia()));
        colAdresa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAdresa()));
        colEmriPrindit.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmriPrindit()));
        colKontaktiPrindit.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKontaktiPrindit()));

        cmbGjinia.setItems(FXCollections.observableArrayList("Mashkull", "Femër"));
        refreshTable();

        tableFemijet.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal));



    }

    private void populateFields(Femijet femija) {
        if (femija != null) {
            selectedFemijaId = femija.getFemijaID();
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
    private void handleShto(ActionEvent event) {
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
            femijetService.create(dto);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    @FXML
    private void handlePerditeso(ActionEvent event) {
        if (selectedFemijaId == -1) {
            showAlert(resources.getString("error.noSelection"));
            return;
        }
        try {
            UpdateFemijetDto dto = new UpdateFemijetDto(
                    selectedFemijaId,
                    txtEmri.getText(),
                    txtMbiemri.getText(),
                    txtDitelindja.getText(),
                    cmbGjinia.getValue(),
                    txtAdresa.getText(),
                    txtEmriPrindit.getText(),
                    txtKontaktiPrindit.getText()
            );
            femijetService.update(dto);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    @FXML
    private void handleFshij(ActionEvent event) {
        if (selectedFemijaId == -1) {
            showAlert(resources.getString("error.noSelection"));
            return;
        }
        try {
            femijetService.delete(selectedFemijaId);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    @FXML
    private void handlePastro(ActionEvent event) {
        clearFields();
    }

    private void refreshTable() {
        List<Femijet> femijet = femijetService.getAll();
        tableFemijet.setItems(FXCollections.observableArrayList(femijet));
    }

    private void clearFields() {
        selectedFemijaId = -1;
        txtEmri.clear();
        txtMbiemri.clear();
        txtDitelindja.clear();
        cmbGjinia.setValue(null);
        txtAdresa.clear();
        txtEmriPrindit.clear();
        txtKontaktiPrindit.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resources.getString("error.title"));
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String s) {
        System.err.println(s);
    }
}
