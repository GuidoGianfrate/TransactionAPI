package com.guidogianfrate.demo.exception;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(String message){
        super(message);
    }
}
