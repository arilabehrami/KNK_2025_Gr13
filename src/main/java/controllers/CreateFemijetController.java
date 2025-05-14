package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CreateFemijetController {

    @FXML
    private TableView<?> femijetTable;

    @FXML
    private TableColumn<?, String> emriColumn;

    @FXML
    private TableColumn<?, String> mbiemriColumn;

    @FXML
    private TableColumn<?, String> ditelindjaColumn;

    @FXML
    private TableColumn<?, String> gjiniaColumn;

    @FXML
    private TableColumn<?, String> adresaColumn;

    @FXML
    private TextField emriField;

    @FXML
    private TextField mbiemriField;

    @FXML
    private TextField ditelindjaField;

    @FXML
    private ComboBox<String> gjiniaComboBox;

    @FXML
    private TextField adresaField;

    @FXML
    private Button shtoButoni;

    @FXML
    private Button perditesoButoni;

    @FXML
    private Button fshiButoni;

    @FXML
    private void initialize() {
        // Shto opsionet në ComboBox
        gjiniaComboBox.getItems().addAll("Mashkull", "Femër");
    }

    @FXML
    private void shtoFemije() {
        String emri = emriField.getText();
        String mbiemri = mbiemriField.getText();
        String ditelindja = ditelindjaField.getText();
        String gjinia = gjiniaComboBox.getValue();
        String adresa = adresaField.getText();

        System.out.println("Emri: " + emri);
        System.out.println("Mbiemri: " + mbiemri);
        System.out.println("Ditelindja: " + ditelindja);
        System.out.println("Gjinia: " + gjinia);
        System.out.println("Adresa: " + adresa);

       
    }

    @FXML
    private void perditesoFemije() {

        System.out.println("U klikua butoni perditeso");
    }

    @FXML
    private void fshiFemije() {

        System.out.println("U klikua butoni fshi");
    }
}
