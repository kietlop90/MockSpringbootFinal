package com.mock.api.config;

import com.mock.api.Enum.UserRoleEnum;
import com.mock.api.entities.Permission;
import com.mock.api.entities.Role;
import com.mock.api.entities.User;
import com.mock.api.repository.PermissionRepository;
import com.mock.api.repository.RoleRepository;
import com.mock.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // check int data
        boolean isInitData = userRepository.count() == 0;
        List<Role> roles;
        Role roleAdmin;
        Role roleUser;
        User userAdmin;

        if (isInitData) {

            roles = new ArrayList<>();
            roleAdmin = roleRepository.save(Role.builder()
                    .name(UserRoleEnum.ROLE_ADMIN.getValue())
                    .build());
            roles.add(roleAdmin);
            roleUser = roleRepository.save(Role.builder()
                    .name(UserRoleEnum.ROLE_USER.getValue())
                    .build());


            userAdmin = User
                    .builder()
                    .username("admin")
                    .email("admin@admin.com")
                    .role(roleAdmin)
                    .password(passwordEncoder.encode("password"))
                    .build();
            userRepository.save(userAdmin);
            log.debug("created ADMIN user - {}", userAdmin);

            List<Permission> permissions = new ArrayList<>();
            permissions.add(Permission.builder()
                    .role(roleAdmin)
                    .name("READ_USER").build());
            permissions.add(Permission.builder()
                    .role(roleAdmin)
                    .name("WRITE_USER").build());
            permissionRepository.saveAll(permissions);
//            roleAdmin.setPermissions(permissions);
//            roleAdmin.setUsers(Set.of(userAdmin));
//
        }
//        else { // update data for version
//            roles = roleRepository.findAll();
//            roleAdmin = roles.stream()
//                    .filter(ele -> ele.getName().equals(UserRoleEnum.ROLE_ADMIN.getValue())).findFirst()
//                    .orElse(new Role());
//            roleUser = roles.stream()
//                    .filter(ele -> ele.getName().equals(UserRoleEnum.ROLE_USER.getValue())).findFirst()
//                    .orElse(new Role());
//        }

    }
}
