package com.exemple.spring.exceptions;

public class BadConnectionServiceException{
    private String str;

    public BadConnectionServiceException() {
    }

    public BadConnectionServiceException(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
