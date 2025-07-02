package com.tutorial.apidemo.apidemo.repositories;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.tutorial.apidemo.apidemo.model.User;
import com.tutorial.apidemo.apidemo.model.State;

@Repository
public class UserRepository {
    
    // Thread-safe storage for user data using Long ID (matches User entity)
    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    
    // Thread-safe auto-increment counter for ID generation
    private final AtomicLong idCounter = new AtomicLong(0);

    /**
     * Creates a new user with the given details
     * @param fullName User's full name
     * @param email User's email address
     * @param hashPassword User's hashed password
     * @param state User's current state
     * @return The created user with auto-generated ID
     */
    public User addUser(String fullName, String email, String hashPassword, State state) {
        User user = User.builder()
                .fullname(fullName)
                .email(email)
                .hash_password(hashPassword)
                .state(state)
                .build();
        return save(user);
    }

    /**
     * Saves a user to the repository. Auto-generates ID if not present.
     * @param user The user to save
     * @return The saved user with ID
     */
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }
    
    /**
     * Finds a user by their ID
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    public User findById(Long id) {
        return users.get(id);
    }
    
    /**
     * Finds a user by email address
     * @param email The user's email
     * @return The user if found, null otherwise
     */
    public User findByEmail(String email) {
        return users.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Returns all users in the repository
     * @return List of all users
     */
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    /**
     * Checks if a user exists with the given ID
     * @param id The user ID to check
     * @return true if user exists, false otherwise
     */
    public boolean existsById(Long id) {
        return users.containsKey(id);
    }
    
    /**
     * Checks if a user exists with the given email
     * @param email The email to check
     * @return true if user exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> email.equals(user.getEmail()));
    }
    
    /**
     * Deletes a user by their ID
     * @param id The user ID to delete
     * @return true if user was deleted, false if not found
     */
    public boolean deleteById(Long id) {
        return users.remove(id) != null;
    }
    
    /**
     * Updates an existing user
     * @param user The user with updated information
     * @return The updated user, or null if user doesn't exist
     */
    public User update(User user) {
        if (user.getId() != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }
    
    /**
     * Returns the total number of users
     * @return The count of users
     */
    public long count() {
        return users.size();
    }
    
    /**
     * Creates a user only if it doesn't already exist
     * @param user The user to create
     * @return The created user, or existing user if already present
     */
    public User saveIfNotExists(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.incrementAndGet());
        }
        
        User existingUser = users.putIfAbsent(user.getId(), user);
        return existingUser != null ? existingUser : user;
    }
    
    /**
     * Clears all users from the repository
     */
    public void clear() {
        users.clear();
        idCounter.set(0);
    }
}
