package com.mock.api.config;

import com.mock.api.entities.UserModel;
import com.mock.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            UserModel admin = UserModel
                    .builder()
                    .username("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password"))
                    .build();

            userRepository.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}
