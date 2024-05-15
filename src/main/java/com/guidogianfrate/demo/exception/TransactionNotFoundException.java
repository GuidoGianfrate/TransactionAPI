package com.guidogianfrate.demo.exception;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(){
        super("Transaction does not exist");
    }
}
