package controllers;


import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    public void initialize() {
        // Hover effect për loginButton
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #f4511e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 10 25;"));

        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #ff7043; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 10 25;"));

        // Hover effect për signupButton
        signupButton.setOnMouseEntered(e -> signupButton.setStyle(
                "-fx-background-color: #ffccbc; -fx-border-color: #ff7043; -fx-text-fill: #ff7043; -fx-border-radius: 12; -fx-padding: 10 25;"));

        signupButton.setOnMouseExited(e -> signupButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #ff7043; -fx-text-fill: #ff7043; -fx-border-radius: 12; -fx-padding: 10 25;"));
    }



    @FXML
    private void goToSignUp(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SignUpView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Regjistrimi");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Plotësoni të gjitha fushat!");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loadMainView(username);
            } else {
                showError("Përdoruesi ose fjalëkalimi gabim!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Gabim me databazën!");
        }
    }

    private void loadMainView(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
            Parent root = loader.load();

            // Dergo username ne MainController
            MainController controller = loader.getController();
            controller.setUsername(username);
            controller.setPrimaryStage((Stage) usernameField.getScene().getWindow());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Paneli Kryesor");
            stage.setMaximized(true);
            stage.show();

            // Mbyll login window
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void forgotPassword(ActionEvent actionEvent) {
    }
}