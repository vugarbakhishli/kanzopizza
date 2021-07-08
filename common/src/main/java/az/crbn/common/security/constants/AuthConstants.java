package az.crbn.common.security.constants;

public final class AuthConstants {
    public static final int PASSWORD_MIN_LENGTH = 7;
    public static final int PASSWORD_MAX_LENGTH = 50;
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    private AuthConstants() {
    }
}
