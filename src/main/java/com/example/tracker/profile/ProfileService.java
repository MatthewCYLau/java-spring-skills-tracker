package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional <Profile> getProfileById(UUID id) {
        return profileDataAccessService.selectProfileById(id);
    }
}
