package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Dto.Pagesa.CreatePagesaDto;
import models.domain.Pagesa;
import services.PagesaService;

import java.time.LocalDate;

public class CreatePagesaController {

    @FXML
    private TextField txtFemijaId;
    @FXML
    private TextField txtPershkrimi;
    @FXML
    private TextField txtShuma;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button ruajButton;

    private PagesaService pagesaService;

    public CreatePagesaController() {
        this.pagesaService = new PagesaService();
    }

    @FXML
    private void handleRuaj() {
        try {
            int femijaId = Integer.parseInt(txtFemijaId.getText().trim());
            double shuma = Double.parseDouble(txtShuma.getText().trim());
            LocalDate data = datePicker.getValue();
            String pershkrimi = txtPershkrimi.getText().trim();

            CreatePagesaDto dto = new CreatePagesaDto(femijaId, shuma, data, pershkrimi);
            Pagesa krijuar = pagesaService.create(dto);

            showAlert(AlertType.INFORMATION, "Sukses", "Pagesa u ruajt me sukses me ID: " + krijuar.getPagesaId());
            clearForm();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Gabim", "ID e fëmijës dhe shuma duhet të jenë numra.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Gabim", e.getMessage());
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearForm() {
        txtFemijaId.clear();
        txtPershkrimi.clear();
        txtShuma.clear();
        datePicker.setValue(null);
    }
}
