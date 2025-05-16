package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.Donacionet.CreateDonacionetDto;
import repository.DonacionetRepository;

public class CreateDonacionetController {

    @FXML private TextField emriOrganizatesField;
    @FXML private ComboBox<String> llojiDonatoriCombo;
    @FXML private TextField kontaktiField;
    @FXML private TextField emailField;
    @FXML private TextField adresaField;
    @FXML private DatePicker dataDonacionitPicker;
    @FXML private TextField shumaField;
    @FXML private ComboBox<String> llojiDonacionitCombo;
    @FXML private TextArea pershkrimiArea;
    @FXML private Button ruajButton;
    @FXML private Button anuloButton;

    private final DonacionetRepository repository = new DonacionetRepository();

    @FXML
    public void initialize() {
        llojiDonatoriCombo.getItems().addAll("Organizate", "Qeveri", "Individ", "Biznes", "Tjeter");
        llojiDonacionitCombo.getItems().addAll("Financiar", "Material", "Sherbim");
    }

    @FXML
    private void ruajDonacionin() {
        try {
            // Validate required fields
            if (emriOrganizatesField.getText().isEmpty() ||
                    llojiDonatoriCombo.getValue() == null ||
                    kontaktiField.getText().isEmpty() ||
                    emailField.getText().isEmpty() ||
                    adresaField.getText().isEmpty() ||
                    dataDonacionitPicker.getValue() == null ||
                    shumaField.getText().isEmpty() ||
                    llojiDonacionitCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ju lutem plotësoni të gjitha fushat e detyrueshme!");
                alert.showAndWait();
                return;
            }

            // Validate email format
            String email = emailField.getText();
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email-i nuk është i vlefshëm!");
                alert.showAndWait();
                return;
            }

            // Validate shuma (must be a number)
            Double shuma;
            try {
                shuma = Double.parseDouble(shumaField.getText());
                if (shuma <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Shuma duhet të jetë një numër pozitiv!");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Shuma duhet të jetë një numër i vlefshëm!");
                alert.showAndWait();
                return;
            }

            // Create DTO and save to repository
            CreateDonacionetDto dto = new CreateDonacionetDto(
                    null,
                    emriOrganizatesField.getText(),
                    llojiDonatoriCombo.getValue(),
                    kontaktiField.getText(),
                    email,
                    adresaField.getText(),
                    dataDonacionitPicker.getValue().toString(),
                    shuma,
                    llojiDonacionitCombo.getValue(),
                    pershkrimiArea.getText()
            );

            repository.create(dto);

            // Show success message and clear fields
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Donacioni u ruajt me sukses!");
            alert.showAndWait();
            clearFields();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gabim gjatë ruajtjes së donacionit: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void anulo() {
        // Close the window
        Stage stage = (Stage) anuloButton.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        emriOrganizatesField.clear();
        llojiDonatoriCombo.setValue(null);
        kontaktiField.clear();
        emailField.clear();
        adresaField.clear();
        dataDonacionitPicker.setValue(null);
        shumaField.clear();
        llojiDonacionitCombo.setValue(null);
        pershkrimiArea.clear();
    }
}