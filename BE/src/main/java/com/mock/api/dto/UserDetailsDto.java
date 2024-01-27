package com.mock.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mock.api.entities.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsDto implements UserDetails {

    private static final long serialVersionUID = 2396654715019746670L;

    private Long userId;
    private String username;
    private String email;
    private List<String> roles;
    private String password;
    private final List<GrantedAuthority> grantedAuthorities;

    public UserDetailsDto(Long userId, String username, String password, String email, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.grantedAuthorities = roles == null ? Collections.emptyList() : roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public UserDetailsDto(Long userId, String username, String password, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.grantedAuthorities = roles == null ? Collections.emptyList() : roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static UserDetailsDto build(UserModel user) {

        List<GrantedAuthority> authorities = Collections.emptyList();
        //TODO
//        List<GrantedAuthority> authorities = user.getRoles() == null ? Collections.emptyList() :
//                user.getRoles().stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());

        return new UserDetailsDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                null);
    }

    @JsonIgnore
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
