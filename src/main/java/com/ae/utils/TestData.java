package com.ae.utils;

public class TestData {

    private static ThreadLocal<String> registeredEmail = new ThreadLocal<>();
    private static ThreadLocal<String> registeredPassword = new ThreadLocal<>();

    public static void setEmail(String email) {
        registeredEmail.set(email);
    }

    public static String getEmail() {
        return registeredEmail.get();
    }

    public static void setPassword(String password) {
        registeredPassword.set(password);
    }

    public static String getPassword() {
        return registeredPassword.get();
    }
}
