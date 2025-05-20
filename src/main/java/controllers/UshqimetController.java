package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dto.Ushqimet.CreateUshqimetDto;
import models.Dto.Ushqimet.UpdateUshqimetDto;
import models.domain.Ushqimet;
import services.UshqimetService;

import java.util.List;

public class UshqimetController {

    @FXML private TableView<Ushqimet> ushqimetTable;
    @FXML private TableColumn<Ushqimet, Integer> idCol;
    @FXML private TableColumn<Ushqimet, String> emriCol;
    @FXML private TableColumn<Ushqimet, String> kategoriaCol;
    @FXML private TableColumn<Ushqimet, String> pershkrimiCol;

    @FXML private TextField emriField;
    @FXML private TextField kategoriaField;
    @FXML private TextField pershkrimiField;

    private final UshqimetService ushqimetService = new UshqimetService();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("ushqimiID"));
        emriCol.setCellValueFactory(new PropertyValueFactory<>("emriUshqimit"));
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        pershkrimiCol.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));

        loadData();

        ushqimetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                emriField.setText(newVal.getEmriUshqimit());
                kategoriaField.setText(newVal.getKategoria());
                pershkrimiField.setText(newVal.getPershkrimi());
            }
        });
    }

    private void loadData() {
        List<Ushqimet> ushqimet = ushqimetService.getAll();
        ushqimetTable.getItems().setAll(ushqimet);
    }

    @FXML
    private void handleAdd() {
        try {
            CreateUshqimetDto dto = new CreateUshqimetDto(
                    emriField.getText(),
                    kategoriaField.getText(),
                    pershkrimiField.getText()
            );
            ushqimetService.create(dto);
            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert("Gabim gjatë shtimit", e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Ushqimet selected = ushqimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Zgjidh një ushqim", "Nuk ke zgjedhur ushqim për përditësim.");
            return;
        }

        try {
            UpdateUshqimetDto dto = new UpdateUshqimetDto(
                    selected.getUshqimiID(),
                    emriField.getText(),
                    kategoriaField.getText(),
                    pershkrimiField.getText()
            );
            ushqimetService.update(dto);
            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert("Gabim gjatë përditësimit", e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Ushqimet selected = ushqimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Zgjidh një ushqim", "Nuk ke zgjedhur ushqim për fshirje.");
            return;
        }

        try {
            ushqimetService.delete(selected.getUshqimiID());
            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert("Gabim gjatë fshirjes", e.getMessage());
        }
    }

    private void clearFields() {
        emriField.clear();
        kategoriaField.clear();
        pershkrimiField.clear();
        ushqimetTable.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void shtoUshqimin(ActionEvent actionEvent) {
    }
}
