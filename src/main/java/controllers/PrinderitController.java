package controllers;

import services.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.Prinderit;
import models.Dto.Prinderit.CreatePrinderitDto;
import models.Dto.Prinderit.UpdatePrinderitDto;
import services.PrinderitService;

import java.util.Optional;

public class PrinderitController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtKontakti;

    @FXML
    private TextField txtAdresa;

    @FXML
    private TableView<Prinderit> tablePrinderit;

    @FXML
    private TableColumn<Prinderit, Integer> colID;

    @FXML
    private TableColumn<Prinderit, String> colEmri;

    @FXML
    private TableColumn<Prinderit, String> colMbiemri;

    @FXML
    private TableColumn<Prinderit, String> colKontakti;

    @FXML
    private TableColumn<Prinderit, String> colAdresa;

    private final PrinderitService service = new PrinderitService();

    private final ObservableList<Prinderit> prinderitList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("prindiID"));
        colEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        colMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        colKontakti.setCellValueFactory(new PropertyValueFactory<>("telefoni"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        loadPrinderit();
        tablePrinderit.setItems(prinderitList);

        tablePrinderit.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                populateFields(newSel);
            }
        });
    }


    private void loadPrinderit() {
        prinderitList.clear();
        try {
            prinderitList.addAll(service.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk u ngarkuan të dhënat.");
        }
    }

    private void populateFields(Prinderit prindi) {
        txtEmri.setText(prindi.getEmri());
        txtMbiemri.setText(prindi.getMbiemri());
        txtKontakti.setText(prindi.getTelefoni());
        txtAdresa.setText(prindi.getAdresa());
    }

    @FXML
    private void handleShto() {
        try {
            validateInputForCreate();

            CreatePrinderitDto dto = new CreatePrinderitDto(
                    txtEmri.getText().trim(),
                    txtMbiemri.getText().trim(),
                    txtKontakti.getText().trim(),
                    "", // Email nuk e ke në view, e le bosh ose mund ta shtosh
                    txtAdresa.getText().trim(),
                    "NukDihet" // LlojiLidhjes nuk e ke në view, mund ta bësh fushë input
            );

            Prinderit prindi = service.create(dto);
            prinderitList.add(prindi);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Prindi u shtua me sukses.");
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Gabim", ex.getMessage());
        }
    }

    @FXML
    private void handleLargo() {
        Prinderit selected = tablePrinderit.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidhni një prind për ta larguar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmimi");
        confirm.setHeaderText("Jeni të sigurt që doni ta largoni prindin?");
        confirm.setContentText(selected.getEmri() + " " + selected.getMbiemri());

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = service.delete(selected.getPrindiID());
            if (deleted) {
                prinderitList.remove(selected);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Prindi u largua me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk u largua prindi.");
            }
        }
    }

    @FXML
    private void handlePerditeso() {
        Prinderit selected = tablePrinderit.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Vërejtje", "Zgjidhni një prind për përditësim.");
            return;
        }

        try {
            validateInputForUpdate();

            UpdatePrinderitDto dto = new UpdatePrinderitDto(
                    selected.getPrindiID(),
                    txtEmri.getText().trim(),
                    txtMbiemri.getText().trim(),
                    txtKontakti.getText().trim(),
                    "",  // Email nuk e ke në input
                    txtAdresa.getText().trim(),
                    "NukDihet"  // LlojiLidhjes
            );

            Prinderit updated = service.update(dto);
            if (updated != null) {
                int index = prinderitList.indexOf(selected);
                prinderitList.set(index, updated);
                tablePrinderit.getSelectionModel().select(updated);
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Prindi u përditësua me sukses.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Gabim", "Nuk u përditësua prindi.");
            }
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Gabim", ex.getMessage());
        }
    }

    private void clearFields() {
        txtEmri.clear();
        txtMbiemri.clear();
        txtKontakti.clear();
        txtAdresa.clear();
        tablePrinderit.getSelectionModel().clearSelection();
    }

    private void validateInputForCreate() throws Exception {
        if (txtEmri.getText().trim().isEmpty() ||
                txtMbiemri.getText().trim().isEmpty() ||
                txtKontakti.getText().trim().isEmpty()) {
            throw new Exception("Emri, Mbiemri dhe Kontakti janë të detyrueshme.");
        }
    }

    private void validateInputForUpdate() throws Exception {
        validateInputForCreate();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
