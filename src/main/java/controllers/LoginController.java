package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("admin") && password.equals("1234")) {
            System.out.println("Login me sukses!");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AktivitetetView.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Aktivitetet");
//            stage.show();
        } else {
            System.out.println("Kyçja dështoi!");
        }
    }
}
