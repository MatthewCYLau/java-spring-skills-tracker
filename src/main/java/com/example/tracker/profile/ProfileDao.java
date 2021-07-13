package com.example.tracker.profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileDao {

	int insertProfile(UUID id, Profile profile);

	List<Profile> selectAllProfiles();

	Optional<Profile> selectProfileById(UUID id);

	int deleteProfileById(UUID id);

	int updateEmail(UUID id, String email);

	int updateName(UUID id, String name);

}
