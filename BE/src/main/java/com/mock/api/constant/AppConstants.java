package com.mock.api.constant;

public class AppConstants {

    public final static String COMMA = ",";
    public final static String OR_STRING = " OR ";
    public final static String SPACE_STRING = " ";
    public final static String OR_SYMBOL = "\\|";
    public final static String HYPHEN = "-";
    public final static String EMPTY = "";
    public final static String LEFT_PARENTHESES = "(";
    public final static String RIGHT_PARENTHESES = ")";
    public final static String LEFT_BRACKETS = "[";
    public final static String RIGHT_BRACKETS = "]";
    public final static String EQUAL = "=";
    public final static String COLON = ":";
    public final static String SEMICOLON = ";";

    // authentication config
    public final static String ADMIN_ROLE = "admin";
    public final static String USER_ROLE = "user";
    public final static String ROLES = "roles";
    public final static String USER_ID = "userId";
    public final static String USERNAME = "username";
    public final static String AUTHORITIES = "authorities";

    /**
     * REGEX_PASSWORD </br>
     * <p>
     * Min 1 uppercase letter.
     * Min 1 lowercase letter.
     * Min 1 special character.
     * Min 1 number.
     * Min 8 characters.
     */
    public final static String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$";
}
