package com.learn.GeminiProject.projectConfig;


import com.learn.GeminiProject.repository.UserRepo;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public @NullMarked class GeminiProjectUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo; 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         com.learn.GeminiProject.models.User
                user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not registered"));
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getRole()));

        return new GeminiProjectUserDetails(user.getId(),user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
