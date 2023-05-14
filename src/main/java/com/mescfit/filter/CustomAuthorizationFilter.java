package com.mescfit.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mescfit.appuser.AppUserService;
import com.mescfit.util.TokenValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final TokenValidator tokenValidator = new TokenValidator();
    private final AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/v1/auth/login") ||
                request.getServletPath().equals("/api/v1/auth/register") ||
                request.getServletPath().equals("/api/v1/auth/token/refresh") ||
                request.getServletPath().equals("/api/v1/auth/google")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                // Get the token from the header
                String token = authorizationHeader.substring("Bearer ".length());

                // Verify and Decode the token
                DecodedJWT decodedJWT = tokenValidator.verify(token);
                String username = decodedJWT
                        .getSubject();
                String role = decodedJWT.getClaim("roles").toString();

                // Make an authentication token with the roles and username from the token
                Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // Store the user_id on the request
                request.setAttribute("user", appUserService.getUserByEmail(username).orElse(null));

                // Continue the filter chain
                filterChain.doFilter(request, response);
                return;
            } catch (Exception e) {
                tokenValidator.tokenException(e, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}
