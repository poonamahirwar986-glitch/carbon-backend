package com.carbontracker.Service;

import com.carbontracker.DTO.LoginRequest;
import com.carbontracker.DTO.ProfileResponse;
import com.carbontracker.DTO.SignupRequest;
import com.carbontracker.DTO.UserResponse;
import com.carbontracker.DTO.UpdateProfileRequest;
import com.carbontracker.DTO.ChangePasswordRequest;
import com.carbontracker.DTO.SettingsRequest;
import com.carbontracker.Entity.User;

public interface UserService {

    UserResponse signup(SignupRequest signupRequest);

    UserResponse login(LoginRequest loginRequest);

    ProfileResponse getProfile(Integer id);

    UserResponse updateProfile(UpdateProfileRequest request);

    UserResponse changePassword( Long userId , ChangePasswordRequest request);

    UserResponse deleteAccount(Integer id);

    User updateSettings(Long userId, SettingsRequest request);

    User getSettings(Long userId);
}
