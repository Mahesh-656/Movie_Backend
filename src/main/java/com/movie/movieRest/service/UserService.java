package com.movie.movieRest.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.movie.movieRest.DTO.UserProfileUpdatedRequest;
import com.movie.movieRest.Repo.UserRepository;
import com.movie.movieRest.model.Role;
import com.movie.movieRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//updates user profiles

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    public User updateUser(String email, UserProfileUpdatedRequest request, MultipartFile image) throws IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

        user.setBio(request.getBio());
        user.setFavouriteGenres(request.getFavouriteGenres());

        if (image != null && !image.isEmpty()) {
            //Delete old image
            if (user.getProfilePicturePublicId() != null) {
                cloudinary.uploader().destroy(user.getProfilePicturePublicId(), ObjectUtils.emptyMap());
            }
//            Upload new profile image
            Map upload = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            user.setProfilePicturePublicId(upload.get("public_id").toString());
            user.setProfileUrl(upload.get("secure_url").toString());
        }
        return userRepository.save(user);
    }
    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User promoteToAdmin(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
