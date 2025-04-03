package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(Long id, User user); // Исправленная сигнатура
    void deleteUser(Long id);
    User findByUsername(String username);
}