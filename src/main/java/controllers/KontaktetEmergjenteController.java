package controllers;

import helpers.LanguageContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Dto.KontatetEmergjente.CreateKontaktetEmergjenteDto;
import models.domain.KontaktiEmergjent;
import services.KontaktiEmergjentService;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class KontaktetEmergjenteController {

    @FXML private ComboBox<String> languageSelector;
    @FXML private TextField femijaIdField, emriField, mbiemriField, telefoniField;
    @FXML private Label statusLabel;

    private final KontaktiEmergjentService service = new KontaktiEmergjentService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("Shqip", "English");
        languageSelector.setValue(LanguageContext.currentLocale.getLanguage().equals("en") ? "English" : "Shqip");
    }

    @FXML
    public void onShtoKontakti() {
        try {
            int femijaId = Integer.parseInt(femijaIdField.getText());
            String emri = emriField.getText();
            String mbiemri = mbiemriField.getText();
            String telefoni = telefoniField.getText();

            var dto = new CreateKontaktetEmergjenteDto(femijaId, emri, mbiemri, telefoni);
            KontaktiEmergjent kontakti = service.create(dto);

            statusLabel.setText("✔ " + kontakti.getEmri() + " " + kontakti.getMbiemri());
        } catch (Exception e) {
            statusLabel.setText("❌ " + e.getMessage());
        }
    }

    @FXML
    private void onLanguageChange() {
        String selected = languageSelector.getValue();
        Locale locale = selected.equals("English") ? new Locale("en") : new Locale("sq");
        LanguageContext.currentLocale = locale;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/KontaktetEmergjenteView.fxml"), bundle);
            Parent root = loader.load();
            Stage stage = (Stage) languageSelector.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}