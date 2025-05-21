package controllers;

import Database.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserSession;
import utils.PasswordUtils;
import utils.SceneLocator;
import services.SceneManager;
import services.LanguageManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    @FXML private Button signupButton;
    @FXML private Button switchLangButton;


    @FXML
    private ResourceBundle resources;

    @FXML
    public void initialize() {
        // Stilizimi i butonave me hover
        styleButtons();

        errorLabel.setText("");
        errorLabel.setVisible(false);

        // Vendos tekstet sipas gjuhës aktuale nga resource bundle
        loginButton.setText(resources.getString("button.login"));
        signupButton.setText(resources.getString("button.signup"));
        switchLangButton.setText(resources.getString("button.switch_language"));

    }

    private void styleButtons() {
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #f4511e; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 12; -fx-padding: 10 25;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #ff7043; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 12; -fx-padding: 10 25;"));

        signupButton.setOnMouseEntered(e -> signupButton.setStyle(
                "-fx-background-color: #ffccbc; -fx-border-color: #ff7043; -fx-text-fill: #ff7043; " +
                        "-fx-border-radius: 12; -fx-padding: 10 25;"));
        signupButton.setOnMouseExited(e -> signupButton.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #ff7043; -fx-text-fill: #ff7043; " +
                        "-fx-border-radius: 12; -fx-padding: 10 25;"));
    }

    @FXML
    private void goToSignUp(ActionEvent event) {
        SceneManager.changeScene(SceneLocator.SIGNUP_VIEW);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError(resources.getString("error.fill_all_fields"));
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id, username FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, PasswordUtils.hashPassword(password));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String user = rs.getString("username");

                UserSession.init(userId, user);

                ResourceBundle currentBundle = LanguageManager.getBundle();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"), currentBundle);
                Parent root = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();

                // Merr madhësinë ekzistuese të dritares
                double width = stage.getWidth();
                double height = stage.getHeight();

                Scene scene = new Scene(root, width, height);

                stage.setScene(scene);
                stage.setTitle(currentBundle.getString("title.main"));

                // E vendos dritaren në maximized ose full screen sipas dëshirës
                stage.setMaximized(true);
                // ose stage.setFullScreen(true);

                stage.show();

            } else {
                showError(resources.getString("error.invalid_credentials"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError(resources.getString("error.database_error"));
        }
    }



    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    @FXML
    private void switchLanguage(ActionEvent event) {
        LanguageManager.toggleLanguage();
        SceneManager.changeScene(SceneLocator.LOGIN_VIEW); // rifreskon skenën me gjuhën e re
    }

    @FXML
    public void emailHoverOff(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setStyle("-fx-underline: false; -fx-text-fill: #6d4c41;");
    }






    @FXML
    public void forgotPassword(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resources.getString("forgot_password.title"));  // titulli i dritares (p.sh. "Forgot Password")
        alert.setHeaderText(null);
        alert.setContentText(resources.getString("forgot_password.instructions"));
        alert.showAndWait();
    }



}
