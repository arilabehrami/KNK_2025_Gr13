package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Prania;
import services.PraniaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PraniaController {

    @FXML
    private Button btnLanguage;
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private TextField txtFemijaId;
    @FXML private TextField txtStatusi;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Prania> tableView;
    @FXML private TableColumn<Prania, Integer> colId;
    @FXML private TableColumn<Prania, Integer> colFemija;
    @FXML private TableColumn<Prania, LocalDate> colData;
    @FXML private TableColumn<Prania, String> colStatusi;

    private Locale currentLocale = new Locale("sq");
    private PraniaService praniaService = new PraniaService();

    private ObservableList<Prania> dataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        btnLanguage.setOnAction(e -> switchLanguage());
        btnSave.setOnAction(e -> handleSave());
        btnDelete.setOnAction(e -> handleDelete());

        colId.setCellValueFactory(new PropertyValueFactory<>("praniaId"));
        colFemija.setCellValueFactory(new PropertyValueFactory<>("femijaId"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colStatusi.setCellValueFactory(new PropertyValueFactory<>("statusi"));

        tableView.setItems(dataList);

        loadData();
    }

    private void switchLanguage() {
        if (currentLocale.getLanguage().equals("sq")) {
            currentLocale = new Locale("en");
        } else {
            currentLocale = new Locale("sq");
        }

        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", currentLocale);

        btnLanguage.setText(bundle.getString("language"));
        txtFemijaId.setPromptText(bundle.getString("femijaId"));
        txtStatusi.setPromptText(bundle.getString("status"));
        btnSave.setText(bundle.getString("save"));
        btnDelete.setText(bundle.getString("delete"));
        colFemija.setText(bundle.getString("femijaId"));
        colStatusi.setText(bundle.getString("status"));
    }

    @FXML
    public void handleSave() {
        try {
            int femijaId = Integer.parseInt(txtFemijaId.getText());
            String statusi = txtStatusi.getText();
            LocalDate data = datePicker.getValue();

            Prania prania = new Prania();
            prania.setFemijaId(femijaId);
            prania.setStatusi(statusi);
            prania.setData(data);

            praniaService.save(prania);

            loadData();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "U ruajt me sukses!");
            alert.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gabim gjatë ruajtjes: " + e.getMessage());
            alert.show();
        }
    }

    private void loadData() {
        try {
            List<Prania> listaPrania = praniaService.getAll();
            dataList.setAll(listaPrania);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gabim gjatë ngarkimit të të dhënave: " + e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void handleDelete() {
        Prania selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ju lutem zgjidhni një rresht për të fshirë.");
            alert.show();
            return;
        }

        praniaService.delete(selected.getPraniaId());

        dataList.remove(selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "U fshi me sukses!");
        alert.show();
    }
}
