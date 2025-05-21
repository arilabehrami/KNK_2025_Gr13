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
import services.LanguageManager;
import services.UserSession;

public class AktivitetetController {

    @FXML private TableView<Aktivitetet> tableAktivitetet;
    @FXML private TableColumn<Aktivitetet, Integer> colID;
    @FXML private TableColumn<Aktivitetet, String> colEmri;
    @FXML private TableColumn<Aktivitetet, String> colPershkrimi;
    @FXML private TableColumn<Aktivitetet, LocalDate> colData;
    @FXML private TableColumn<Aktivitetet, Integer> colGrupiID;
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

    private ResourceBundle resources;

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

            resources = LanguageManager.getBundle();

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
                showAlert(resources.getString("gabim"), resources.getString("aktivitetet.error.emri.required"), Alert.AlertType.WARNING);
                return;
            }
            if (dpData.getValue() == null) {
                showAlert(resources.getString("gabim"), resources.getString("aktivitetet.error.data.required"), Alert.AlertType.WARNING);
                return;
            }
            int grupiID;
            try {
                grupiID = Integer.parseInt(tfGrupiID.getText());
            } catch (NumberFormatException e) {
                showAlert(resources.getString("gabim"), resources.getString("aktivitetet.error.grupiid.number"), Alert.AlertType.WARNING);
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
            showAlert(resources.getString("gabim"), e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void updateAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(resources.getString("aktivitetet.warning.noSelection"), resources.getString("aktivitetet.warning.selectForUpdate"), Alert.AlertType.WARNING);
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
            showAlert(resources.getString("gabim"), e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deleteAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(resources.getString("aktivitetet.warning.noSelection"), resources.getString("aktivitetet.warning.selectForDelete"), Alert.AlertType.WARNING);
            return;
        }
        try {
            aktivitetetService.deleteAktivitet(selected.getAktivitetiID());
            loadData();
            clearForm();
        } catch (Exception e) {
            showAlert(resources.getString("gabim"), e.getMessage(), Alert.AlertType.ERROR);
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
    public void switchToAlbanian() {
        LanguageManager.setLocale(new Locale("sq"));
        refreshLanguage();
    }

    @FXML
    public void switchToEnglish() {
        LanguageManager.setLocale(Locale.ENGLISH);
        refreshLanguage();
    }

    private void refreshLanguage() {
        resources = LanguageManager.getBundle();
        updateUILabels();
        updatePrompts();
        refreshTableColumns();
        clearForm();
    }

    private void updateUILabels() {
        if (resources == null) return;

        colID.setText(resources.getString("aktivitetet.col.id"));
        colEmri.setText(resources.getString("aktivitetet.col.emri"));
        colPershkrimi.setText(resources.getString("aktivitetet.col.pershkrimi"));
        colData.setText(resources.getString("aktivitetet.col.data"));
        colGrupiID.setText(resources.getString("aktivitetet.col.grupiid"));

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

    private void refreshTableColumns() {
        tableAktivitetet.refresh();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
