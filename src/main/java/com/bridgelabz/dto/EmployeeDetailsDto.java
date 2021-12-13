package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Purpose : To invoke the data from client
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@Data
public class EmployeeDetailsDto {

    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name is invalid")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Last name is invalid")
    private String lastName;

    @Size(message = "Characters should be within 10 letters", max = 10)
    private String gender;

    private double salary;

    @Size(message = "Characters Should be within 50 letters", max = 50)
    private String department;

    @Size(message = "Notes should be within 150 letters", max = 150)
    private String notes;

    @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",
            message = "Date should be maintain the format : mm-dd-yyyy")
    private String joiningDate;
}
