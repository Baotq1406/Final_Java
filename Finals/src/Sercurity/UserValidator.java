package Sercurity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[a-zA-Z\\d@#$%^&+=]).{6,}$";

    private static final String USERNAME_REGEX = "^.{6,}$";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
