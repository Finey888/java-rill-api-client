package com.rill.client;

public class RillRestException extends Exception {

    private Exception cause;

    public RillRestException(final String message){
        super(message);
    }

    public RillRestException(final String message, final Exception cause){
        super(message);
        this.cause = cause;
    }

    public Exception getCause(){
        return this.cause;
    }
}

    
    
    