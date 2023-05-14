package com.mescfit.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mescfit.appuser.AppUser;
import com.mescfit.appuser.AppUserService;
import com.mescfit.appuser.roles.AppUserRoleService;
import com.mescfit.util.EmailValidator;
import com.mescfit.util.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final TokenValidator tokenValidator;
    private final AppUserRoleService appUserRoleService;
    @Value("${app.google.clientId}")
    private String googleClientId;

    public AppUser register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.isValidEmail(request.email());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        return appUserService.registerUser(new AppUser(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                appUserRoleService.getRoleByName("CLIENT"),
                request.picture()
        ));
    }

    public Boolean sendConfirmationEmail(String to) {
        // TODO: Send an email to whoever registers or tries to sign in to unconfirmed account
        return false;
    }

    public void continueWithGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the token from the request
        String token = request.getParameter("credential");

        // Decode JWT and get the necessary claims
        DecodedJWT decodedJWT = tokenValidator.decode(token);
        String clientId = getClaim(decodedJWT, "aud");
        String firstName = getClaim(decodedJWT, "given_name");
        String lastName = getClaim(decodedJWT, "family_name");
        String email = getClaim(decodedJWT, "email");
        String picture = getClaim(decodedJWT, "picture");

        // Check if the JWT is from one of MescFITs client ids
        if (!clientId.equals(googleClientId)) {
            throw new IllegalStateException("Tokens clientId did not match");
        }

        // Check if user already exists if so give him the access and refresh tokens
        Map<String, String> json = new HashMap<>();

        if (appUserService.getUserByEmail(email).isEmpty()) {
            AppUser user = register(new RegistrationRequest(
                    firstName,
                    lastName,
                    email,
                    token,
                    picture,
                    false,
                    false
            ));
            putAppUserInJson(user, json);

            response.setStatus(CREATED.value());
        } else {
            UserDetails userDetails = appUserService.loadUserByUsername(email);
            String accessToken = tokenValidator.generateAccessToken(userDetails);
            String refreshToken = tokenValidator.generateRefreshToken(userDetails);
            json.put("access_token", accessToken);
            json.put("refresh_token", refreshToken);
            response.setContentType(APPLICATION_JSON_VALUE);
        }
        new ObjectMapper().writeValue(response.getOutputStream(), json);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = tokenValidator.verify(refreshToken);
                String email = decodedJWT.getSubject();
                UserDetails user = appUserService.loadUserByUsername(email);
                String accessToken = tokenValidator.generateAccessToken(user);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                tokenValidator.tokenException(e, response);
            }
        } else {
            throw new IllegalStateException("refresh_token was not found in the authorization header");
        }
    }

    public void putAppUserInJson(AppUser user, Map<String, String> json) {
        String accessToken = tokenValidator.generateAccessToken(user);
        String refreshToken = tokenValidator.generateRefreshToken(user);
        json.put("user_id", user.getUserId() + "");
        json.put("first_name", user.getFirstName());
        json.put("last_name", user.getLastName());
        json.put("email", user.getEmail());
        json.put("role", String.valueOf(user.getRoles()));
        json.put("access_token", accessToken);
        json.put("refresh_token", refreshToken);
    }

    public String getClaim(DecodedJWT decodedJWT, String claim) {
        String claimStr = decodedJWT.getClaim(claim).toString();
        return claimStr.substring(1, claimStr.length() - 1);
    }
}
