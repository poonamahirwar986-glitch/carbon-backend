package com.carbontracker.Service;

import com.carbontracker.DTO.*;
import com.carbontracker.Repository.AIChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.carbontracker.Entity.User;
import com.carbontracker.Repository.UserRepository;
import com.carbontracker.Service.UserService;
import com.carbontracker.Security.JwtService;
import com.carbontracker.DTO.ChangePasswordRequest;

@Service
public class UserserviceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AIChatRepository aiChatRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Override
    public UserResponse signup(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
        return new UserResponse(
                    null,
                    null,
                    null,
                    "Email already registered!",
                     null
            );
        }

        User user = new User();

        user.setFullName(signupRequest.getFullName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(
                passwordEncoder.encode(signupRequest.getPassword())
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "Account Created Successfully",
                null
        );

    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (user == null) {
            return new UserResponse(
                    null,
                    null,
                    null,
                    "User not found!",
                    null
            );
        }

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {
            return new UserResponse(
                    null,
                    null,
                    null,
                    "Invalid Password!",
                    null
            );
        }

        String token = jwtService.generateToken(user.getEmail());


        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                "Login Successful",
                token
        );
    }
    @Override
    public ProfileResponse getProfile(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        ProfileResponse response = new ProfileResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setCity(user.getCity());
        response.setCountry(user.getCountry());
        response.setProfileImage(user.getProfileImage());

        if (user.getCreatedAt() != null) {
            response.setJoinedDate(user.getCreatedAt().toLocalDate().toString());
        }

        return response;
    }

    @Override
    public UserResponse updateProfile(UpdateProfileRequest request) {


        System.out.println("ID = " + request.getId());
        System.out.println("Name = " + request.getFullName());
        System.out.println("Phone: " + request.getPhoneNumber());


        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setProfileImage(request.getProfileImage());

        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                "Profile Updated Successfully",
                null
        );
    }

    @Override
    public UserResponse changePassword(Long userId, ChangePasswordRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return new UserResponse(
                    null,
                    null,
                    null,
                    "Old password is incorrect",
                    null
            );
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "Password Changed Successfully",
                null
        );
    }
    @Override
    public UserResponse deleteAccount(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        aiChatRepository.deleteByUserId(id);

        userRepository.delete(user);

        return new UserResponse(
                null,
                null,
                null,
                "Account Deleted Successfully",
                null
        );
    }

    @Override
    public User updateSettings(Long userId, SettingsRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setNotificationEnabled(request.getNotificationEnabled());
        user.setCarbonGoal(request.getCarbonGoal());
        user.setTreeGoal(request.getTreeGoal());

        return userRepository.save(user);
    }

    @Override
    public User getSettings(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
