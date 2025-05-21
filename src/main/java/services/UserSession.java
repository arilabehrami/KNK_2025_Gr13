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

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public static void clearSession() {
        instance = null;
    }
}
