package com.example.solcoupang.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserRes {
    private int id;
    private String email;
    private String password;
    private String name;

    public GetUserRes(final int id, final String email, final String password, final String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
