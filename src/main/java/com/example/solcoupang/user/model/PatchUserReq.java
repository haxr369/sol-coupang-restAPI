package com.example.solcoupang.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchUserReq {
    private int id;
    private String name;


    public PatchUserReq(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
}