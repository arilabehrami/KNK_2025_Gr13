package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class CreatePraniaController {

    @FXML
    private ComboBox<String> femijaComboBox;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private ComboBox<String> statusiComboBox;
    @FXML
    private Button ruajButton;
    @FXML
    private Label statusLabel;

    public void initialize() {
        femijaComboBox.getItems().addAll("Femija 1", "Femija 2", "Femija 3");
        statusiComboBox.getItems().addAll("Aktiv", "Jo Aktiv");

        ruajButton.setOnAction(e -> ruajPranine());
    }

    private void ruajPranine() {
        String femijaId = femijaComboBox.getValue();
        String data = dataPicker.getValue().toString();
        String statusi = statusiComboBox.getValue();

        statusLabel.setText("Femija: " + femijaId + "\nData: " + data + "\nStatusi: " + statusi);

    }
}
