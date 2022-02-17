package com.decathlon.devalert.Exception;

public class AlertFailureException extends RuntimeException {
    private String message;

    public AlertFailureException(){

    }
    public AlertFailureException(String message){
        super(message);
        this.message = message;
    }
}
