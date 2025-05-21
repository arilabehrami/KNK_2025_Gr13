package services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static LanguageManager instance;
    private Locale locale;
    private ResourceBundle bundle;

    private LanguageManager() {
        setLocale("sq"); // default language
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public void setLocale(String languageCode) {
        this.locale = new Locale(languageCode);
        this.bundle = ResourceBundle.getBundle("languages.messages", locale);
    }

    public ResourceBundle getResourceBundle() {
        return bundle;
    }

    public Locale getLocale() {
        return locale;
    }
}
