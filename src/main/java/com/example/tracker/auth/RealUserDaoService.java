package com.example.tracker.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.example.tracker.security.UserRole.ADMIN;
import static com.example.tracker.security.UserRole.BASIC_USER;

@Repository("real")
public class RealUserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RealUserDaoService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<User> getUsers() {

        String sql = "" +
                "SELECT " +
                " users.username, " +
                " users.password " +
                "FROM users ";
        return jdbcTemplate.query(
                sql,
                mapUserFromDb()
        );
    }

    private RowMapper<User> mapUserFromDb() {
        return (resultSet, i) -> {
            UUID id =  UUID.fromString("e149b3dc-0552-11eb-adc1-0242ac120002");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean isAdmin = true;
            Set<? extends GrantedAuthority> grantedAuthorities = isAdmin ? ADMIN.getGrantedAuthorities() : BASIC_USER.getGrantedAuthorities();
            return new User(
                    id, username,
                    passwordEncoder.encode(password),
                    isAdmin, grantedAuthorities,
                    true,
                    true,
                    true,
                    true

            );
        };
    }

}
