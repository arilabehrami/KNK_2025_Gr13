package controllers;

import javafx.fxml.FXML;
import services.LanguageManager;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController {

    protected ResourceBundle resources;

    @FXML
    public void switchToAlbanian() {
        LanguageManager.setLocale(new Locale("sq"));
        refreshLanguage();
    }

    @FXML
    public void switchToEnglish() {
        LanguageManager.setLocale(Locale.ENGLISH);
        refreshLanguage();
    }

    protected abstract void refreshLanguage();
}
