package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreatePraniaController {

    @FXML
    private Label lblFemijaId;

    @FXML
    private Label lblData;

    @FXML
    private Label lblStatusi;

    @FXML
    private TableView<?> praniaTable;

    @FXML
    private TableColumn<?, ?> colPraniaId, colFemijaId, colData, colStatusi;

    @FXML
    private TextField tfFemijaId;

    @FXML
    private DatePicker dpData;

    @FXML
    private ComboBox<?> cbStatusi;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnChangeLanguage;

    private Locale currentLocale = new Locale("sq"); // default language: Albanian

    @FXML
    public void initialize() {
        updateTexts();

        btnChangeLanguage.setOnAction(this::handleChangeLanguage);
    }

    private void handleChangeLanguage(ActionEvent event) {
        if (currentLocale.getLanguage().equals("sq")) {
            currentLocale = new Locale("en");
        } else {
            currentLocale = new Locale("sq");
        }
        updateTexts();
    }

    private void updateTexts() {
        ResourceBundle bundle = ResourceBundle.getBundle("languages.messages", currentLocale);

        colPraniaId.setText(bundle.getString("prania.id"));
        colFemijaId.setText(bundle.getString("femija.id"));
        colData.setText(bundle.getString("data"));
        colStatusi.setText(bundle.getString("statusi"));

        lblFemijaId.setText(bundle.getString("femija.id") + ":");
        lblData.setText(bundle.getString("data") + ":");
        lblStatusi.setText(bundle.getString("statusi") + ":");

        btnSave.setText(bundle.getString("btn.save"));
        btnChangeLanguage.setText(bundle.getString("btn.changeLanguage"));
    }

}
