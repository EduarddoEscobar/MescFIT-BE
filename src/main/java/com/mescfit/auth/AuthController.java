package com.mescfit.auth;

import com.mescfit.appuser.AppUser;
import com.mescfit.appuser.AppUserDTO;
import com.mescfit.util.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenValidator tokenValidator;


    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        AppUser newUser = authService.register(request);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/auth/register")
                .toUriString());
        String accessToken = tokenValidator.generateAccessToken(newUser);
        String refreshToken = tokenValidator.generateRefreshToken(newUser);
        RegistrationResponse response = new RegistrationResponse(newUser, accessToken, refreshToken);
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/google")
    public void continueWithGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.continueWithGoogle(request, response);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

    @PostMapping("/")
    public void resetPassword(@RequestBody PasswordResetRequest request) {

    }
}
