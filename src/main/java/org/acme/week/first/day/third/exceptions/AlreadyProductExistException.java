package org.acme.week.first.day.third.exceptions;

public class AlreadyProductExistException extends RuntimeException{
    public AlreadyProductExistException(String message){
        super(message);
    }
}
