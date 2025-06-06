package com.campuslands.grocery_management.exceptions;

public class ProductNotFountException extends RuntimeException{

    public ProductNotFountException(String message){
        super(message);
    }
}
