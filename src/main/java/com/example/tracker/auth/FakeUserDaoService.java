package com.example.tracker.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.tracker.security.UserRole.ADMIN;
import static com.example.tracker.security.UserRole.BASIC_USER;

@Repository("fake")
public class FakeUserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<User> getUsers() {
        List<User> users = Lists.newArrayList(
                new User(
                        UUID.fromString("e149b3dc-0552-11eb-adc1-0242ac120002"), "basic_user",
                        passwordEncoder.encode("password"),
                        BASIC_USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        UUID.fromString("e149b3dc-0552-11eb-adc1-0242ac120002"), "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return users;
    }

}