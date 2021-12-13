package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for catching the employee custom exception
 * which extends the runtime exception
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-10
 */
public class EmployeeCustomException extends RuntimeException {

    public EmployeeCustomException(String message) {
        super(message);
    }
}
