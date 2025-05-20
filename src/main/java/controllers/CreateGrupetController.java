package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.Grupet.CreateGrupetDto;
import models.Dto.Grupet.UpdateGrupetDto;
import models.domain.Grupet;
import services.GrupetService;

public class CreateGrupetController {

    @FXML private TextField txtEmriGrupit;
    @FXML private TextField txtMoshaMin;
    @FXML private TextField txtMoshaMax;
    @FXML private TextField txtEdukatoriId;

    @FXML private TableView<Grupet> tableGrupet;
    @FXML private TableColumn<Grupet, Integer> colGrupiId;
    @FXML private TableColumn<Grupet, String> colEmriGrupit;
    @FXML private TableColumn<Grupet, Integer> colMoshaMin;
    @FXML private TableColumn<Grupet, Integer> colMoshaMax;
    @FXML private TableColumn<Grupet, Integer> colEdukatoriId;

    private final GrupetService service = new GrupetService();
    private final ObservableList<Grupet> grupetList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colGrupiId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getGrupiId()).asObject());
        colEmriGrupit.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmriGrupit()));
        colMoshaMin.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getMoshaMin()).asObject());
        colMoshaMax.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getMoshaMax()).asObject());
        colEdukatoriId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getEdukatoriId()).asObject());

        tableGrupet.setItems(grupetList);
        tableGrupet.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> populateFields(newVal));

        loadGrupet();
    }

    private void loadGrupet() {
        grupetList.clear();
        try {
            grupetList.addAll(service.getAll());
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void populateFields(Grupet grupi) {
        if (grupi != null) {
            txtEmriGrupit.setText(grupi.getEmriGrupit());
            txtMoshaMin.setText(String.valueOf(grupi.getMoshaMin()));
            txtMoshaMax.setText(String.valueOf(grupi.getMoshaMax()));
            txtEdukatoriId.setText(String.valueOf(grupi.getEdukatoriId()));
        }
    }

    @FXML
    public void handleShto() {
        try {
            CreateGrupetDto dto = new CreateGrupetDto(
                    txtEmriGrupit.getText(),
                    Integer.parseInt(txtMoshaMin.getText()),
                    Integer.parseInt(txtMoshaMax.getText()),
                    Integer.parseInt(txtEdukatoriId.getText())
            );
            Grupet grupi = service.create(dto);
            grupetList.add(grupi);
            clearFields();
        } catch (NumberFormatException e) {
            showError("Sigurohu që mosha dhe edukatori ID janë numra.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handlePerditeso() {
        Grupet selected = tableGrupet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një grup për përditësim.");
            return;
        }

        try {
            UpdateGrupetDto dto = new UpdateGrupetDto(
                    selected.getGrupiId(),
                    txtEmriGrupit.getText(),
                    Integer.parseInt(txtMoshaMin.getText()),
                    Integer.parseInt(txtMoshaMax.getText()),
                    Integer.parseInt(txtEdukatoriId.getText())
            );
            Grupet updated = service.update(dto);
            int index = grupetList.indexOf(selected);
            grupetList.set(index, updated);
            tableGrupet.refresh();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Sigurohu që mosha dhe edukatori ID janë numra.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleFshi() {
        Grupet selected = tableGrupet.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Zgjedh një grup për fshirje.");
            return;
        }

        try {
            service.delete(selected.getGrupiId());
            grupetList.remove(selected);
            clearFields();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearFields() {
        txtEmriGrupit.clear();
        txtMoshaMin.clear();
        txtMoshaMax.clear();
        txtEdukatoriId.clear();
        tableGrupet.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
