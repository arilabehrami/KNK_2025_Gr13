package controllers;

import javafx.scene.Node;
import services.SceneManager;
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
import services.LanguageManager;
import utils.SceneLocator;

import java.util.ResourceBundle;

public class MainController {

    @FXML
    private VBox menuPane;

    @FXML
    private Label welcomeLabel;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Button logoutBtn;



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
        bundle = LanguageManager.getBundle();

        setupMenu();
        setupTopButtons();

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
        addMenuItem("🍽 " + bundle.getString("menu.menyjaditore"), "MenyjaDitore");
        addMenuItem("👥 " + bundle.getString("menu.grupet"), "GrupetView");
        addMenuItem("💳 " + bundle.getString("menu.financat"), "FinancatView");
        addMenuItem("🥦 " + bundle.getString("menu.ushqimet"), "UshqimetView");
        addMenuItem("👪 " + bundle.getString("menu.prinderit"), "PrinderitView");
        addMenuItem("💰 " + bundle.getString("menu.pagat"), "PagatView");}

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
        logoutBtn.setOnAction(this::onLoginButtonClick);
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        SceneManager.changeScene(SceneLocator.LOGIN_VIEW);

        // Merr stage nga event-i dhe e maximizon
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        // ose për full screen:
        // stage.setFullScreen(true);
    }

}
