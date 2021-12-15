package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Purpose : To invoke the data from client
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@Data
public class EmployeeDetailsDto {

    @NotNull(message = "First name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name is invalid")
    private String firstName;

    @NotNull(message = "Last name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Last name is invalid")
    private String lastName;

    @NotNull(message = "Gender field should not be empty")
    @Pattern(regexp = "Male|Female", message = "Gender Should be either Male or Female")
    private String gender;

    @NotNull(message = "Salary should not be empty")
    @Min(value = 10000, message = "Minimum wage should be more than 10000")
    private double salary;

    @NotNull(message = "Department name should not be empty")
    @Size(message = "Department name Should be within 50 letters", max = 50)
    private List<String> departments;

    @NotNull(message = "Notes should not be empty")
    @Size(message = "Notes should be within 150 letters", max = 150)
    private String notes;

    @NotBlank(message = "Image can not be empty")
    private String imagePath;

    @NotNull(message = "Joining date should not be empty")
    @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",
            message = "Date should be maintain the format : mm/dd/yyyy")
    private String joiningDate;
}
