package com.ciandt.summit.bootcamp2022.service.exceptions;

public class MusicAlreadyExistException extends RuntimeException{

    public MusicAlreadyExistException(String msg) {
        super(msg);
    }
}
