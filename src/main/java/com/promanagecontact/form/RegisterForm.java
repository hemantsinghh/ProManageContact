package com.promanagecontact.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
}
