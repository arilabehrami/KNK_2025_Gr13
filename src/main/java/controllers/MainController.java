package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private VBox menuPane;

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
    private final ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", Locale.getDefault());

    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsername(String username) {
        // Ruaj username ose bëj diçka me të
        System.out.println("Username: " + username);
    }

    public void initialize() {
        setupMenu();
        setupTopButtons();
    }

    private void setupMenu() {
        addMenuItem("📘 Aktivitetet", "AktivitetetView");
        addMenuItem("💰 Donacionet", "DonacionetView");
        addMenuItem("💡 Edukatoret", "EdukatoretView");
        addMenuItem("👧 Femijet", "FemijetView");
        addMenuItem("📞 Kontaktet Emergjente", "KontaktetEmergjenteView");
        addMenuItem("⏰ Orari", "OrariView");
        addMenuItem("💵 Pagesa", "PagesaView");
        addMenuItem("🟢 Prania", "PraniaView");
        addMenuItem("🩺 Shënimet Shëndetësore", "ShenimetShendetesoreView");
        addMenuItem("💡 Sugjerimet", "SugjerimetView");
        addMenuItem("💡 Menyja Ditore", "MenyjaDitore");
        addMenuItem("💡 Grupet", "GrupetView");
        addMenuItem("💡 Financat", "FinancatView");
        addMenuItem("💡 Ushqimet", "UshqimetView");
        addMenuItem("💡 Prinderit", "PrinderitView");
    }

    private void addMenuItem(String title, String mainClassPath) {
        Label menuItem = new Label(title);
        menuItem.setStyle("-fx-padding: 10; -fx-background-color: #ffffff; -fx-border-color: #dddddd;");
        menuItem.setMaxWidth(Double.MAX_VALUE);

        menuItem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                // Ngarko FXML me ResourceBundle përkthimi
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/Views/" + mainClassPath + ".fxml"), bundle);
                Parent loadedPane = loader.load();

                // Zëvendëso përmbajtjen e centerPane me atë të ngarkuar
                centerPane.getChildren().setAll(loadedPane);

                // Ancorim për të plotësuar qendrën
                javafx.scene.layout.AnchorPane.setTopAnchor(loadedPane, 0.0);
                javafx.scene.layout.AnchorPane.setBottomAnchor(loadedPane, 0.0);
                javafx.scene.layout.AnchorPane.setLeftAnchor(loadedPane, 0.0);
                javafx.scene.layout.AnchorPane.setRightAnchor(loadedPane, 0.0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuPane.getChildren().add(menuItem);
    }

    private void setupTopButtons() {

        logoutBtn.setOnAction(e -> {
            try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
                javafx.scene.Parent root = loader.load();
                javafx.stage.Stage stage = new javafx.stage.Stage();
                stage.setScene(new javafx.scene.Scene(root));
                stage.setTitle("Login");
                stage.show();

                // Mbyll dritaren aktuale
                logoutBtn.getScene().getWindow().hide();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void stage(Stage window) {
    }
}