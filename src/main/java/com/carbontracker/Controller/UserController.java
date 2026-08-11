package com.carbontracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carbontracker.DTO.LoginRequest;
import com.carbontracker.DTO.SignupRequest;
import com.carbontracker.DTO.UserResponse;
import com.carbontracker.Service.UserService;
import com.carbontracker.DTO.ProfileResponse;
import com.carbontracker.DTO.UpdateProfileRequest;
import com.carbontracker.DTO.ChangePasswordRequest;
import jakarta.validation.Valid;
import com.carbontracker.DTO.SettingsRequest;
import com.carbontracker.Entity.User;

import com.carbontracker.DTO.SettingsRequest;
import com.carbontracker.Entity.User;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody SignupRequest signupRequest) {

        return userService.signup(signupRequest);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest);
    }

    // ================= GET PROFILE =================

    @GetMapping("/profile/{id}")
    public ProfileResponse getProfile(@PathVariable Integer id) {

        return userService.getProfile(id);

    }
    // UPDATE PROFILE

    @PutMapping("/update")
    public UserResponse updateProfile(@RequestBody UpdateProfileRequest request) {

        return userService.updateProfile(request);

    }

    @PutMapping("/change-password/{id}")
    public UserResponse changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {

        return userService.changePassword(id, request);

    }
    @DeleteMapping("/delete/{id}")
    public UserResponse deleteAccount(
            @PathVariable Integer id) {

        return userService.deleteAccount(id);

    }

    @PutMapping("/settings/{id}")
    public User updateSettings(
            @PathVariable Long id,
            @RequestBody SettingsRequest request) {

        return userService.updateSettings(id, request);
    }

    @GetMapping("/settings/{id}")
    public User getSettings(@PathVariable Long id) {

        return userService.getSettings(id);
    }

}
