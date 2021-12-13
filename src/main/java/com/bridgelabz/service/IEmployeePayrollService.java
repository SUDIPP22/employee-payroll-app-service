package com.bridgelabz.service;

import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;

import java.util.List;

/**
 * Purpose : This is the interface of employee payroll service by which all the process of service class will occur
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-11
 */
public interface IEmployeePayrollService {
    List<Employee> getAllEmployeeData();

    Employee findEmployeeDataById(int employeeId);

    ResponseDto addEmployeeEntities(EmployeeDetailsDto employeeDetailsDto);

    ResponseDto updateEmployeeData(int employeeId, EmployeeDetailsDto employeeDetailsDto);

    ResponseDto deleteEmployeeDataById(int employeeId);
}
