package controllers;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Femijet;

import java.sql.*;

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
    @FXML private Button shtoButoni;
    @FXML private Button perditesoButoni;
    @FXML private Button fshiButoni;

    private final ObservableList<Femijet> femijetList = FXCollections.observableArrayList();

    /* --------------------------------------------------------------------- */
    @FXML
    private void initialize() {

        // Popullo ComboBox-in manualisht
        gjiniaComboBox.getItems().addAll("Mashkull", "Femer");

        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        ditelindjaColumn.setCellValueFactory(new PropertyValueFactory<>("dataLindjes"));
        gjiniaColumn.setCellValueFactory(new PropertyValueFactory<>("gjinia")); // do të shfaqet M/F
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        femijetTable.setItems(femijetList);
        ngarkoFemijetNgaDB();

        femijetTable.setOnMouseClicked(event -> {
            Femijet f = femijetTable.getSelectionModel().getSelectedItem();
            if (f != null) {
                emriField.setText(f.getEmri());
                mbiemriField.setText(f.getMbiemri());
                ditelindjaField.setText(f.getDataLindjes());
                gjiniaComboBox.setValue(
                        f.getGjinia().equals("M") ? "Mashkull" : "Femer"
                );
                adresaField.setText(f.getAdresa());
            }
        });
    }

    /* --------------------------------------------------------------------- */
    private void ngarkoFemijetNgaDB() {
        femijetList.clear();
        String query = "SELECT * FROM femijet";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Femijet f = new Femijet(
                        rs.getInt("femijaid"),
                        rs.getString("emri"),
                        rs.getString("mbiemri"),
                        rs.getString("datalindjes"),
                        rs.getString("gjinia"),   // M ose F
                        rs.getString("adresa"),
                        "", ""
                );
                femijetList.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* --------------------------------------------------------------------- */
    @FXML
    private void shtoFemije() {
        String emri = emriField.getText();
        String mbiemri = mbiemriField.getText();
        String ditelindja = ditelindjaField.getText();
        String gjiniaDisplay = gjiniaComboBox.getValue();
        String adresa = adresaField.getText();

        if (emri.isEmpty() || mbiemri.isEmpty() || ditelindja.isEmpty()
                || gjiniaDisplay == null || adresa.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Ju lutem plotësoni të gjitha fushat!");
            return;
        }

        String gjiniaDB = gjiniaDisplay.equals("Mashkull") ? "M" : "F";

        String query = "INSERT INTO femijet (emri, mbiemri, datalindjes, gjinia, adresa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, emri);
            ps.setString(2, mbiemri);
            ps.setString(3, ditelindja);
            ps.setString(4, gjiniaDB);
            ps.setString(5, adresa);

            if (ps.executeUpdate() > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Fëmija u shtua me sukses.");
                ngarkoFemijetNgaDB();
                pastrimiFushave();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë shtimit në databazë.");
        }
    }

    /* --------------------------------------------------------------------- */
    @FXML
    private void perditesoFemije() {
        Femijet sel = femijetTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidh një fëmijë për përditësim.");
            return;
        }

        String gjiniaDisplay = gjiniaComboBox.getValue();
        String gjiniaDB = gjiniaDisplay.equals("Mashkull") ? "M" : "F";

        String query = "UPDATE femijet SET emri=?, mbiemri=?, datalindjes=?, gjinia=?, adresa=? WHERE femijaid=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, emriField.getText());
            ps.setString(2, mbiemriField.getText());
            ps.setString(3, ditelindjaField.getText());
            ps.setString(4, gjiniaDB);
            ps.setString(5, adresaField.getText());
            ps.setInt(6, sel.getFemijaID());

            if (ps.executeUpdate() > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Fëmija u përditësua me sukses.");
                ngarkoFemijetNgaDB();
                pastrimiFushave();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë përditësimit.");
        }
    }

    /* --------------------------------------------------------------------- */
    @FXML
    private void fshiFemije() {
        Femijet sel = femijetTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidh një fëmijë për fshirje.");
            return;
        }

        String query = "DELETE FROM femijet WHERE femijaid=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, sel.getFemijaID());

            if (ps.executeUpdate() > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Fëmija u fshi me sukses.");
                ngarkoFemijetNgaDB();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Gabim gjatë fshirjes.");
        }
    }

    /* --------------------------------------------------------------------- */
    private void pastrimiFushave() {
        emriField.clear();
        mbiemriField.clear();
        ditelindjaField.clear();
        gjiniaComboBox.setValue(null);
        adresaField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
