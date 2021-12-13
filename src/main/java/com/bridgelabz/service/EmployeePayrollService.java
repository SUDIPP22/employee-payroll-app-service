package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;
import com.bridgelabz.exception.custom.BadRequestException;
import com.bridgelabz.exception.custom.EmployeeNotFoundException;
import com.bridgelabz.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose : To demonstrate business logic which implements all the methods in controller layer
 * for EmployeeDetailsDto Payroll Application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    private static final String EMPLOYEE_DATA_DELETED_SUCCESSFULLY = "Employee details are successfully deleted by id :";
    private static final String EMPLOYEE_DATA_NOT_FOUND = "Employee details are not found by this id :";
    private static final String EMPLOYEE_DATA_ADDED_SUCCESSFULLY = "Employee details are added successfully!!!";
    private static final String EMPLOYEE_DATA_UPDATED_SUCCESSFULLY = "Employee details are updated successfully!!!";
    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;


    /**
     * Purpose : This method is used to get back the list of employee details
     *
     * @return the employee entity
     */
    @Override
    public List<Employee> getAllEmployeeData() {
        return employeePayrollRepository.findAll();
    }

    /**
     * Purpose : This method is used to get back the corresponding employee details by respective employee id
     *
     * @param employeeId : takes the employee id of that particular employee entity
     * @return the employee entity using the employee id
     */
    @Override
    public Employee findEmployeeDataById(int employeeId) {
        return employeePayrollRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_DATA_NOT_FOUND + " " + employeeId));
    }

    /**
     * Purpose : This method is used to add the employee details by using of employee DTO class
     *
     * @param employeeDetailsDto : takes the employee details as DTO to provide the repository for storing in database
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDto addEmployeeEntities(EmployeeDetailsDto employeeDetailsDto) {
        if (employeeDetailsDto == null) {
            throw new BadRequestException("Employee details are null");
        }
        Employee employeeEntity = new Employee();
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDetailsDto, employeeEntity);
        employeePayrollRepository.save(employeeEntity);
        return new ResponseDto(HttpStatus.CREATED, EMPLOYEE_DATA_ADDED_SUCCESSFULLY);
    }

    /**
     * Purpose : This method is used to update the employee details by using their respective employee id
     *
     * @param employeeId         : takes the employee id for updating that particular employee entity
     * @param employeeDetailsDto : takes the updated employee details as DTO
     *                           to provide the repository for storing in database
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDto updateEmployeeData(int employeeId, EmployeeDetailsDto employeeDetailsDto) {
        Employee employeeEntity = findEmployeeDataById(employeeId);
        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDetailsDto, employeeEntity);
        employeePayrollRepository.save(employeeEntity);
        return new ResponseDto(HttpStatus.ACCEPTED, EMPLOYEE_DATA_UPDATED_SUCCESSFULLY);
    }

    /**
     * Purpose : This method is used to delete the employee details by using the respective employee id
     *
     * @param employeeId : takes the employee id for deleting that particularly employee entity
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDto deleteEmployeeDataById(int employeeId) {
        Employee employee = findEmployeeDataById(employeeId);
        employeePayrollRepository.deleteById(employeeId);
        return new ResponseDto(HttpStatus.ACCEPTED, EMPLOYEE_DATA_DELETED_SUCCESSFULLY + " " + employeeId);
    }

}
