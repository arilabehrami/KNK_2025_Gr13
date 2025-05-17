package controllers;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.MenyjaDitore;
import models.domain.Grupet;
import models.domain.Ushqimet;

import java.sql.*;

public class MenyjaDitoreController {

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

    @FXML
    private ComboBox<String> ditaComboBox;
    @FXML
    private ComboBox<Grupet> grupiComboBox;
    @FXML
    private ComboBox<Ushqimet> ushqimiComboBox;

    @FXML
    private Button shtoButton;
    @FXML
    private Button fshijButton;
    @FXML
    private Button pastroButton;

    private ObservableList<MenyjaDitore> menyjaList = FXCollections.observableArrayList();
    private ObservableList<Grupet> grupetList = FXCollections.observableArrayList();
    private ObservableList<Ushqimet> ushqimetList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize table columns
        menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("menuID"));
        ditaColumn.setCellValueFactory(new PropertyValueFactory<>("dita"));
        grupiIdColumn.setCellValueFactory(new PropertyValueFactory<>("grupiID"));
        ushqimiIdColumn.setCellValueFactory(new PropertyValueFactory<>("ushqimiID"));

        menyjaTable.setItems(menyjaList);

        // Load initial data
        loadDitaComboBox();
        loadGrupetComboBox();
        loadUshqimetComboBox();
        loadMenyjaFromDB();

        // Set up selection listener
        menyjaTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        ditaComboBox.getSelectionModel().select(newSelection.getDita());
                       // grupiComboBox.getSelectionModel().select(findGrupiById(newSelection.getGrupiID()));
                        ushqimiComboBox.getSelectionModel().select(findUshqimById(newSelection.getUshqimiID()));
                    }
                });
    }

    private void loadDitaComboBox() {
        ObservableList<String> dite = FXCollections.observableArrayList(
                "E Hënë", "E Martë", "E Mërkurë", "E Enjte", "E Premte", "E Shtunë", "E Diel"
        );
        ditaComboBox.setItems(dite);
    }

    private void loadGrupetComboBox() {
        grupetList.clear();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM grupet")) {

            while (rs.next()) {
                grupetList.add(Grupet.getInstance(rs));
            }
            grupiComboBox.setItems(grupetList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUshqimetComboBox() {
        ushqimetList.clear();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ushqimet")) {

            while (rs.next()) {
                ushqimetList.add(Ushqimet.getInstance(rs));
            }
            ushqimiComboBox.setItems(ushqimetList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMenyjaFromDB() {
        menyjaList.clear();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM menyjaditore")) {

            while (rs.next()) {
                menyjaList.add(MenyjaDitore.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    private Grupet findGrupiById(int id) {
//        return grupetList.stream()
//                .filter(g -> g.getGrupiID() == id)
//                .findFirst()
//                .orElse(null);
//    }

    private Ushqimet findUshqimById(int id) {
        return ushqimetList.stream()
                .filter(u -> u.getUshqimiID() == id)
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void handleShto() {
        String dita = ditaComboBox.getValue();
        Grupet grupi = grupiComboBox.getValue();
        Ushqimet ushqimi = ushqimiComboBox.getValue();

        if (dita == null || grupi == null || ushqimi == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO menyjaditore (dita, grupiid, ushqimiid) VALUES (?, ?, ?)")) {

            ps.setString(1, dita);
           // ps.setInt(2, grupi.getGrupiID());
            ps.setInt(3, ushqimi.getUshqimiID());

            int i = ps.executeUpdate();
            if (i > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Menyja u shtua me sukses.");
                loadMenyjaFromDB();
                handlePastro();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë shtimit në databazë.");
        }
    }

    @FXML
    private void handleFshij() {
        MenyjaDitore selected = menyjaTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidh një meni për fshirje.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM menyjaditore WHERE menuid = ?")) {

            ps.setInt(1, selected.getMenuID());

            int i = ps.executeUpdate();
            if (i > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Menyja u fshi me sukses.");
                loadMenyjaFromDB();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë fshirjes nga databaza.");
        }
    }

    @FXML
    private void handlePastro() {
        ditaComboBox.getSelectionModel().clearSelection();
        grupiComboBox.getSelectionModel().clearSelection();
        ushqimiComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}