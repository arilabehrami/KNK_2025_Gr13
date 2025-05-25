package controllers;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import services.SceneManager;
import utils.PasswordUtils;
import utils.SceneLocator;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleSignUp() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Ju lutem plotësoni të gjitha fushat!");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setVisible(true);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String hashedPassword = PasswordUtils.hashPassword(password);

            String insert = "INSERT INTO users(username, password) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();

            statusLabel.setText("Regjistrimi u krye me sukses!");
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            statusLabel.setVisible(true);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(this::goBackToLogin);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Ky emër përdoruesi mund të jetë i zënë!");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            statusLabel.setVisible(true);
        }
    }


    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        SceneManager.changeScene(SceneLocator.LOGIN_VIEW);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
    }

    @FXML
    private void goBackToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Kyçu");
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



