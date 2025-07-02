package com.tutorial.apidemo.apidemo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorial.apidemo.apidemo.model.User;


@Service
public class UserServiceInMemory implements  UserService {

    @Override
    public User login(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public User logout(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }

    @Override
    public Boolean activateUser(String activationCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public Boolean updateEmail(String oldEmail, String newEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmail'");
    }

    @Override
    public Optional<User> findByMail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByMail'");
    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
        
}
