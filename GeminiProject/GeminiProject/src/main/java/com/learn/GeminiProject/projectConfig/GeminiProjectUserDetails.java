package com.learn.GeminiProject.projectConfig;

// File: com.learn.GeminiProject.security.GeminiProjectUserDetails.java



import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public @NullMarked class GeminiProjectUserDetails implements UserDetails {

    // *** IMPORTANT: Add the public getter for ID ***
    @Getter
    private final Long id; // <--- The new field for the ID
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public GeminiProjectUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // --- Standard UserDetails Interface Methods (Implement/Delegate) ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Add other methods like isAccountNonExpired(), etc. (You can hardcode them to return true for simplicity)

    // ... (omitted for brevity)
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
