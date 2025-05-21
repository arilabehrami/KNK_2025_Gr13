package services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    private static Locale currentLocale = Locale.forLanguageTag("sq"); // default shqip

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("languages.messages", currentLocale);
    }

    public static void toggleLanguage() {
        if (currentLocale.getLanguage().equals("sq")) {
            currentLocale = Locale.ENGLISH;
        } else {
            currentLocale = Locale.forLanguageTag("sq");
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}
