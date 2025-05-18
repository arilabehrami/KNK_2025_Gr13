package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Orari;
import models.Dto.Orari.CreateOrariDto;
import models.Dto.Orari.UpdateOrariDto;
import services.OrariService;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrariController {

    @FXML private TableView<Orari> tableOrari;
    @FXML private TableColumn<Orari, Integer> colOrariID;
    @FXML private TableColumn<Orari, Integer> colFemijaID;
    @FXML private TableColumn<Orari, String> colDita;
    @FXML private TableColumn<Orari, LocalTime> colOraHyrjes;
    @FXML private TableColumn<Orari, LocalTime> colOraDaljes;

    @FXML private TextField tfFemijaID;
    @FXML private TextField tfDita;
    @FXML private TextField tfOraHyrjes;
    @FXML private TextField tfOraDaljes;

    @FXML private Label lblFemijaID;
    @FXML private Label lblDita;
    @FXML private Label lblOraHyrjes;
    @FXML private Label lblOraDaljes;

    @FXML private Button btnAdd, btnUpdate, btnDelete, btnClear;

    private OrariService service;
    private ObservableList<Orari> orariObservableList;
    private ResourceBundle resources;

    public void setService(OrariService service) {
        this.service = service;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
        updateUILanguage();
        loadData();
    }

    @FXML
    public void initialize() {
        colOrariID.setCellValueFactory(new PropertyValueFactory<>("orariID"));
        colFemijaID.setCellValueFactory(new PropertyValueFactory<>("femijaID"));
        colDita.setCellValueFactory(new PropertyValueFactory<>("dita"));
        colOraHyrjes.setCellValueFactory(new PropertyValueFactory<>("oraHyrjes"));
        colOraDaljes.setCellValueFactory(new PropertyValueFactory<>("oraDaljes"));
        tableOrari.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> fillForm(newSelection));
    }

    private void loadData() {
        if (service == null) return;
        try {
            List<Orari> list = service.getAllOrari();
            orariObservableList = FXCollections.observableArrayList(list);
            tableOrari.setItems(orariObservableList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, getMessage("orari.error.load", "Error loading data!"));
            e.printStackTrace();
        }
    }

    private void fillForm(Orari o) {
        if (o == null) {
            clearForm();
            return;
        }
        tfFemijaID.setText(String.valueOf(o.getFemijaID()));
        tfDita.setText(o.getDita());
        tfOraHyrjes.setText(o.getOraHyrjes() != null ? o.getOraHyrjes().toString() : "");
        tfOraDaljes.setText(o.getOraDaljes() != null ? o.getOraDaljes().toString() : "");
    }

    @FXML
    private void addOrari() {
        String oraHyrjesString = tfOraHyrjes.getText().trim();
        String oraDaljesString = tfOraDaljes.getText().trim();

        // Vetëm validim i formatit HH:mm
        if (!oraHyrjesString.matches("^\\d{1,2}:\\d{2}$")) {
            showAlert(Alert.AlertType.ERROR, "Ora hyrëse duhet të jetë në formatin HH:mm, p.sh. 12:00");
            return;
        }
        if (!oraDaljesString.matches("^\\d{1,2}:\\d{2}$")) {
            showAlert(Alert.AlertType.ERROR, "Ora dalëse duhet të jetë në formatin HH:mm, p.sh. 12:00");
            return;
        }

        try {
            CreateOrariDto dto = new CreateOrariDto(
                    Integer.parseInt(tfFemijaID.getText()),
                    tfDita.getText(),
                    oraHyrjesString,
                    oraDaljesString
            );
            service.addOrari(dto);
            loadData();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Orari u shtua!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Gabim gjatë shtimit!");
            e.printStackTrace();
        }
    }


    @FXML
    private void updateOrari() {
        Orari selected = tableOrari.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, getMessage("orari.warning.select", "Zgjidh një rresht!"));
            return;
        }
        try {
            UpdateOrariDto dto = new UpdateOrariDto(
                    selected.getOrariID(),
                    Integer.parseInt(tfFemijaID.getText()),
                    tfDita.getText(),
                    tfOraHyrjes.getText(),
                    tfOraDaljes.getText()
            );
            service.updateOrari(dto);
            loadData();
            showAlert(Alert.AlertType.INFORMATION, getMessage("orari.info.updated", "Orari u përditësua!"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, getMessage("orari.error.update", "Gabim gjatë përditësimit!"));
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteOrari() {
        Orari selected = tableOrari.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, getMessage("orari.warning.select", "Zgjidh një rresht!"));
            return;
        }
        try {
            service.deleteOrari(selected.getOrariID());
            loadData();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, getMessage("orari.info.deleted", "Orari u fshi!"));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, getMessage("orari.error.delete", "Gabim gjatë fshirjes!"));
            e.printStackTrace();
        }
    }

    @FXML
    private void clearForm() {
        tfFemijaID.clear();
        tfDita.clear();
        tfOraHyrjes.clear();
        tfOraDaljes.clear();
        tableOrari.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(getMessage("orari.title.alert", "Alert"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String getMessage(String key, String defaultMsg) {
        return (resources != null && resources.containsKey(key)) ? resources.getString(key) : defaultMsg;
    }

    private void updateUILanguage() {
        if (resources == null) return;

        colOrariID.setText(resources.getString("orari.id"));
        colFemijaID.setText(resources.getString("orari.femijaID"));
        colDita.setText(resources.getString("orari.dita"));
        colOraHyrjes.setText(resources.getString("orari.oraHyrjes"));
        colOraDaljes.setText(resources.getString("orari.oraDaljes"));

        lblFemijaID.setText(resources.getString("orari.lblFemijaID"));
        lblDita.setText(resources.getString("orari.lblDita"));
        lblOraHyrjes.setText(resources.getString("orari.lblOraHyrjes"));
        lblOraDaljes.setText(resources.getString("orari.lblOraDaljes"));

        btnAdd.setText(resources.getString("button.add"));
        btnUpdate.setText(resources.getString("button.update"));
        btnDelete.setText(resources.getString("button.delete"));
        btnClear.setText(resources.getString("button.clear"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
