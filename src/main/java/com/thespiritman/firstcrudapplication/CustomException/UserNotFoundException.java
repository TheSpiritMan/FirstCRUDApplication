package com.thespiritman.firstcrudapplication.CustomException;

public class UserNotFoundException extends  Throwable{
    public UserNotFoundException(String message) {
        super(message);
    }
}

