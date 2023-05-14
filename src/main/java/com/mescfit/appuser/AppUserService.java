package com.mescfit.appuser;

import com.mescfit.aws.buckets.BucketName;
import com.mescfit.aws.filestore.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final FileStoreService fileStoreService;
    @Value("${app.base.url}")
    private String baseURL;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User with email %s was not found", email)
                        ));
    }

    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id %s was not found", id)));
    }

    public AppUser registerUser(AppUser user) {
        boolean userExists = appUserRepository
                .findByEmail(user.getUsername())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("Email is already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        return appUserRepository.save(user);
    }

    public byte[] getUploadedProfilePicture(Long id, String key) {
        return fileStoreService.downloadFile(BucketName.USER_PROFILE_DEV, id, key);
    }

    public AppUser updateUser(Long userId, AppUser user) {
        // Assert that the user already exists
        AppUser existingUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id %s was not found", userId)));

        // Assert that the user id matches the one in the database
//        if (user.email() != null) {
//            // TODO: Once emails are ready send an email to verify the email update
//            existingUser.setEmail(user.email());
//            existingUser.setEnabled(false);
//        }

        if (user.getPassword() != null) {
            existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getTfa() != null) {
            existingUser.setTfa(user.getTfa());
        }

        if (user.getSubscribed() != null) {
            existingUser.setSubscribed(user.getSubscribed());
        }

        // Update the user and return the result
        return appUserRepository.save(existingUser);
    }

    public String getUserProfilePictureLink(Long id) {
        AppUser user = getUserById(id);
        return user.getProfileImageLink();
    }

    public void uploadUserProfilePicture(Long id, MultipartFile file) {
        fileStoreService.isFileEmpty(file);
        fileStoreService.isImage(file);
        AppUser user = getUserById(id);
        String key = fileStoreService.uploadFile(BucketName.EXERCISE_LIBRARY_DEV, id, file);
        String link = String.format("https://%s/users/%d/image/download/%s", baseURL, id, key);
        user.setProfileImageLink(link);
        appUserRepository.save(user);
    }
}
