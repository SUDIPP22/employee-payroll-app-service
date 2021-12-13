package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for catching the bad request exception
 * which extends the EmployeeCustomException class
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-10
 */
public class BadRequestException extends EmployeeCustomException{

    public BadRequestException(String message) {
        super(message);
    }
}
