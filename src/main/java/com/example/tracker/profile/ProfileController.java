package com.example.tracker.profile;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void createProfile(@RequestBody Profile profile) {
        System.out.println("register");
        System.out.println(profile);
    }

    @GetMapping(path = "{id}")
    public Profile getProfileById(@PathVariable("id") UUID id) {
        return profileService.getProfileById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProfile(@PathVariable("id") Integer id) {
        System.out.println("Delete");
        System.out.println(id);
    }

    @PutMapping(path = "{id}")
    public void updateProfile(@PathVariable("id") Integer id, @RequestBody Profile profile) {
        System.out.println("update");
        System.out.println(String.format("%s %s", id, profile));
    }}
