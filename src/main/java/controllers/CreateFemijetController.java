package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import models.domain.Femijet;

public class CreateFemijetController {

    @FXML private TableView<Femijet> femijetTable;
    @FXML private TableColumn<Femijet, String> emriColumn;
    @FXML private TableColumn<Femijet, String> mbiemriColumn;
    @FXML private TableColumn<Femijet, String> ditelindjaColumn;
    @FXML private TableColumn<Femijet, String> gjiniaColumn;
    @FXML private TableColumn<Femijet, String> adresaColumn;

    @FXML private TextField emriField;
    @FXML private TextField mbiemriField;
    @FXML private TextField ditelindjaField;
    @FXML private ComboBox<String> gjiniaComboBox;
    @FXML private TextField adresaField;

    private final ObservableList<Femijet> femijet = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Inicializo kolonat
        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        ditelindjaColumn.setCellValueFactory(new PropertyValueFactory<>("ditelindja"));
        gjiniaColumn.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        // Mbush ComboBox-in
        gjiniaComboBox.setItems(FXCollections.observableArrayList("Mashkull", "Femër"));

        // Lidho listën me tabelën
        femijetTable.setItems(femijet);

        // Kur përzgjidhet një rresht, mbush fushat
        femijetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                emriField.setText(newSelection.getEmri(emriField.getText()));
                mbiemriField.setText(newSelection.getMbiemri(mbiemriField.getText()));
                ditelindjaField.setText(newSelection.getDataLindjes(ditelindjaField.getText()));
                gjiniaComboBox.setValue(newSelection.getGjinia(gjiniaComboBox.getValue()));
                adresaField.setText(newSelection.getAdresa(adresaField.getText()));
            }
        });
    }

    @FXML
    private void shtoFemije() {
        Femijet f = new Femijet(
                emriField.getText(),
                mbiemriField.getText(),
                ditelindjaField.getText(),
                gjiniaComboBox.getValue(),
                adresaField.getText()
        );
        femijet.add(f);
        pastroFushat();
    }

    @FXML
    private void perditesoFemije() {
        Femijet f = femijetTable.getSelectionModel().getSelectedItem();
        if (f != null) {
            f.getEmri(emriField.getText());
            f.getMbiemri(mbiemriField.getText());
            f.getDataLindjes(ditelindjaField.getText());
            f.getGjinia(gjiniaComboBox.getValue());
            f.getAdresa(adresaField.getText());
            femijetTable.refresh();
            pastroFushat();
        }
    }

    @FXML
    private void fshiFemije() {
        Femijet f = femijetTable.getSelectionModel().getSelectedItem();
        if (f != null) {
            femijet.remove(f);
            pastroFushat();
        }
    }

    private void pastroFushat() {
        emriField.clear();
        mbiemriField.clear();
        ditelindjaField.clear();
        gjiniaComboBox.setValue(null);
        adresaField.clear();
        femijetTable.getSelectionModel().clearSelection();
    }
}
