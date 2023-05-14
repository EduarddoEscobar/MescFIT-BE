package com.mescfit.appuser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/image/download/{key}")
    public byte[] getUploadedUserProfilePicture(
            @PathVariable Long userId,
            @PathVariable String key) {
        return appUserService.getUploadedProfilePicture(userId, key);
    }

    @GetMapping("/{userId}/image/download")
    public String getUserProfilePictureLink(@PathVariable Long userId) {
        return appUserService.getUserProfilePictureLink(userId);
    }

    @PostMapping(
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> uploadUserProfilePicture(HttpServletRequest request,
                                                           @RequestParam("file") MultipartFile file) {
        AppUser user = (AppUser) request.getAttribute("user");
        appUserService.uploadUserProfilePicture(user.getUserId(), file);
        return ResponseEntity.ok().body("Image uploaded");
    }

    @GetMapping
    public AppUser getAppUser(HttpServletRequest request) {
        AppUser user = (AppUser) request.getAttribute("user");
        return appUserService.getUserById(user.getUserId());
    }

    @PutMapping
    public AppUser updateAppUser(HttpServletRequest request, @RequestBody AppUser appUser) {
        AppUser user = (AppUser) request.getAttribute("user");
        return appUserService.updateUser(user.getUserId(), appUser);
    }
}
