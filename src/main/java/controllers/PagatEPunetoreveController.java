package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.domain.PagatEPunetoreve;
import models.Dto.PagatEPunetoreve.UpdatePagatEPunetoreveDto;
import repository.PagatEPunetoreveRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class PagatEPunetoreveController {

    @FXML private TableView<PagatEPunetoreve> tableView;
    @FXML private TableColumn<PagatEPunetoreve, Integer> idColumn;
    @FXML private TableColumn<PagatEPunetoreve, String> muajiColumn;
    @FXML private TableColumn<PagatEPunetoreve, Integer> vitiColumn;
    @FXML private TableColumn<PagatEPunetoreve, Double> shumaColumn;
    @FXML private TableColumn<PagatEPunetoreve, String> dataColumn;

    @FXML private TextField idTextField;
    @FXML private Button kerkoButton;
    @FXML private Button fshijButton;
    @FXML private Button ruajButton;

    private PagatEPunetoreveRepository repository;
    private ObservableList<PagatEPunetoreve> pagatList;

    @FXML
    public void initialize(){
        repository = new PagatEPunetoreveRepository();

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPagaID()).asObject());
        muajiColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMuaji()));
        vitiColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getViti()).asObject());
        shumaColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getShumaPaga()).asObject());
        dataColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData()));


        loadTableData();
    }

    private void loadTableData(){
        pagatList = FXCollections.observableArrayList(repository.getAll());
        tableView.setItems(pagatList);
    }

    @FXML
    public void handleKerko(){
        String idText = idTextField.getText();
        if(idText == null || idText.isBlank()){
            showAlert("Gabim", "Ju lutem shkruani një ID valide për të kërkuar.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            PagatEPunetoreve paga = repository.getById(id);
            if(paga != null){
                pagatList.clear();
                pagatList.add(paga);
            } else {
                showAlert("Nuk u gjet", "Nuk u gjet asnjë pagë me ID: " + id);
            }
        } catch (NumberFormatException e){
            showAlert("Gabim", "ID duhet të jetë numër i plotë.");
        }
    }

    @FXML
    public void handleFshij(){
        PagatEPunetoreve selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null){
            showAlert("Gabim", "Ju lutem zgjidhni një rresht për të fshirë.");
            return;
        }

        boolean success = repository.delete(selected.getPagaID());
        if(success){
            pagatList.remove(selected);
        } else {
            showAlert("Gabim", "Fshirja dështoi.");
        }
    }

    @FXML
    public void handleRuaj(){
        PagatEPunetoreve selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null){
            showAlert("Gabim", "Ju lutem zgjidhni një rresht për të ruajtur ndryshimet.");
            return;
        }

        UpdatePagatEPunetoreveDto updateDto = new UpdatePagatEPunetoreveDto(
                selected.getPagaID(),
                selected.getEdukatoriID(),
                selected.getMuaji(),
                selected.getViti(),
                selected.getShumaPaga(),
                selected.getData()
        );

        PagatEPunetoreve updated = repository.update(updateDto);
        if(updated != null){
            int index = pagatList.indexOf(selected);
            pagatList.set(index, updated);
        } else {
            showAlert("Gabim", "Ruajtja dështoi.");
        }
    }

    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
