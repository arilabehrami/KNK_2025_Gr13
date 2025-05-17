package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.Orari.UpdateOrariDto;
import models.domain.Orari;
import services.OrariService;
import helpers.LanguageContext;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrariController {

    @FXML private MenuItem switchLanguageItem;
    @FXML private Menu fileMenu, editMenu, helpMenu, languageMenu;
    @FXML private TableColumn<Orari, String> ditaColumn;
    @FXML private TableColumn<Orari, Time> oraHyrjesColumn;
    @FXML private TableColumn<Orari, Time> oraDaljesColumn;
    @FXML private TableColumn<Orari, Integer> femijaIdColumn;
    @FXML private TableView<Orari> orariTable;
    @FXML private Button backButton;

    private OrariService orariService = new OrariService();

    @FXML
    public void initialize() {
        ditaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDita()));
        oraHyrjesColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getOraHyrjes()));
        oraDaljesColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getOraDaljes()));
        femijaIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getFemijaID()));

        try {
            List<Orari> oraret = orariService.getAll(); 
            ObservableList<Orari> observableList = FXCollections.observableArrayList(oraret);
            orariTable.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gabim gjatë ngarkimit të orarit!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onSwitchLanguage() {
        Locale newLocale = LanguageContext.currentLocale.getLanguage().equals("en") ? new Locale("sq") : new Locale("en");
        LanguageContext.currentLocale = newLocale;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", newLocale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"), bundle);
            Parent root = loader.load();
            Stage stage = (Stage) switchLanguageItem.getParentPopup().getOwnerWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(bundle.getString("label.title2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onSave() {
        try {
            ObservableList<Orari> updatedList = orariTable.getItems();
            for (Orari orari : updatedList) {
                UpdateOrariDto dto = new UpdateOrariDto(
                        orari.getOrariID(),
                        orari.getFemijaID(),
                        orari.getDita(),
                        orari.getOraHyrjes().toString(),
                        orari.getOraDaljes().toString()
                );
                orariService.update(dto);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Orari u ruajt me sukses!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gabim gjatë ruajtjes së orarit.");
            alert.showAndWait();
        }
    }


}
