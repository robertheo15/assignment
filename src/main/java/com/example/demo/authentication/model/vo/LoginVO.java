package com.example.demo.authentication.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVO {

    private String usernameOrEmail;

    private String password;
}
