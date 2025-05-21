package services;

public class UserSession {
    private static UserSession instance;

    private final String username;
    private final int userId;

    private UserSession(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public static void init(int userId, String username) {
        instance = new UserSession(userId, username);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void clear() {
        instance = null;
    }

    public static void clearSession() {
        clear();
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }
}
