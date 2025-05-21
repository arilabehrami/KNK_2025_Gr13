package controllers;

import Database.DBConnection;
import services.UserSession;
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

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    @FXML private Button signupButton;

    @FXML
    public void initialize() {
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #f4511e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 10 25;"));

        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #ff7043; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 10 25;"));

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
            String query = "SELECT id, username FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String user = rs.getString("username");

                // Inicioni sesionin e përdoruesit
                UserSession.init(userId, user);

                loadMainView();
            } else {
                showError("Përdoruesi ose fjalëkalimi gabim!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Gabim me databazën!");
        }
    }

    private void loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Paneli Kryesor");
            stage.setMaximized(true);
            stage.show();

            // Mbyll dritaren e login-it
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
        // Mund të shtosh logjikën këtu për dërgimin e emailit ose rikuperimin e fjalëkalimit.
    }

    @FXML
    public void emailHoverOff(javafx.scene.input.MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setStyle("-fx-underline: false; -fx-text-fill: #6d4c41;");
    }
}
