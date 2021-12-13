package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for generating catching the employee not found exception
 * which extends the EmployeeCustomException class
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-10
 */
public class EmployeeNotFoundException extends EmployeeCustomException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
