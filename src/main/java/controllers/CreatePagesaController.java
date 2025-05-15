package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePagesaController {
    @FXML
    private TextField pagesaIDTextField;

    @FXML
    private TextField shumaTextField;

    @FXML
    private TextArea pershkrimiTextArea;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private Button ruajButton;

    @FXML
    private void ruajPagesen(){
        String pagesaID = pagesaIDTextField.getText();
        String shuma = shumaTextField.getText();
        String pershkrimi = pershkrimiTextArea.getText();
        String data = dataDatePicker.getValue().toString();

        System.out.println("ID: " + pagesaID);
        System.out.println("Shuma: " + shuma);
        System.out.println("Pershkrimi: " + pershkrimi);
        System.out.println("Data: " + data);
    }

    @FXML
    private void goToFinancat(ActionEvent ae) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/FinancatView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
