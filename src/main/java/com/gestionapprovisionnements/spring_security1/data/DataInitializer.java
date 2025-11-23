package com.gestionapprovisionnements.spring_security1.data;

import com.gestionapprovisionnements.spring_security1.entity.User;
import com.gestionapprovisionnements.spring_security1.entity.enums.Role;
import com.gestionapprovisionnements.spring_security1.entity.enums.UserStatus;
import com.gestionapprovisionnements.spring_security1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.count() == 0) {

                User admin = new User();
                admin.setFullName("Super Admin");
                admin.setEmail("SOCIETE@example.com");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setRole(Role.SOCIETE);
                admin.setStatus(UserStatus.ACTIVE);
                userRepository.save(admin);

                User comptable = new User();
                comptable.setFullName("Comptable Test");
                comptable.setEmail("comptable@example.com");
                comptable.setPassword(passwordEncoder.encode("123456"));
                comptable.setRole(Role.COMPTABLE);
                comptable.setStatus(UserStatus.ACTIVE);
                userRepository.save(comptable);

                User societe = new User();
                societe.setFullName("Societe User");
                societe.setEmail("societe@example.com");
                societe.setPassword(passwordEncoder.encode("123456"));
                societe.setRole(Role.SOCIETE);
                societe.setStatus(UserStatus.ACTIVE);
                userRepository.save(societe);

                System.out.println("✔ 3 Utilisateurs créés automatiquement.");
            }
        };
    }
}
