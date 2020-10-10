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
        return selectAllUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public List<User> selectAllUsers() {

        String sql = "" +
                "SELECT " +
                " users.user_id, " +
                " users.username, " +
                " users.password, " +
                " users.is_admin " +
                "FROM users ";
        return jdbcTemplate.query(
                sql,
                mapUserFromDb()
        );
    }

    private RowMapper<User> mapUserFromDb() {
        return (resultSet, i) -> {
            UUID userId =  UUID.fromString(resultSet.getString("user_id"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean isAdmin = resultSet.getBoolean("is_admin");
            Set<? extends GrantedAuthority> grantedAuthorities = isAdmin ? ADMIN.getGrantedAuthorities() : BASIC_USER.getGrantedAuthorities();
            return new User(
                    userId, username,
                    passwordEncoder.encode(password),
                    isAdmin, grantedAuthorities,
                    true,
                    true,
                    true,
                    true

            );
        };
    }

    @Override
    public int insertUser(UUID id, User user) {

        String sql = "" +
                "INSERT INTO users (user_id, username, password, is_admin) " +
                "VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                user.getUsername(),
                user.getPassword(),
                user.isAdmin()
        );
    }

}
