package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import jakarta.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        createRoles();
        createAdmin();
        createUser();
    }

    private void createRoles() {
        if (!roleService.existsByName("ROLE_ADMIN")) {
            roleService.save(new Role("ROLE_ADMIN"));
        }
        if (!roleService.existsByName("ROLE_USER")) {
            roleService.save(new Role("ROLE_USER"));
        }
    }

    private void createAdmin() {
        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@example.com");
            admin.setRoles(Set.of(
                    roleService.findByName("ROLE_ADMIN"),
                    roleService.findByName("ROLE_USER")
            ));
            userService.saveUser(admin);
        }
    }

    private void createUser() {
        if (userService.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@example.com");
            user.setRoles(Set.of(
                    roleService.findByName("ROLE_USER")
            ));
            userService.saveUser(user);
        }
    }
}