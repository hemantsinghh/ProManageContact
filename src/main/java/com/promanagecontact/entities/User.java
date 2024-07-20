package com.promanagecontact.entities;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "name",nullable = false)
    @NotEmpty(message = "please enter name")
    private String name;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @NotBlank(message = "email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z]{2,})+$")
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @NotBlank(message = "about field cannot be blank")
    private String about;
    
    @Column(name ="profile_pic")
    private String profilePic;
    
    @Column(name = "enabled")
    private boolean enabled = false;
    
    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "provider")
    @Enumerated(value = EnumType.STRING)
    private Provider provider = Provider.SELF;
    
    @Column(name = "provider_id")
    private String providerId;

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY)
    List<Contact> contact = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList =  new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles{admin, user}
        // collection of SimpleGrantedAuthority
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public boolean isEnabled(){
        return true;
    }

}
