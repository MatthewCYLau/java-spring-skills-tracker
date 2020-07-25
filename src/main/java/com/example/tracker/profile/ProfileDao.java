package com.example.tracker.profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileDao {

    int insertProfile(UUID id, Profile profile);

    List<Profile> selectAllProfiles();

    Optional<Profile> selectProfileById(UUID id);

}
