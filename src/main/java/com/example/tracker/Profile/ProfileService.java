package com.example.tracker.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
