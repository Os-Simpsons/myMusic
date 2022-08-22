package com.ciandt.summit.bootcamp2022.utils;

import com.ciandt.summit.bootcamp2022.client.TokenFeignClient;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.repositories.UserRepository;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenService {

    @Autowired
    private TokenFeignClient tokenFeignClient;

    @Autowired
    private UserRepository userRepository;

    public String createToken(UsernameDto usernameDto) throws InvalidLogDataException {

        if(!validateUserName(usernameDto.getName())) {
            throw new InvalidLogDataException("Invalid Username!");
        }
        return tokenFeignClient.createToken(usernameDto);
    }

    public void validateToken(UsernameDto usernameDto) throws InvalidLogDataException {
        //if pessoa existe _> if token ta correto -> true
        //else return false;
        if(!validateUserName(usernameDto.getName())) {
            throw new InvalidLogDataException("Invalid Username!");
        }
        if(usernameDto.getToken()!=tokenFeignClient.authorizeToken(usernameDto)) {
            throw new InvalidLogDataException("Invalid Token!");
        }
    }

    public boolean validateUserName(String username) {
        return userRepository.getByNome(username).isPresent();
    }



}
