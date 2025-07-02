package com.tutorial.apidemo.apidemo;

import com.tutorial.apidemo.apidemo.model.User;
import com.tutorial.apidemo.apidemo.model.State;
import com.tutorial.apidemo.apidemo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestUserRepository {
    
    @Autowired
    private UserRepository userRepository;
    
    @BeforeEach
    void setUp() {
        userRepository.clear(); // Reset before each test
    }
    
    @Test
    void testAddUser() {
        // Arrange
        String fullName = "Tien Cut";
        String email = "tiencut@example.com";
        String hashPassword = "hashed123";
        State state = State.ACTIVE;
        
        // Act
        User createdUser = userRepository.addUser(fullName, email, hashPassword, state);
        
        // Assert
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getFullname()).isEqualTo(fullName);
        assertThat(createdUser.getEmail()).isEqualTo(email);
        assertThat(createdUser.getHash_password()).isEqualTo(hashPassword);
        assertThat(createdUser.getState()).isEqualTo(state);
    }
    
    @Test
    void testSaveUser() {
        // Arrange
        User user = User.builder()
                .fullname("John Doe")
                .email("john@example.com")
                .hash_password("password123")
                .state(State.ACTIVE)
                .build();
        
        // Act
        User savedUser = userRepository.save(user);
        
        // Assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(1L); // First auto-generated ID
    }
    
    @Test
    void testFindById() {
        // Arrange
        User user = userRepository.addUser("Jane Doe", "jane@example.com", "pass456", State.ACTIVE);
        Long userId = user.getId();
        
        // Act
        User foundUser = userRepository.findById(userId);
        
        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(userId);
        assertThat(foundUser.getFullname()).isEqualTo("Jane Doe");
    }
    
    @Test
    void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        userRepository.addUser("Test User", email, "password", State.ACTIVE);
        
        // Act
        User foundUser = userRepository.findByEmail(email);
        
        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUser.getFullname()).isEqualTo("Test User");
    }
    
    @Test
    void testFindAll() {
        // Arrange
        userRepository.addUser("User 1", "user1@example.com", "pass1", State.ACTIVE);
        userRepository.addUser("User 2", "user2@example.com", "pass2", State.INACTIVE);
        userRepository.addUser("User 3", "user3@example.com", "pass3", State.ACTIVE);
        
        // Act
        List<User> allUsers = userRepository.findAll();
        
        // Assert
        assertThat(allUsers).hasSize(3);
        assertThat(allUsers).extracting(User::getFullname)
                .containsExactlyInAnyOrder("User 1", "User 2", "User 3");
    }
    
    @Test
    void testExistsById() {
        // Arrange
        User user = userRepository.addUser("Existing User", "exists@example.com", "password", State.ACTIVE);
        Long userId = user.getId();
        
        // Act & Assert
        assertThat(userRepository.existsById(userId)).isTrue();
        assertThat(userRepository.existsById(999L)).isFalse();
    }
    
    @Test
    void testExistsByEmail() {
        // Arrange
        String email = "exists@example.com";
        userRepository.addUser("Existing User", email, "password", State.ACTIVE);
        
        // Act & Assert
        assertThat(userRepository.existsByEmail(email)).isTrue();
        assertThat(userRepository.existsByEmail("notexists@example.com")).isFalse();
    }
    
    @Test
    void testDeleteById() {
        // Arrange
        User user = userRepository.addUser("To Delete", "delete@example.com", "password", State.ACTIVE);
        Long userId = user.getId();
        
        // Act
        boolean deleted = userRepository.deleteById(userId);
        
        // Assert
        assertThat(deleted).isTrue();
        assertThat(userRepository.findById(userId)).isNull();
        assertThat(userRepository.existsById(userId)).isFalse();
    }
    
    @Test
    void testUpdate() {
        // Arrange
        User user = userRepository.addUser("Original Name", "original@example.com", "password", State.ACTIVE);
        Long userId = user.getId();
        
        // Modify user
        user.setFullname("Updated Name");
        user.setEmail("updated@example.com");
        
        // Act
        User updatedUser = userRepository.update(user);
        
        // Assert
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getFullname()).isEqualTo("Updated Name");
        assertThat(updatedUser.getEmail()).isEqualTo("updated@example.com");
        
        // Verify in repository
        User retrievedUser = userRepository.findById(userId);
        assertThat(retrievedUser.getFullname()).isEqualTo("Updated Name");
    }
    
    @Test
    void testCount() {
        // Arrange
        assertThat(userRepository.count()).isEqualTo(0);
        
        userRepository.addUser("User 1", "user1@example.com", "pass1", State.ACTIVE);
        userRepository.addUser("User 2", "user2@example.com", "pass2", State.ACTIVE);
        
        // Act & Assert
        assertThat(userRepository.count()).isEqualTo(2);
    }
    
    @Test
    void testAutoIncrementId() {
        // Act
        User user1 = userRepository.addUser("User 1", "user1@example.com", "pass1", State.ACTIVE);
        User user2 = userRepository.addUser("User 2", "user2@example.com", "pass2", State.ACTIVE);
        User user3 = userRepository.addUser("User 3", "user3@example.com", "pass3", State.ACTIVE);
        
        // Assert
        assertThat(user1.getId()).isEqualTo(1L);
        assertThat(user2.getId()).isEqualTo(2L);
        assertThat(user3.getId()).isEqualTo(3L);
    }
}
