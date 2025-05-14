package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML
    private void goToOrari(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/OrariView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
