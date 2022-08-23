package com.ciandt.summit.bootcamp2022.dto;

import lombok.Getter;

@Getter
public class UsernameDto {


    private Data data;

    public UsernameDto() {
    }

    public UsernameDto(Data data) {
        this.data = data;
    }
}
