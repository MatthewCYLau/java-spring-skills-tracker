package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileDataAccessService implements ProfileDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Profile> selectAllProfiles() {

        String sql = "" +
                "SELECT profile_id, " +
                "name, " +
                "email " +
                "FROM profiles";

        return jdbcTemplate.query(sql, mapProfileFromDb());
    }

    private RowMapper<Profile> mapProfileFromDb() {
        return (resultSet, i) -> {
            String idStr = resultSet.getString("profile_id");
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

    @Override
    public Optional<Profile> selectProfileById(UUID id) {

        final String sql = "SELECT profile_id, name, email FROM profiles WHERE profile_id = ?";

        Profile profile = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID profileId = UUID.fromString(resultSet.getString("profile_id"));
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return new Profile(profileId, name, email);
                });

        return Optional.ofNullable(profile);
    }

    @Override
    public int insertProfile(UUID id, Profile profile) {

        String sql = "" +
                "INSERT INTO profiles (profile_id, name, email) " +
                "VALUES (?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                id,
                profile.getName(),
                profile.getEmail()
        );
    }

    @Override
    public int updateEmail(UUID id, String email) {
        String sql = "" +
                "UPDATE profiles " +
                "SET email = ? " +
                "WHERE profile_id = ?";
        return jdbcTemplate.update(sql, email, id);
    }

    @Override
    public int updateName(UUID id, String name) {
        String sql = "" +
                "UPDATE profiles " +
                "SET name = ? " +
                "WHERE profile_id = ?";
        return jdbcTemplate.update(sql, name, id);
    }

    @Override
    public int deleteProfileById(UUID id) {
        String sql = "" +
                "DELETE FROM profiles " +
                "WHERE profile_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
