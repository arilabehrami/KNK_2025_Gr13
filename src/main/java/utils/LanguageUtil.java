package utils;
import java.util.Locale;

public class LanguageUtil {
    private static Locale currentLocale = new Locale("sq");
    public static void toggleLanguage() {
        if (currentLocale.getLanguage().equals("sq")) {
            currentLocale = new Locale("en");
        } else {
            currentLocale = new Locale("sq");
        }
    }
    public static Locale getLocale() {
        return currentLocale;
    }
}
