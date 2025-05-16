package controllers;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Ushqimet;

import java.sql.*;

public class UshqimetController {

    @FXML
    private TableView<Ushqimet> ushqimetTable;
    @FXML
    private TableColumn<Ushqimet, Integer> idColumn;
    @FXML
    private TableColumn<Ushqimet, String> emriColumn;
    @FXML
    private TableColumn<Ushqimet, String> kategoriaColumn;
    @FXML
    private TableColumn<Ushqimet, String> pershkrimiColumn;

    @FXML
    private Button shtoButton;
    @FXML
    private Button fshijButton;
    @FXML
    private Button pastroButton;

    @FXML
    private ComboBox<String> emriComboBox;
    @FXML
    private ComboBox<String> kategoriaComboBox;
    @FXML
    private ComboBox<String> pershkrimiComboBox;

    private ObservableList<Ushqimet> ushqimetList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kategoriaComboBox.setEditable(true);
        emriComboBox.setEditable(true);
        pershkrimiComboBox.setEditable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ushqimiID"));
        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emriUshqimit"));
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        pershkrimiColumn.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));

        ushqimetTable.setItems(ushqimetList);
        loadUshqimetFromDB();

        loadComboBoxValues();
    }

    private void loadComboBoxValues() {
        loadComboBox("SELECT DISTINCT emriushqimit FROM ushqimet", emriComboBox);
        loadComboBox("SELECT DISTINCT kategoria FROM ushqimet", kategoriaComboBox);
        loadComboBox("SELECT DISTINCT pershkrimi FROM ushqimet", pershkrimiComboBox);
    }

    private void loadComboBox(String query, ComboBox<String> comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(rs.getString(1));
            }

            comboBox.setItems(list);
            if (!list.isEmpty()) {
                comboBox.getSelectionModel().selectFirst();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUshqimetFromDB() {
        ushqimetList.clear();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ushqimet")) {

            while (rs.next()) {
                ushqimetList.add(Ushqimet.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShto() {
        String emri = emriComboBox.getEditor().getText().trim();
        String kategoria = kategoriaComboBox.getEditor().getText().trim();
        String pershkrimi = pershkrimiComboBox.getEditor().getText().trim();

        if (emri.isEmpty() || kategoria.isEmpty() || pershkrimi.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO ushqimet (emriushqimit, kategoria, pershkrimi) VALUES (?, ?, ?)")) {

            ps.setString(1, emri);
            ps.setString(2, kategoria);
            ps.setString(3, pershkrimi);

            int i = ps.executeUpdate();
            if (i > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Ushqimi u shtua me sukses.");

                loadUshqimetFromDB();
                handlePastro();

                addToComboBoxIfAbsent(emriComboBox, emri);
                addToComboBoxIfAbsent(kategoriaComboBox, kategoria);
                addToComboBoxIfAbsent(pershkrimiComboBox, pershkrimi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë shtimit në databazë.");
        }
    }

    private void addToComboBoxIfAbsent(ComboBox<String> comboBox, String value) {
        if (comboBox.getItems().stream().noneMatch(item -> item.equalsIgnoreCase(value))) {
            comboBox.getItems().add(value);
        }
    }

    @FXML
    private void handleFshij() {
        Ushqimet selected = ushqimetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidh një ushqim për fshirje.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM ushqimet WHERE ushqimiid = ?")) {

            ps.setInt(1, selected.getUshqimiID());

            int i = ps.executeUpdate();
            if (i > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Ushqimi u fshi me sukses.");
                loadUshqimetFromDB();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë fshirjes nga databaza.");
        }
    }

    @FXML
    private void handlePastro() {
        emriComboBox.getEditor().clear();
        kategoriaComboBox.getEditor().clear();
        pershkrimiComboBox.getEditor().clear();

        emriComboBox.getSelectionModel().clearSelection();
        kategoriaComboBox.getSelectionModel().clearSelection();
        pershkrimiComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
