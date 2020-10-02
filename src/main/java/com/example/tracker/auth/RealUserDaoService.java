package com.example.tracker.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.tracker.security.UserRole.ADMIN;

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
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            return new User(
                    username,
                    passwordEncoder.encode(password),
                    ADMIN.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true

            );
        };
    }

}
