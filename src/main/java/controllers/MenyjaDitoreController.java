package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.domain.MenyjaDitore;
import models.Dto.MenyjaDitore.CreateMenyjaDitoreDto;
import services.MenyjaDitoreService;

import java.util.List;

public class MenyjaDitoreController {

    @FXML
    private ComboBox<String> ditaComboBox;

    @FXML
    private ComboBox<Integer> grupiComboBox;

    @FXML
    private ComboBox<Integer> ushqimiComboBox;

    @FXML
    private TableView<MenyjaDitore> menyjaTable;

    @FXML
    private TableColumn<MenyjaDitore, Integer> menuIdColumn;

    @FXML
    private TableColumn<MenyjaDitore, String> ditaColumn;

    @FXML
    private TableColumn<MenyjaDitore, Integer> grupiIdColumn;

    @FXML
    private TableColumn<MenyjaDitore, Integer> ushqimiIdColumn;

    private MenyjaDitoreService service;

    private ObservableList<MenyjaDitore> menyjaData;

    @FXML
    public void initialize() {
        service = new MenyjaDitoreService();

        menuIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMenuID()).asObject());
        ditaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDita()));
        grupiIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getGrupiID()).asObject());
        ushqimiIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUshqimiID()).asObject());

        loadComboBoxes();
        loadTableData();
    }

    private void loadComboBoxes() {
        ditaComboBox.setItems(FXCollections.observableArrayList(
                "E Hene", "E Marte", "E Merkure", "E Enjte", "E Premte", "E Shtune", "E Diel"));

        ObservableList<Integer> grupet = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            grupet.add(i);
        }
        grupiComboBox.setItems(grupet);

        ObservableList<Integer> ushqimet = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            ushqimet.add(i);
        }
        ushqimiComboBox.setItems(ushqimet);
    }

    private void loadTableData() {
        List<MenyjaDitore> menyjaList = service.getAll();
        menyjaData = FXCollections.observableArrayList(menyjaList);
        menyjaTable.setItems(menyjaData);
    }

    @FXML
    private void handleShto() {
        try {
            String dita = ditaComboBox.getValue();
            Integer grupiID = grupiComboBox.getValue();
            Integer ushqimiID = ushqimiComboBox.getValue();

            if (dita == null || dita.isEmpty()) {
                showAlert("Gabim", "Ju lutem zgjidhni Ditën.");
                return;
            }
            if (grupiID == null || grupiID <= 0) {
                showAlert("Gabim", "Ju lutem zgjidhni Grupin.");
                return;
            }
            if (ushqimiID == null || ushqimiID <= 0) {
                showAlert("Gabim", "Ju lutem zgjidhni Ushqimin.");
                return;
            }

            CreateMenyjaDitoreDto createDto = new CreateMenyjaDitoreDto(dita, grupiID, ushqimiID);
            MenyjaDitore newItem = service.create(createDto);

            if (newItem != null) {
                menyjaData.add(newItem);
                showAlert("Sukses", "Shtimi u krye me sukses.");
                clearInputs();
            } else {
                showAlert("Gabim", "Shtimi dështoi.");
            }
        } catch (Exception ex) {
            showAlert("Gabim", ex.getMessage());
        }
    }

    @FXML
    private void handleFshij() {
        MenyjaDitore selected = menyjaTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Gabim", "Ju lutem zgjidhni një rresht për të fshirë.");
            return;
        }

        try {
            service.delete(selected.getMenuID());
            menyjaData.remove(selected);
            showAlert("Sukses", "Fshirja u krye me sukses.");
        } catch (Exception ex) {
            showAlert("Gabim", ex.getMessage());
        }
    }

    @FXML
    private void handlePastro() {
        ditaComboBox.getSelectionModel().clearSelection();
        grupiComboBox.getSelectionModel().clearSelection();
        ushqimiComboBox.getSelectionModel().clearSelection();
    }

    private void clearInputs() {
        handlePastro();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
