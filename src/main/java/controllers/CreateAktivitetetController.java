package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateAktivitetetController {

    @FXML
    private TextField emriAktiviteteriTextField;

    @FXML
    private TextArea pershkrimiTextArea;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private ComboBox<String> grupiComboBox;

    @FXML
    private Button shtoButton;

    @FXML
    private void shtoAktivitet() {
        String emriAktiviteteri = emriAktiviteteriTextField.getText();
        String pershkrimi = pershkrimiTextArea.getText();
        String data = dataDatePicker.getValue().toString();
        String grupiID = grupiComboBox.getValue();


        System.out.println("Emri: " + emriAktiviteteri);
        System.out.println("Përshkrimi: " + pershkrimi);
        System.out.println("Data: " + data);
        System.out.println("Grupi ID: " + grupiID);

    }

    @FXML
    private void initialize() {
        grupiComboBox.getItems().addAll("Grupi 1", "Grupi 2", "Grupi 3");
    }
}
