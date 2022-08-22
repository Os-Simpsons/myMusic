package com.ciandt.summit.bootcamp2022.dto;

import lombok.Getter;

@Getter
public class UsernameDto {

    private String name;
    private String token;

    public UsernameDto() {
    }
    public UsernameDto(String name) {
        this.name = name;
    }

    public UsernameDto(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
