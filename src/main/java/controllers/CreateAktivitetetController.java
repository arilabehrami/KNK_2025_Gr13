package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.AktivitetetService;
import models.Dto.Aktivitetet.CreateAktivitetetDto;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateAktivitetetController {
    @FXML private TextField emriField;
    @FXML private TextArea pershkrimiArea;
    @FXML private DatePicker dataPicker;
    @FXML private TextField grupiField;
    @FXML private Button languageButton;

    private final AktivitetetService service = new AktivitetetService();
    private boolean isAlbanian = true;

    @FXML
    private void onCreate() {
        try {
            CreateAktivitetetDto dto = new CreateAktivitetetDto(
                    emriField.getText(),
                    pershkrimiArea.getText(),
                    dataPicker.getValue().toString(),
                    Integer.parseInt(grupiField.getText())
            );
            service.create(dto);
            showAlert("Sukses!", "Aktiviteti u shtua me sukses.");
        } catch (Exception e) {
            showAlert("Gabim", e.getMessage());
        }
    }

    @FXML
    private void toggleLanguage() {
        isAlbanian = !isAlbanian;
        Locale locale = new Locale(isAlbanian ? "sq" : "en");
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AktivitetetView.fxml"), bundle);
            Parent root = loader.load();

            // Merr controller-in e ri dhe kalo gjuhën
            CreateAktivitetetController controller = loader.getController();
            controller.setLanguage(isAlbanian);

            Stage stage = (Stage) languageButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Gabim", "Nuk u arrit të ndërrohet gjuha.");
        }
    }

    public void setLanguage(boolean isAlbanian) {
        this.isAlbanian = isAlbanian;
    }


    private void updateTexts(ResourceBundle bundle) {
        languageButton.setText(bundle.getString("button.language"));
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
