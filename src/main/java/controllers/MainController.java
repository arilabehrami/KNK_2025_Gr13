package controllers;

import Database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utils.PasswordUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Label userLabel;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField usernameField; // duhet të jetë TextField, jo String

    @FXML
    private TextField passwordField;

    private Stage primaryStage;

    // Për të vendosur referencën e skenës kryesore
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    // Vendos përdoruesin në etiketën e sipërme
    public void setUsername(String username) {
        userLabel.setText("Përdoruesi: " + username);
    }

    // Thirret gjatë loginit
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Plotësoni të gjitha fushat!");
            return;
        }

        String hashedPassword = PasswordUtils.hashPassword(password);

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Ngarko MainView nëse login-i është i suksesshëm
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MainView.fxml"));
                Parent root = loader.load();

                // Merr controller-in dhe dërgo username-in
                MainController mainController = loader.getController();
                mainController.setUsername(username);
                mainController.setPrimaryStage(primaryStage);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Paneli Kryesor - Kopshti Modern");
                stage.setMaximized(true);
                stage.centerOnScreen();

            } else {
                showError("Përdoruesi ose fjalëkalimi gabim!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Nuk mund të lidhemi me serverin. Ju lutemi provoni më vonë.");
        }
    }

    // Thirret automatikisht pas ngarkimit të pamjes
    @FXML
    public void initialize() {
        if (logoutBtn != null) {
            logoutBtn.setOnAction(e -> confirmLogout());
        }
    }

    // Konfirmon daljen me Alert dialog
    private void confirmLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmimi i Daljes");
        alert.setHeaderText(null);
        alert.setContentText("Je i sigurt që do të dalësh nga sistemi?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            logout();
        }
    }

    // Kthen përdoruesin te LoginView
    private void logout() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
            stage.setTitle("Kyçu");
            stage.setMaximized(false);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ngarkon një pamje të caktuar në contentArea
    private Locale currentLocale = new Locale("sq");
    private void loadView(String fxmlFileName) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", currentLocale);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + fxmlFileName), bundle);
            Node node = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Butonat e menusë
    @FXML
    private void loadHome(ActionEvent event) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Mirësevini në Panelin Kryesor!"));
    }

    @FXML private void loadFemijet(ActionEvent event) { loadView("FemijetView.fxml"); }
    @FXML private void loadGrupet(ActionEvent event) { loadView("GrupetView.fxml"); }
    @FXML private void loadUshqimet(ActionEvent event) { loadView("UshqimetView.fxml"); }
    @FXML private void loadMenyjaDitore(ActionEvent event) { loadView("MenyjaDitoreView.fxml"); }
    @FXML private void loadPrania(ActionEvent event) { loadView("PraniaView.fxml"); }
    @FXML private void loadPagesa(ActionEvent event) { loadView("PagesaView.fxml"); }
    @FXML private void loadOrari(ActionEvent event) { loadView("OrariView.fxml"); }
    @FXML private void loadSugjerimet(ActionEvent event) { loadView("SugjerimetView.fxml"); }
    @FXML private void loadDonacionet(ActionEvent event) { loadView("DonacionetView.fxml"); }
    @FXML private void loadShenimetShendetesore(ActionEvent event) { loadView("ShenimetShendetesoreView.fxml"); }
    @FXML private void loadKontaktetEmergjente(ActionEvent event) { loadView("KontaktetEmergjenteView.fxml"); }
    @FXML private void loadAktivitetet(ActionEvent event) { loadView("AktivitetetView.fxml"); }

    // Për errora
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void stage(Stage window) {
    }
}
