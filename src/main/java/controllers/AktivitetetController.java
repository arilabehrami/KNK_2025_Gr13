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
import java.util.Locale;
import java.util.ResourceBundle;
import services.UserSession;

public class AktivitetetController {

    @FXML private TableView<Aktivitetet> tableAktivitetet;
    @FXML private TableColumn<Aktivitetet, Integer> colID;
    @FXML private TableColumn<Aktivitetet, String> colEmri;
    @FXML private TableColumn<Aktivitetet, String> colPershkrimi;
    @FXML private TableColumn<Aktivitetet, LocalDate> colData;
    @FXML private TableColumn<Aktivitetet, Integer> colGrupiID;
    @FXML private Button btnSwitchAlbanian;
    @FXML private Button btnSwitchEnglish;
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnClear;
    @FXML private Label lblEmriAktivitetit;
    @FXML private Label lblPershkrimi;
    @FXML private Label lblData;
    @FXML private Label lblGrupiID;
    @FXML private TextField tfEmriAktivitetit;
    @FXML private TextArea taPershkrimi;
    @FXML private DatePicker dpData;
    @FXML private TextField tfGrupiID;

    private AktivitetetService aktivitetetService;
    private ObservableList<Aktivitetet> aktivitetetList = FXCollections.observableArrayList();

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();

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

            resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));
            updateUILabels();
            updatePrompts();

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
            if (tfEmriAktivitetit.getText().isEmpty()) {
                showAlert("Gabim", "Ju lutem shkruani emrin e aktivitetit.", Alert.AlertType.WARNING);
                return;
            }
            if (dpData.getValue() == null) {
                showAlert("Gabim", "Ju lutem zgjidhni një datë valide.", Alert.AlertType.WARNING);
                return;
            }
            int grupiID;
            try {
                grupiID = Integer.parseInt(tfGrupiID.getText());
            } catch (NumberFormatException e) {
                showAlert("Gabim", "GrupiID duhet të jetë numër i plotë.", Alert.AlertType.WARNING);
                return;
            }

            CreateAktivitetetDto dto = new CreateAktivitetetDto(
                    tfEmriAktivitetit.getText(),
                    taPershkrimi.getText(),
                    dpData.getValue().toString(),
                    grupiID
            );
            aktivitetetService.addAktivitet(dto);
            loadData();
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
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
    private ResourceBundle resources;

    @FXML
    public void switchToAlbanian() {
        try {
            resources = ResourceBundle.getBundle("languages.messages", new Locale("sq"));
            updateUILabels();
            updatePrompts();
        } catch (Exception e) {
            showAlert("Gabim në ndërrimin e gjuhës", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void switchToEnglish() {
        try {
            resources = ResourceBundle.getBundle("languages.messages", Locale.ENGLISH);
            updateUILabels();
            updatePrompts();
        } catch (Exception e) {
            showAlert("Error switching language", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void updateUILabels() {
        if (resources == null) return;

        colID.setText(resources.getString("aktivitetet.col.id"));
        colEmri.setText(resources.getString("aktivitetet.col.emri"));
        colPershkrimi.setText(resources.getString("aktivitetet.col.pershkrimi"));
        colData.setText(resources.getString("aktivitetet.col.data"));
        colGrupiID.setText(resources.getString("aktivitetet.col.grupiid"));

        btnSwitchAlbanian.setText(resources.getString("aktivitetet.button.switchAlbanian"));
        btnSwitchEnglish.setText(resources.getString("aktivitetet.button.switchEnglish"));
        btnAdd.setText(resources.getString("aktivitetet.btn.add"));
        btnUpdate.setText(resources.getString("aktivitetet.btn.update"));
        btnDelete.setText(resources.getString("aktivitetet.btn.delete"));
        btnClear.setText(resources.getString("aktivitetet.btn.clear"));


        lblEmriAktivitetit.setText(resources.getString("aktivitetet.label.emri"));
        lblPershkrimi.setText(resources.getString("aktivitetet.label.pershkrimi"));
        lblData.setText(resources.getString("aktivitetet.label.data"));
        lblGrupiID.setText(resources.getString("aktivitetet.label.grupiid"));

    }

    private void updatePrompts() {
        if (resources == null) return;

        tfEmriAktivitetit.setPromptText(resources.getString("aktivitetet.promptEmri"));
        taPershkrimi.setPromptText(resources.getString("aktivitetet.promptPershkrimi"));
        tfGrupiID.setPromptText(resources.getString("aktivitetet.promptGrupiID"));
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}