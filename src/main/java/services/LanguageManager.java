package services;

import java.util.Locale;
import java.util.ResourceBundle;


public class LanguageManager {
    private static Locale currentLocale = new Locale("sq"); // default shqip

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getLocale() {
        return currentLocale;
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("languages.messages", currentLocale);
    }
}