package services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    private static Locale currentLocale = Locale.forLanguageTag("sq"); // default shqip

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("languages.messages", currentLocale);
    }

    public static void toggleLanguage() {
        if (currentLocale.getLanguage().equals("sq")) {
            currentLocale = Locale.ENGLISH;
        } else {
            currentLocale = Locale.forLanguageTag("sq");
        }
    }
}
