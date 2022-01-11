package com.example.loverbe.model.entity;


public class Regex {
    public static final String USER_REGEX = "^[a-zA-Z0-9]+([a-zA-Z0-9]([_\\\\-])[a-zA-Z0-9])*[a-zA-Z0-9]+${4,}";
    public static final String PASSWORD_REGEX = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).*)[^\\s]{3,}$";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String USERNAME_MESSAGE = "* invalid username";
    public static final String PASSWORD_MESSAGE = "* invalid password";
    public static final String RE_PASSWORD_MESSAGE = "* invalid re-password";
    public static final String EMAIL_MESSAGE = "* invalid email";

}
