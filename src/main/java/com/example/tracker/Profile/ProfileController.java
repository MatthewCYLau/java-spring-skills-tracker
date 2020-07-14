package com.example.tracker.Profile;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private static final List<Profile> PROFILES = Arrays.asList(
            new Profile(1, "James Bond"),
            new Profile(2, "Maria Jones"),
            new Profile(3, "Anna Smith")
    );

    @GetMapping
    public List<Profile> getProfiles() {
        System.out.println("Get profiles");
        return PROFILES;
    }

    @PostMapping
    public void createProfile(@RequestBody Profile profile) {
        System.out.println("register");
        System.out.println(profile);
    }

    @GetMapping(path = "{id}")
    public Profile getProfile(@PathVariable("id") Integer id) {
        return PROFILES.stream()
                .filter(profile -> id.equals(profile.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Profile " + id + " does not exist"
                ));
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
