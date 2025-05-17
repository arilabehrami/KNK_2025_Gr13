package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import services.PagesaService;
import models.dto.Pagesa.CreatePagesaDto;
import models.domain.Pagesa;

import java.time.LocalDate;

public class CreatePagesaController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtFemijaId;

    @FXML
    private TextField txtShuma;

    @FXML
    private TextField txtPershkrimi;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button ruajButton;

    private PagesaService pagesaService;

    public CreatePagesaController() {
        this.pagesaService = new PagesaService();
    }

    @FXML
    public void initialize() {
        ruajButton.setOnAction(e -> handleRuaj());
    }

    @FXML
    private void handleRuaj() {
        try {
            int pagesaId = Integer.parseInt(txtId.getText());
            int femijaId = Integer.parseInt(txtFemijaId.getText());
            double shuma = Double.parseDouble(txtShuma.getText());
            String pershkrimi = txtPershkrimi.getText();
            LocalDate data = datePicker.getValue();

            if (pershkrimi == null || pershkrimi.isEmpty()) {
                throw new Exception("Përshkrimi nuk mund të jetë bosh.");
            }

            if (data == null) {
                throw new Exception("Ju lutem zgjidhni një datë.");
            }

            CreatePagesaDto dto = new CreatePagesaDto(
                    pagesaId,
                    femijaId,
                    shuma,
                    data.toString(),
                    pershkrimi
            );

            Pagesa pagesa = pagesaService.create(dto);
            showSuccess("Pagesa u ruajt me sukses!");

        } catch (NumberFormatException ex) {
            showError("ID, ID e fëmijës dhe Shuma duhet të jenë numra.");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
