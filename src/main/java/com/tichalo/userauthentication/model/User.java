package com.tichalo.userauthentication.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter @Accessors(chain = true)
public class User {
    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;
}
