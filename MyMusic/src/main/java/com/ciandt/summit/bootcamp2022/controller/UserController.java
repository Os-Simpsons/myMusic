package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.utils.TokenService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")//mudar endpoint
public class UserController {

    @Autowired
    TokenService tokenFeignClient;

    @PostMapping
    public String getToken(@RequestBody UsernameDto username) throws InvalidLogDataException {
        return tokenFeignClient.createToken(username);
    }
}
