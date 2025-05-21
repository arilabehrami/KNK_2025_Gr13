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

//    @FXML
//    public void switchToAlbanian() {
//        try {
//            resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));
//
//            updatePrompts();
//        } catch (Exception e) {
//            showError("Gabim në ndërrimin e gjuhës: " + e.getMessage());
//        }
//    }

//    @FXML
//    public void switchToEnglish() {
//        try {
//            resources = ResourceBundle.getBundle("languages.messages", Locale.ENGLISH);
//            updateUILabels();
//            updatePrompts();
//        } catch (Exception e) {
//            showError("Error switching language: " + e.getMessage());
//        }
//    }

//    private void updateUILabels() {
//        if (resources == null) return;
//
//        colID.setText(resources.getString("femijet.id"));
//        colEmri.setText(resources.getString("femijet.emri"));
//        colMbiemri.setText(resources.getString("femijet.mbiemri"));
//        colDitelindja.setText(resources.getString("femijet.ditelindja"));
//        colGjinia.setText(resources.getString("femijet.gjinia"));
//        colAdresa.setText(resources.getString("femijet.adresa"));
//        colEmriPrindit.setText(resources.getString("femijet.emriPrindit"));
//        colKontaktiPrindit.setText(resources.getString("femijet.kontaktiPrindit"));
//
//        lblEmri.setText(resources.getString("femijet.emri"));
//        lblMbiemri.setText(resources.getString("femijet.mbiemri"));
//        lblDitelindja.setText(resources.getString("femijet.ditelindja"));
//        lblGjinia.setText(resources.getString("femijet.gjinia"));
//        lblAdresa.setText(resources.getString("femijet.adresa"));
//        lblEmriPrindit.setText(resources.getString("femijet.emriPrindit"));
//        lblKontaktiPrindit.setText(resources.getString("femijet.kontaktiPrindit"));
//
//        btnShto.setText(resources.getString("actions.shto"));
//        btnPerditeso.setText(resources.getString("actions.perditeso"));
//        btnFshij.setText(resources.getString("actions.fshij"));
//        btnPastro.setText(resources.getString("actions.pastro"));
//
//        btnSwitchAlbanian.setText(resources.getString("language.shqip"));
//        btnSwitchEnglish.setText(resources.getString("language.english"));
//
//        cmbGjinia.setItems(FXCollections.observableArrayList(
//                resources.getString("gjinia.mashkull"),
//                resources.getString("gjinia.femer")
//        ));
//    }

//    private void updatePrompts() {
//        txtEmri.setPromptText(resources.getString("femijet.emri"));
//        txtMbiemri.setPromptText(resources.getString("femijet.mbiemri"));
//        txtDitelindja.setPromptText(resources.getString("femijet.ditelindja"));
//        cmbGjinia.setPromptText(resources.getString("femijet.gjinia"));
//        txtAdresa.setPromptText(resources.getString("femijet.adresa"));
//        txtEmriPrindit.setPromptText(resources.getString("femijet.emriPrindit"));
//        txtKontaktiPrindit.setPromptText(resources.getString("femijet.kontaktiPrindit"));
//    }

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
