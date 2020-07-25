package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Profile> selectAllProfiles() {

        String sql = "" +
                "SELECT id, " +
                "name, " +
                "email " +
                "FROM profiles";

        return jdbcTemplate.query(sql, mapProfileFromDb());
    }

    private RowMapper<Profile> mapProfileFromDb() {
        return (resultSet, i) -> {
            String idStr = resultSet.getString("id");
            UUID id = UUID.fromString(idStr);

            String name = resultSet.getString("name");
            String email = resultSet.getString("email");

            return new Profile(
                    id,
                    name,
                    email

            );
        };
    }

    public Optional<Profile> selectProfileById(UUID id) {

        final String sql = "SELECT id, name, email FROM profiles WHERE id = ?";

        Profile profile = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID profileId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return new Profile(profileId, name, email);
                });

        return Optional.ofNullable(profile);
    }

    int insertProfile(UUID id, Profile profile) {

        String sql = "" +
                "INSERT INTO profiles (id, name, email) " +
                "VALUES (?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                profile.getName(),
                profile.getEmail()
        );
    }
}
