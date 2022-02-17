package com.decathlon.devalert.Exception;

public class DBException extends RuntimeException {
    private String message;

    public DBException(){

    }
    public DBException(String message){
        super(message);
        this.message = message;
    }
}
