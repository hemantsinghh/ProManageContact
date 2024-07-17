package com.promanagecontact.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    @NotBlank(message = "name should not be blank")
    private String name;
    
    @Column(name = "phone_number")
    @NotBlank(message = "phone number should not be blank")
    private String phoneNumber;
    
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\\\.[a-zA-Z]{2,})+$")
    private String email;
    
    @Column(name = "address")
    private String address;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact")
    List<SocialLink> socialLink =  new ArrayList<>();
}
