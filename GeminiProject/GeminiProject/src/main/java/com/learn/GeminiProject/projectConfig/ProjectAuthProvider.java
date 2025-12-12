package com.learn.GeminiProject.projectConfig;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProjectAuthProvider implements AuthenticationProvider {
    private final GeminiProjectUserDetailsService GUserDS;
    private final PasswordEncoder passwordEncoder;
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = GUserDS.loadUserByUsername(username);

        if(passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
