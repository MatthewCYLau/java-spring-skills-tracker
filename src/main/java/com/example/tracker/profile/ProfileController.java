package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @PostMapping
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        profileService.addProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(path = "{id}")
    public Profile getProfileById(@PathVariable("id") UUID id) {
        return profileService.getProfileById(id).orElse(null);
    }

    @PatchMapping(path = "{id}")
    public void updateProfile(@PathVariable("id") UUID id,
                              @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteProfile(@PathVariable("id") UUID id) {
        profileService.deleteProfile(id);
    }
}
