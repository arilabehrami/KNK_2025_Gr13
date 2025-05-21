package controllers;

import services.UserSession;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import models.domain.Orari;
import services.OrariService;

import java.util.Locale;
import java.util.ResourceBundle;

public class OrariController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML private TableView<Orari> tableOrari;
    @FXML private TableColumn<Orari, Integer> colOrariID;
    @FXML private TableColumn<Orari, Integer> colFemijaID;
    @FXML private TableColumn<Orari, String> colDita;
    @FXML private TableColumn<Orari, String> colOraHyrjes;
    @FXML private TableColumn<Orari, String> colOraDaljes;

    @FXML private TextField tfFemijaID;
    @FXML private TextField tfDita;
    @FXML private TextField tfOraHyrjes;
    @FXML private TextField tfOraDaljes;

    @FXML private Button btnSwitchAlbanian;
    @FXML private Button btnSwitchEnglish;
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnClear;

    @FXML private Label lblFemijaID;
    @FXML private Label lblDita;
    @FXML private Label lblOraHyrjes;
    @FXML private Label lblOraDaljes;


    private OrariService orariService = new OrariService();
    private final ObservableList<Orari> orariList = FXCollections.observableArrayList();
    private Orari selectedOrari;
    private ResourceBundle resources;

    @FXML
    public void initialize() {
        // Ngarko resource bundle default (shqip)
        resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));

        colOrariID.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getOrariID()));
        colFemijaID.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFemijaID()));
        colDita.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getDita()));
        colOraHyrjes.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getOraHyrjes().toString()));
        colOraDaljes.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getOraDaljes().toString()));

        tableOrari.setOnMouseClicked(this::handleRowSelect);

        updateUILabels();

        tfFemijaID.setPromptText(resources.getString("orari.lblFemijaID"));
        tfDita.setPromptText(resources.getString("orari.lblDita"));
        tfOraHyrjes.setPromptText(resources.getString("orari.lblOraHyrjes"));
        tfOraDaljes.setPromptText(resources.getString("orari.lblOraDaljes"));

        loadOrariData();
    }

    private void loadOrariData() {
        orariList.clear();
        try {
            orariList.addAll(orariService.getAllOrari());
            tableOrari.setItems(orariList);
        } catch (Exception e) {
            showError(resources.getString("orari.error.load") + "\n" + e.getMessage());
        }
    }

    private void handleRowSelect(MouseEvent event) {
        selectedOrari = tableOrari.getSelectionModel().getSelectedItem();
        if (selectedOrari != null) {
            tfFemijaID.setText(String.valueOf(selectedOrari.getFemijaID()));
            tfDita.setText(selectedOrari.getDita());
            tfOraHyrjes.setText(selectedOrari.getOraHyrjes().toString());
            tfOraDaljes.setText(selectedOrari.getOraDaljes().toString());
        }
    }

    @FXML
    public void addOrari() {
        try {
            int femijaID = Integer.parseInt(tfFemijaID.getText());
            String dita = tfDita.getText();
            String oraHyrjes = tfOraHyrjes.getText();
            String oraDaljes = tfOraDaljes.getText();

            CreateOrariDto dto = new CreateOrariDto(femijaID, dita, oraHyrjes, oraDaljes);
            orariService.addOrari(dto);
            clearForm();
            loadOrariData();
            showInfo(resources.getString("orari.info.added"));
        } catch (NumberFormatException nfe) {
            showError(resources.getString("error.invalid_id"));
        } catch (Exception e) {
            showError(resources.getString("orari.error.add") + "\n" + e.getMessage());
        }
    }

    @FXML
    public void updateOrari() {
        if (selectedOrari == null) {
            showError(resources.getString("orari.warning.select"));
            return;
        }
        try {
            int orariID = selectedOrari.getOrariID();
            int femijaID = Integer.parseInt(tfFemijaID.getText());
            String dita = tfDita.getText();
            String oraHyrjes = tfOraHyrjes.getText();
            String oraDaljes = tfOraDaljes.getText();

            UpdateOrariDto dto = new UpdateOrariDto(orariID, femijaID, dita, oraHyrjes, oraDaljes);
            orariService.updateOrari(dto);
            clearForm();
            loadOrariData();
            showInfo(resources.getString("orari.info.updated"));
        } catch (NumberFormatException nfe) {
            showError(resources.getString("error.invalid_id"));
        } catch (Exception e) {
            showError(resources.getString("orari.error.update") + "\n" + e.getMessage());
        }
    }

    @FXML
    public void deleteOrari() {
        if (selectedOrari == null) {
            showError(resources.getString("orari.warning.select"));
            return;
        }
        try {
            orariService.deleteOrari(selectedOrari.getOrariID());
            clearForm();
            loadOrariData();
            showInfo(resources.getString("orari.info.deleted"));
        } catch (Exception e) {
            showError(resources.getString("orari.error.delete") + "\n" + e.getMessage());
        }
    }

    @FXML
    public void clearForm() {
        tfFemijaID.clear();
        tfDita.clear();
        tfOraHyrjes.clear();
        tfOraDaljes.clear();
        selectedOrari = null;
        tableOrari.getSelectionModel().clearSelection();
    }

    @FXML
    public void switchToAlbanian() {
        try {
            resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));
            updateUILabels();
            updatePrompts();
        } catch (Exception e) {
            showError("Gabim në ndërrimin e gjuhës: " + e.getMessage());
        }
    }

    @FXML
    public void switchToEnglish() {
        try {
            resources = ResourceBundle.getBundle("languages.messages", Locale.ENGLISH);
            updateUILabels();
            updatePrompts();
        } catch (Exception e) {
            showError("Error switching language: " + e.getMessage());
        }
    }

    private void updateUILabels() {
        if (resources == null) return;

        colOrariID.setText(resources.getString("orari.id"));
        colFemijaID.setText(resources.getString("orari.femijaID"));
        colDita.setText(resources.getString("orari.dita"));
        colOraHyrjes.setText(resources.getString("orari.oraHyrjes"));
        colOraDaljes.setText(resources.getString("orari.oraDaljes"));

        btnSwitchAlbanian.setText(resources.getString("button.switchAlbanian"));
        btnSwitchEnglish.setText(resources.getString("button.switchEnglish"));

        btnAdd.setText(resources.getString("button.add"));
        btnUpdate.setText(resources.getString("button.update"));
        btnDelete.setText(resources.getString("button.delete"));
        btnClear.setText(resources.getString("button.clear"));
        lblFemijaID.setText(resources.getString("orari.lblFemijaID"));
        lblDita.setText(resources.getString("orari.lblDita"));
        lblOraHyrjes.setText(resources.getString("orari.lblOraHyrjes"));
        lblOraDaljes.setText(resources.getString("orari.lblOraDaljes"));

    }

    private void updatePrompts() {
        if (resources == null) return;

        tfFemijaID.setPromptText(resources.getString("orari.lblFemijaID"));
        tfDita.setPromptText(resources.getString("orari.lblDita"));
        tfOraHyrjes.setPromptText(resources.getString("orari.lblOraHyrjes"));
        tfOraDaljes.setPromptText(resources.getString("orari.lblOraDaljes"));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resources != null ? resources.getString("alert.error") : "Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resources != null ? resources.getString("alert.success") : "Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setService(OrariService service) {
        this.orariService = service;
    }
}