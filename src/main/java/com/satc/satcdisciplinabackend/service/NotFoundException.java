package com.satc.satcdisciplinabackend.service;

public class NotFoundException extends RuntimeException{
    public NotFoundException() { super("Registro não encontrado"); }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
