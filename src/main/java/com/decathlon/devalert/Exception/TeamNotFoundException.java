package com.decathlon.devalert.Exception;

public class TeamNotFoundException extends RuntimeException{

    private String message;

    public TeamNotFoundException(){

    }
    public TeamNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
