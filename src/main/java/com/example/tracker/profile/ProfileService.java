package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileDataAccessService profileDataAccessService;

    @Autowired
    public ProfileService(ProfileDataAccessService profileDataAccessService) {
        this.profileDataAccessService = profileDataAccessService;
    }

    public List<Profile> getProfiles() {
        return profileDataAccessService.selectAllProfiles();
    }

    public Optional<Profile> getProfileById(UUID id) {
        return profileDataAccessService.selectProfileById(id);
    }

    void addProfile(Profile profile) {
        addProfile(null, profile);
    }

    void addProfile(UUID id, Profile profile) {
        UUID newProfileId = Optional.ofNullable(id).orElse(UUID.randomUUID());

        profileDataAccessService.insertProfile(newProfileId, profile);
    }

    public void updateProfile(UUID id, Profile profile) {
        Optional.ofNullable(profile.getEmail())
                .ifPresent(email -> {
                    profileDataAccessService.updateEmail(id, email);
                });
        Optional.ofNullable(profile.getName())
                .filter(name -> !StringUtils.isEmpty(name))
                .ifPresent(name -> profileDataAccessService.updateName(id, name));
    }

    void deleteProfile(UUID id) {
        profileDataAccessService.deleteProfileById(id);
    }
}
