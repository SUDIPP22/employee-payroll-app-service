package com.bridgelabz.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Purpose : To contain the entities in the database
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@Entity
@Table(name = "employee_payroll")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private int empId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "IMAGE")
    private String imagePath;

    @Column(name = "START_DATE")
    private String joiningDate;

}
