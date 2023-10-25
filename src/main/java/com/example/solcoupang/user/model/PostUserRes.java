package com.example.solcoupang.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUserRes {
    private int id;
    public PostUserRes(final int id) {
        this.id = id;
    }
}
