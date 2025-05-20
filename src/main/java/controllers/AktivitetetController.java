package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.Aktivitetet.CreateAktivitetetDto;
import models.Dto.Aktivitetet.UpdateAktivitetetDto;
import models.domain.Aktivitetet;
import repository.AktivitetetRepository;
import services.AktivitetetService;
import Database.DBConnection;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class AktivitetetController {

    @FXML private TableView<Aktivitetet> tableAktivitetet;
    @FXML private TableColumn<Aktivitetet, Integer> colID;
    @FXML private TableColumn<Aktivitetet, String> colEmri;
    @FXML private TableColumn<Aktivitetet, String> colPershkrimi;
    @FXML private TableColumn<Aktivitetet, LocalDate> colData;
    @FXML private TableColumn<Aktivitetet, Integer> colGrupiID;

    @FXML private TextField tfEmriAktivitetit;
    @FXML private TextArea taPershkrimi;
    @FXML private DatePicker dpData;
    @FXML private TextField tfGrupiID;

    private AktivitetetService aktivitetetService;
    private ObservableList<Aktivitetet> aktivitetetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            Connection connection = DBConnection.getConnection();
            AktivitetetRepository repository = new AktivitetetRepository(connection);
            aktivitetetService = new AktivitetetService(repository);

            colID.setCellValueFactory(new PropertyValueFactory<>("aktivitetiID"));
            colEmri.setCellValueFactory(new PropertyValueFactory<>("emriAktivitetit"));
            colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
            colData.setCellValueFactory(new PropertyValueFactory<>("data"));
            colGrupiID.setCellValueFactory(new PropertyValueFactory<>("grupiID"));

            loadData();

            tableAktivitetet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    tfEmriAktivitetit.setText(newSelection.getEmriAktivitetit());
                    taPershkrimi.setText(newSelection.getPershkrimi());
                    dpData.setValue(newSelection.getData());
                    tfGrupiID.setText(String.valueOf(newSelection.getGrupiID()));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gabim gjatë inicializimit", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void loadData() {
        try {
            aktivitetetList.setAll(aktivitetetService.getAllAktivitetet());
            tableAktivitetet.setItems(aktivitetetList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gabim gjatë ngarkimit të të dhënave", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void addAktivitet() {
        try {
            CreateAktivitetetDto dto = new CreateAktivitetetDto(
                    tfEmriAktivitetit.getText(),
                    taPershkrimi.getText(),
                    dpData.getValue().toString(),
                    Integer.parseInt(tfGrupiID.getText())
            );
            aktivitetetService.addAktivitet(dto);
            loadData();
            clearForm();
        } catch (Exception e) {
            showAlert("Gabim gjatë shtimit të aktivitetit", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void updateAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Asnjë aktivitet i përzgjedhur", "Ju lutem përzgjidhni një aktivitet për ta përditësuar.", Alert.AlertType.WARNING);
            return;
        }
        try {
            UpdateAktivitetetDto dto = new UpdateAktivitetetDto(
                    selected.getAktivitetiID(),
                    tfEmriAktivitetit.getText(),
                    taPershkrimi.getText(),
                    dpData.getValue().toString(),
                    Integer.parseInt(tfGrupiID.getText())
            );
            aktivitetetService.updateAktivitet(dto);
            loadData();
            clearForm();
        } catch (Exception e) {
            showAlert("Gabim gjatë përditësimit të aktivitetit", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deleteAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Asnjë aktivitet i përzgjedhur", "Ju lutem përzgjidhni një aktivitet për ta fshirë.", Alert.AlertType.WARNING);
            return;
        }
        try {
            aktivitetetService.deleteAktivitet(selected.getAktivitetiID());
            loadData();
            clearForm();
        } catch (Exception e) {
            showAlert("Gabim gjatë fshirjes", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clearForm() {
        tfEmriAktivitetit.clear();
        taPershkrimi.clear();
        dpData.setValue(null);
        tfGrupiID.clear();
        tableAktivitetet.getSelectionModel().clearSelection();
    }
    @FXML
    private void switchToAlbanian() {
        switchLanguage("sq");
    }

    @FXML
    private void switchToEnglish() {
        switchLanguage("en");
    }

    private void switchLanguage(String languageCode) {
        try {
            Locale newLocale = Locale.forLanguageTag(languageCode);
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", newLocale);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AktivitetetView.fxml"), bundle);
            Parent root = loader.load();

            Stage stage = (Stage) tableAktivitetet.getScene().getWindow(); // Merr skenën aktuale
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gabim gjatë ndërrimit të gjuhës", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}