package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Femijet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateFemijetController {

    @FXML
    private TableView<Femijet> femijetTable;

    @FXML
    private TableColumn<Femijet, String> emriColumn;

    @FXML
    private TableColumn<Femijet, String> mbiemriColumn;

    @FXML
    private TableColumn<Femijet, String> ditelindjaColumn;

    @FXML
    private TableColumn<Femijet, String> gjiniaColumn;

    @FXML
    private TableColumn<Femijet, String> adresaColumn;

    @FXML
    private TextField emriField;

    @FXML
    private TextField mbiemriField;

    @FXML
    private TextField ditelindjaField;

    @FXML
    private ComboBox<String> gjiniaComboBox;

    @FXML
    private TextField adresaField;

    @FXML
    private Button shtoButoni;

    @FXML
    private Button perditesoButoni;

    @FXML
    private Button fshiButoni;

    @FXML
    private final ObservableList<Femijet> femijetList = FXCollections.observableArrayList();

    private List<String> merrGjiniteNgaDatabase() {
        List<String> gjiniet = new ArrayList<>();
        String query = "SELECT unnest(enum_range(NULL::gjinia_enum))";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/knkdatabase", "postgres", "passwordi");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                gjiniet.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gjiniet;
    }

    @FXML
    private void initialize() {
        // Shto vlerat e gjinive nga databaza
        gjiniaComboBox.getItems().addAll(merrGjiniteNgaDatabase());

        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        ditelindjaColumn.setCellValueFactory(new PropertyValueFactory<>("dataLindjes"));
        gjiniaColumn.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        femijetTable.setItems(femijetList);

        femijetTable.setOnMouseClicked(event -> {
            Femijet f = femijetTable.getSelectionModel().getSelectedItem();
            if (f != null) {
                emriField.setText(f.getEmri());
                mbiemriField.setText(f.getMbiemri());
                ditelindjaField.setText(f.getDataLindjes());
                gjiniaComboBox.setValue(f.getGjinia());
                adresaField.setText(f.getAdresa());
            }
        });
    }

    @FXML
    private void shtoFemije() {
        String emri = emriField.getText();
        String mbiemri = mbiemriField.getText();
        String ditelindja = ditelindjaField.getText();
        String gjinia = gjiniaComboBox.getValue();
        String adresa = adresaField.getText();

        if (emri.isEmpty() || mbiemri.isEmpty() || ditelindja.isEmpty() || gjinia == null || adresa.isEmpty()) {
            System.out.println("Ju lutem plotësoni të gjitha fushat!");
            return;
        }

        Femijet f = new Femijet(0, emri, mbiemri, ditelindja, gjinia, adresa, "", "");
        femijetList.add(f);

        emriField.clear();
        mbiemriField.clear();
        ditelindjaField.clear();
        gjiniaComboBox.setValue(null);
        adresaField.clear();
    }

    @FXML
    private void perditesoFemije() {
        int selectedIndex = femijetTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Femijet updated = new Femijet(
                    0,
                    emriField.getText(),
                    mbiemriField.getText(),
                    ditelindjaField.getText(),
                    gjiniaComboBox.getValue(),
                    adresaField.getText(),
                    "",
                    ""
            );
            femijetList.set(selectedIndex, updated);
        } else {
            System.out.println("Zgjidh një fëmijë për ta përditësuar.");
        }
    }

    @FXML
    private void fshiFemije() {
        Femijet f = femijetTable.getSelectionModel().getSelectedItem();
        if (f != null) {
            femijetList.remove(f);
        } else {
            System.out.println("Zgjidh një fëmijë për ta fshirë.");
        }
    }
}