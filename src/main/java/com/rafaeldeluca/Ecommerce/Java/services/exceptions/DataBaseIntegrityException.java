package com.rafaeldeluca.Ecommerce.Java.services.exceptions;

public class DataBaseIntegrityException extends  RuntimeException {

    public DataBaseIntegrityException(String mensagem) {
        super (mensagem);
    }
}
