package com.tutorial.apidemo.apidemo.services;

import java.util.Optional;

import com.tutorial.apidemo.apidemo.model.User;


public interface UserService {
    public User login(String email, String password);
    public User logout(String email);

    public User addUser(String fullname, String email, String password);
    public Boolean activateUser(String activationCode);
    
    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String oldEmail, String newEmail);

    public Optional<User> findByMail(String email);
    public User findById(String id);

}
