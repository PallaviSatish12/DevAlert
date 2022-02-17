package com.decathlon.devalert.Exception;

public class DeveloperNotPresentException extends RuntimeException {

    private String message;

    public DeveloperNotPresentException(){

    }
    public DeveloperNotPresentException(String message){
        super(message);
        this.message = message;
    }

}
