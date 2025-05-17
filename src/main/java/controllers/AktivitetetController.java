package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Aktivitetet;
import models.Dto.Aktivitetet.CreateAktivitetetDto;
import models.Dto.Aktivitetet.UpdateAktivitetetDto;
import services.AktivitetetService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AktivitetetController {

    @FXML
    private TableView<Aktivitetet> tableAktivitetet;
    @FXML private TableColumn<Aktivitetet, Integer> colID;
    @FXML private TableColumn<Aktivitetet, String> colEmri;
    @FXML private TableColumn<Aktivitetet, String> colPershkrimi;
    @FXML private TableColumn<Aktivitetet, LocalDate> colData;
    @FXML private TableColumn<Aktivitetet, Integer> colGrupiID;

    @FXML private TextField tfEmriAktivitetit;
    @FXML private TextArea taPershkrimi;
    @FXML private DatePicker dpData;
    @FXML private TextField tfGrupiID;

    @FXML private Button btnAdd, btnUpdate, btnDelete, btnClear;

    private AktivitetetService service;
    private ObservableList<Aktivitetet> aktivitetetObservableList;

    private ResourceBundle resources;

    public void setService(AktivitetetService service) {
        this.service = service;
        loadData();
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
        updateUILanguage();
    }

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("aktivitetiID"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emriAktivitetit"));
        colPershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colGrupiID.setCellValueFactory(new PropertyValueFactory<>("grupiID"));

        tableAktivitetet.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> fillForm(newSelection));
    }

    private void loadData() {
        try {
            List<Aktivitetet> list = service.getAllAktivitetet();
            aktivitetetObservableList = FXCollections.observableArrayList(list);
            tableAktivitetet.setItems(aktivitetetObservableList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("aktivitetet.error.load"));
            e.printStackTrace();
        }
    }

    private void fillForm(Aktivitetet a) {
        if (a == null) {
            clearForm();
            return;
        }
        tfEmriAktivitetit.setText(a.getEmriAktivitetit());
        taPershkrimi.setText(a.getPershkrimi());
        dpData.setValue(a.getData());
        tfGrupiID.setText(String.valueOf(a.getGrupiID()));
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
            service.addAktivitet(dto);
            loadData();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, resources.getString("aktivitetet.info.added"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("aktivitetet.error.add"));
            e.printStackTrace();
        }
    }

    @FXML
    private void updateAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, resources.getString("aktivitetet.warning.select"));
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
            service.updateAktivitet(dto);
            loadData();
            showAlert(Alert.AlertType.INFORMATION, resources.getString("aktivitetet.info.updated"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("aktivitetet.error.update"));
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAktivitet() {
        Aktivitetet selected = tableAktivitetet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, resources.getString("aktivitetet.warning.select"));
            return;
        }
        try {
            service.deleteAktivitet(selected.getAktivitetiID());
            loadData();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, resources.getString("aktivitetet.info.deleted"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, resources.getString("aktivitetet.error.delete"));
            e.printStackTrace();
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

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(resources.getString("aktivitetet.title.alert"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateUILanguage() {
        colID.setText(resources.getString("aktivitetet.col.id"));
        colEmri.setText(resources.getString("aktivitetet.col.emri"));
        colPershkrimi.setText(resources.getString("aktivitetet.col.pershkrimi"));
        colData.setText(resources.getString("aktivitetet.col.data"));
        colGrupiID.setText(resources.getString("aktivitetet.col.grupiid"));

        btnAdd.setText(resources.getString("aktivitetet.btn.add"));
        btnUpdate.setText(resources.getString("aktivitetet.btn.update"));
        btnDelete.setText(resources.getString("aktivitetet.btn.delete"));
        btnClear.setText(resources.getString("aktivitetet.btn.clear"));
    }
    @FXML
    private void switchToAlbanian() {
        changeLanguage(new Locale("sq"));
    }

    @FXML
    private void switchToEnglish() {
        changeLanguage(new Locale("en"));
    }

    private void changeLanguage(Locale locale) {
        try {
            ResourceBundle newBundle = ResourceBundle.getBundle("languages.messages", locale);
            setResources(newBundle);
            // Reload data or refrest UI as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
