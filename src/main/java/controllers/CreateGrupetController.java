package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Dto.Grupet.CreateGrupetDto;
import models.domain.Grupet;
import services.GrupetService;

public class CreateGrupetController {
    @FXML
    private TextField emriGrupitField;
    @FXML
    private TextField moshaMinField;
    @FXML
    private TextField moshaMaxField;
    @FXML
    private TextField edukatoriIdField;

    private final GrupetService grupetService = new GrupetService();

    @FXML
    public void ruajGrupin() {
        try {
            String emri = emriGrupitField.getText();
            int moshaMin = Integer.parseInt(moshaMinField.getText());
            int moshaMax = Integer.parseInt(moshaMaxField.getText());
            int edukatoriId = Integer.parseInt(edukatoriIdField.getText());

            CreateGrupetDto dto = new CreateGrupetDto(emri, moshaMin, moshaMax, edukatoriId);

            Grupet grupi = grupetService.create(dto);

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Grupi u shtua me ID: " + grupi.getGrupiId());
            pastroFushat();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Ju lutem sigurohuni që fushat e moshës dhe edukatorit të jenë numra.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Gabim", e.getMessage());
        }
    }

    @FXML
    public void pastroFushat() {
        emriGrupitField.clear();
        moshaMinField.clear();
        moshaMaxField.clear();
        edukatoriIdField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
