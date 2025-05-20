package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        // Placeholder për ndryshimin e gjuhës
        System.out.println("U kalua në Shqip");
    }

    @FXML
    private void switchToEnglish() {
        // Placeholder për ndryshimin e gjuhës
        System.out.println("Switched to English");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}