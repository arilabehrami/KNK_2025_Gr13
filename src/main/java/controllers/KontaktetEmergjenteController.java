package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.domain.KontaktiEmergjent;
import models.Dto.KontatetEmergjente.CreateKontaktetEmergjenteDto;
import models.Dto.KontatetEmergjente.UpdateKontaktetEmergjenteDto;
import services.KontaktiEmergjentService;
import services.LanguageManager;

import java.util.ResourceBundle;

public class KontaktetEmergjenteController extends BaseController {

    @FXML private TextField femijaIdField;
    @FXML private TextField emriField;
    @FXML private TextField mbiemriField;
    @FXML private TextField telefoniField;

    @FXML private Button shtoButton, perditesoButton, fshijButton, pastroButton;
    @FXML private Label statusLabel;

    @FXML private TableView<KontaktiEmergjent> kontaktetTable;
    @FXML private TableColumn<KontaktiEmergjent, Integer> idColumn, femijaIdColumn;
    @FXML private TableColumn<KontaktiEmergjent, String> emriColumn, mbiemriColumn, telefoniColumn;

    private final KontaktiEmergjentService service = new KontaktiEmergjentService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("kontaktiID"));
        femijaIdColumn.setCellValueFactory(new PropertyValueFactory<>("femijaID"));
        emriColumn.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemriColumn.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        telefoniColumn.setCellValueFactory(new PropertyValueFactory<>("telefoni"));

        kontaktetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                femijaIdField.setText(String.valueOf(newVal.getFemijaID()));
                emriField.setText(newVal.getEmri());
                mbiemriField.setText(newVal.getMbiemri());
                telefoniField.setText(newVal.getTelefoni());
            }
        });

        ngarkoKontaktet();
        refreshLanguage();
    }

    @Override
    protected void refreshLanguage() {
        resources = LanguageManager.getBundle();

        femijaIdField.setPromptText(resources.getString("label.femijaid"));
        emriField.setPromptText(resources.getString("label.emri"));
        mbiemriField.setPromptText(resources.getString("label.mbiemri"));
        telefoniField.setPromptText(resources.getString("label.telefoni"));

        idColumn.setText(resources.getString("table.id"));
        femijaIdColumn.setText(resources.getString("label.femijaid"));
        emriColumn.setText(resources.getString("label.emri"));
        mbiemriColumn.setText(resources.getString("label.mbiemri"));
        telefoniColumn.setText(resources.getString("label.telefoni"));

        shtoButton.setText(resources.getString("button.shtokontakt"));
        perditesoButton.setText(resources.getString("button.update_note"));
        fshijButton.setText(resources.getString("button.delete_note"));
        pastroButton.setText(resources.getString("button.clear"));
    }

    @FXML
    private void onShtoKontakti() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            var dto = new CreateKontaktetEmergjenteDto(femijaId, emriField.getText(), mbiemriField.getText(), telefoniField.getText());
            service.create(dto);
            statusLabel.setText(resources.getString("message.shtuar_sukses"));
            ngarkoKontaktet();
            clearFields();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onPerditesoKontakti() {
        var selected = kontaktetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        try {
            var dto = new UpdateKontaktetEmergjenteDto(
                    selected.getKontaktiID(),
                    emriField.getText(),
                    mbiemriField.getText(),
                    telefoniField.getText()
            );
            service.update(dto);
            statusLabel.setText(resources.getString("message.perditesuar_sukses"));
            ngarkoKontaktet();
            clearFields();
        } catch (Exception e) {
            statusLabel.setText(resources.getString("message.gabim") + ": " + e.getMessage());
        }
    }

    @FXML
    private void onFshijKontakti() {
        var selected = kontaktetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText(resources.getString("message.zgjidh_rresht"));
            return;
        }

        boolean deleted = service.delete(selected.getKontaktiID());
        if (deleted) {
            statusLabel.setText(resources.getString("message.fshire_sukses"));
            ngarkoKontaktet();
        } else {
            statusLabel.setText(resources.getString("message.fshirja_deshtoi"));
        }
    }

    @FXML
    private void onPastroFushat() {
        clearFields();
        kontaktetTable.getSelectionModel().clearSelection();
    }

    private void clearFields() {
        femijaIdField.clear();
        emriField.clear();
        mbiemriField.clear();
        telefoniField.clear();
    }

    private void ngarkoKontaktet() {
        kontaktetTable.getItems().setAll(service.getAll());
    }
}