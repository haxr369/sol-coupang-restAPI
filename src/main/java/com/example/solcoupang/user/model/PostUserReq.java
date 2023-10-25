package com.example.solcoupang.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUserReq {
    private String email;
    private String password;
    private String name;

    public PostUserReq(final String email, final String password, final String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
