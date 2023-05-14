package com.mescfit.config;

import com.mescfit.appuser.AppUserService;
import com.mescfit.filter.CustomAuthenticationFilter;
import com.mescfit.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AppUserService appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager(appUserService, bCryptPasswordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter(appUserService);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManager(), appUserService
        );
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/testimonials/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/testimonials/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/testimonials/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/testimonials/**").hasAuthority("ADMIN")
                .antMatchers("/api/v1/exercise-categories").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(customAuthenticationFilter);
        return http.build();
    }
}
