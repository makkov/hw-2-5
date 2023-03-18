package org.skypro.hw.exception;

public class WrongEmployeeDataException extends RuntimeException{

    public WrongEmployeeDataException(String message) {
        super(message);
    }
}
