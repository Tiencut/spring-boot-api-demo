package com.tutorial.apidemo.security;

public interface Hashing {
    public String hashPassword (String password);
    public boolean validatePassword(String origainalPassword, String tmp);
}
