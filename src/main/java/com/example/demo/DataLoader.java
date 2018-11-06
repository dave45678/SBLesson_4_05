package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo.model.*;

import java.util.Arrays;

// Add user/role data into the database before the application runs
@Component
public class DataLoader implements CommandLineRunner{
    //a static final variable is Java's waqy of creating a constant value
    // You can change this value to false to skip the data loader
    static final boolean LOADDATA = true;

    // Instantiate both the user and role repository to invoke constructor methods
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    /*
        Run method will be executed after the application context is
        loaded and right before the Spring Application run method is
        completed.
     */
    @Override
    public void run(String... strings) throws Exception{
        if (LOADDATA) {
            System.out.println("Loading data...");

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("bob@bob.com", "password",
                    "Bob", "Bobberson", true, "bob");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@adm.com", "password",
                    "Admin", "User", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }
    }
}