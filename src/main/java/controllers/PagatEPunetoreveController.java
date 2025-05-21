package controllers;

import services.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.PagatEPunetoreve;
import models.Dto.PagatEPunetoreve.UpdatePagatEPunetoreveDto;
import services.PagatEPunetoreveService;

import java.util.ArrayList;

public class PagatEPunetoreveController {

    private PagatEPunetoreveService pagaService;

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML
    private TableView<PagatEPunetoreve> tableView;

    @FXML
    private TableColumn<PagatEPunetoreve, Integer> idColumn;

    @FXML
    private TableColumn<PagatEPunetoreve, String> muajiColumn;

    @FXML
    private TableColumn<PagatEPunetoreve, Integer> vitiColumn;

    @FXML
    private TableColumn<PagatEPunetoreve, Double> shumaColumn;

    @FXML
    private TableColumn<PagatEPunetoreve, String> dataColumn;

    @FXML
    private TextField idTextField;

    @FXML
    private Button kerkoButton;

    @FXML
    private Button fshijButton;

    @FXML
    private Button ruajButton;

    public PagatEPunetoreveController() {
        this.pagaService = new PagatEPunetoreveService();
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("pagaID"));
        muajiColumn.setCellValueFactory(new PropertyValueFactory<>("muaji"));
        vitiColumn.setCellValueFactory(new PropertyValueFactory<>("viti"));
        shumaColumn.setCellValueFactory(new PropertyValueFactory<>("shumaPaga"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataEPageses"));

        // Load all pagat data
        refreshTable();
    }

    private void refreshTable() {
        ArrayList<PagatEPunetoreve> pagatList = pagaService.getAll();
        ObservableList<PagatEPunetoreve> observableList = FXCollections.observableArrayList(pagatList);
        tableView.setItems(observableList);
    }

    @FXML
    private void handleKerko() {
        String searchText = idTextField.getText().trim();
        if (searchText.isEmpty()) {
            refreshTable();
            return;
        }

        try {
            int id = Integer.parseInt(searchText);
            PagatEPunetoreve paga = pagaService.getByID(id);
            if (paga != null) {
                ObservableList<PagatEPunetoreve> singleItemList = FXCollections.observableArrayList();
                singleItemList.add(paga);
                tableView.setItems(singleItemList);
            } else {
                showAlert("Kerkim", "Nuk u gjet asnje paga me ID: " + id);
            }
        } catch (NumberFormatException e) {
            showAlert("Gabim", "ID duhet te jete numer!");
        } catch (Exception e) {
            showAlert("Gabim", e.getMessage());
        }
    }

    @FXML
    private void handleRuaj() {
        PagatEPunetoreve selectedPaga = tableView.getSelectionModel().getSelectedItem();
        if (selectedPaga == null) {
            showAlert("Gabim", "Ju lutem zgjidhni nje paga per te perditesuar!");
            return;
        }

        try {
            UpdatePagatEPunetoreveDto updateDto = new UpdatePagatEPunetoreveDto(
                    selectedPaga.getPagaID(),
                    selectedPaga.getEdukatoriID(),
                    selectedPaga.getMuaji(),
                    selectedPaga.getViti(),
                    selectedPaga.getShumaPaga(),
                    selectedPaga.getData()
            );

            pagaService.update(updateDto);
            showAlert("Sukses", "Paga u perditesua me sukses!");
            refreshTable();

        } catch (Exception e) {
            showAlert("Gabim", e.getMessage());
        }
    }

    @FXML
    private void handleFshij() {
        PagatEPunetoreve selectedPaga = tableView.getSelectionModel().getSelectedItem();
        if (selectedPaga == null) {
            showAlert("Gabim", "Ju lutem zgjidhni nje paga per te fshire!");
            return;
        }

        try {
            pagaService.delete(selectedPaga.getPagaID());
            showAlert("Sukses", "Paga u fshi me sukses!");
            refreshTable();
        } catch (Exception e) {
            showAlert("Gabim", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}