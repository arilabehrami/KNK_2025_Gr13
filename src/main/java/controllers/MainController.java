package controllers;

import services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {

    String username = UserSession.getInstance().getUsername();
    int userId = UserSession.getInstance().getUserId();
    @FXML
    private VBox menuPane;
    @FXML private Label welcomeLabel;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Button languageBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label emojiLabel;

    @FXML
    private Label appNameLabel;

    @FXML
    private Label quoteLabel;

    private boolean isEnglish = true;
    private ResourceBundle bundle;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        System.out.println("Username: " + username);
    }

    @FXML
    public void initialize() {
        // inicializo gjuhën sipas default
        Locale currentLocale = Locale.getDefault();
        isEnglish = currentLocale.getLanguage().equals("en");
        bundle = ResourceBundle.getBundle("languages.messages", currentLocale);

        setupMenu();
        setupTopButtons();
        updateLanguageTexts();

        languageBtn.setOnAction(e -> switchLanguage());
        String username = UserSession.getInstance().getUsername();
        welcomeLabel.setText("Mirë se vini, " + username + "!");
    }

    private void setupMenu() {
        menuPane.getChildren().clear();

        addMenuItem("📘 " + bundle.getString("menu.aktivitetet"), "AktivitetetView");
        addMenuItem("💰 " + bundle.getString("menu.donacionet"), "DonacionetView");
        addMenuItem("💡 " + bundle.getString("menu.edukatoret"), "EdukatoretView");
        addMenuItem("👧 " + bundle.getString("menu.femijet"), "FemijetView");
        addMenuItem("📞 " + bundle.getString("menu.kontaktet"), "KontaktetEmergjenteView");
        addMenuItem("⏰ " + bundle.getString("menu.orari"), "OrariView");
        addMenuItem("💵 " + bundle.getString("menu.pagesa"), "PagesaView");
        addMenuItem("🟢 " + bundle.getString("menu.prania"), "PraniaView");
        addMenuItem("🩺 " + bundle.getString("menu.shenimet"), "ShenimetShendetesoreView");
        addMenuItem("💡 " + bundle.getString("menu.sugjerimet"), "SugjerimetView");
        addMenuItem("💡 " + bundle.getString("menu.menyjaditore"), "MenyjaDitore");
        addMenuItem("💡 " + bundle.getString("menu.grupet"), "GrupetView");
        addMenuItem("💡 " + bundle.getString("menu.financat"), "FinancatView");
        addMenuItem("💡 " + bundle.getString("menu.ushqimet"), "UshqimetView");
        addMenuItem("💡 " + bundle.getString("menu.prinderit"), "PrinderitView");
    }

    private void addMenuItem(String title, String fxmlName) {
        Label menuItem = new Label(title);
        menuItem.setStyle("-fx-padding: 10; -fx-background-color: #ffffff; -fx-border-color: #dddddd;");
        menuItem.setMaxWidth(Double.MAX_VALUE);

        menuItem.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + fxmlName + ".fxml"), bundle);
                Parent loadedPane = loader.load();

                centerPane.getChildren().setAll(loadedPane);
                AnchorPane.setTopAnchor(loadedPane, 0.0);
                AnchorPane.setBottomAnchor(loadedPane, 0.0);
                AnchorPane.setLeftAnchor(loadedPane, 0.0);
                AnchorPane.setRightAnchor(loadedPane, 0.0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuPane.getChildren().add(menuItem);
    }

    private void setupTopButtons() {
        logoutBtn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new javafx.scene.Scene(root));
                newStage.setTitle("Login");
                newStage.show();

                // Mbyll dritaren aktuale
                logoutBtn.getScene().getWindow().hide();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void updateLanguageTexts() {
        appNameLabel.setText(bundle.getString("app_name"));
        quoteLabel.setText(bundle.getString("quote"));
        logoutBtn.setText(bundle.getString("logout"));
        languageBtn.setText(isEnglish ? "Shqip" : "English");

        // Përditëso menunë për të reflektuar gjuhën
        setupMenu();
    }

    private void switchLanguage() {
        isEnglish = !isEnglish;
        Locale newLocale = isEnglish ? new Locale("en") : new Locale("sq");
        Locale.setDefault(newLocale);
        bundle = ResourceBundle.getBundle("languages.messages", newLocale);

        updateLanguageTexts();

        // Përditëso përmbajtjen në qendër (nëse ke ngarkuar ndonjë pane)
        if (!centerPane.getChildren().isEmpty()) {
            // Merr emrin e parë të panelit të ngarkuar dhe ngarko prapë me bundle të ri
            Parent currentPane = (Parent) centerPane.getChildren().get(0);
            String fxmlName = currentPane.getUserData() != null ? currentPane.getUserData().toString() : null;

            if (fxmlName != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + fxmlName + ".fxml"), bundle);
                    Parent newPane = loader.load();

                    centerPane.getChildren().setAll(newPane);
                    AnchorPane.setTopAnchor(newPane, 0.0);
                    AnchorPane.setBottomAnchor(newPane, 0.0);
                    AnchorPane.setLeftAnchor(newPane, 0.0);
                    AnchorPane.setRightAnchor(newPane, 0.0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stage(Stage window) {
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        UserSession.clearSession();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutBtn.getScene().getWindow(); // logoutButton duhet të jetë FXML-i yt
            stage.setScene(new Scene(root));
            stage.setTitle("Kyçu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
