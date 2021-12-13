package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;
import com.bridgelabz.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : To demonstrate different HTTP methods
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeePayrollController {

    @Autowired
    EmployeePayrollService employeePayrollService;

    /**
     * Purpose : This API(Application programming Interface) is created for registering new employee data into system
     *
     * @param employeeDetailsDto : takes the details of employee using POJO class of employee by using @RequestBody
     * @return the new response entity which is response object and Http status in that entity
     */
    @PostMapping(value = "/detail")
    public ResponseEntity<ResponseDto> addEmployeeData(
            @Valid
            @RequestBody EmployeeDetailsDto employeeDetailsDto) {
        return new ResponseEntity<>
                (employeePayrollService.addEmployeeEntities(employeeDetailsDto), HttpStatus.CREATED);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for getting the list of the employee
     *
     * @return the new response entity which is holding the list and Http status in that entity
     */
    @GetMapping(value = "/details")
    public ResponseEntity<List<Employee>> getEmployeeDetails() {
        return new ResponseEntity<>(employeePayrollService.getAllEmployeeData(), HttpStatus.OK);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for getting the particular employee details
     * by using their employee id
     *
     * @param employeeId : takes the employee id of corresponding employee
     * @return the new response entity which is holding the employee details and Http status in that entity
     */
    @GetMapping(value = "/detail/{employeeId}")
    public ResponseEntity<Employee> getEmployeeDataById(
            @PathVariable int employeeId
    ) {
        return new ResponseEntity<>(employeePayrollService.findEmployeeDataById(employeeId), HttpStatus.OK);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for update the employee details
     * according to their respective employee id
     *
     * @param employeeId         : takes the employee id of corresponding employee
     * @param employeeDetailsDto : takes the details of employee using POJO class of employee by using @RequestBody
     * @return the new response entity which holds the update message and the Http status
     */
    @PutMapping(value = "/detail/{employeeId}")
    public ResponseEntity<ResponseDto> updateEmployeeDetails(
            @PathVariable int employeeId,
            @Valid
            @RequestBody EmployeeDetailsDto employeeDetailsDto
    ) {
        return new ResponseEntity<>
                (employeePayrollService.updateEmployeeData(employeeId, employeeDetailsDto), HttpStatus.ACCEPTED);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for deleting the employee details
     * according to their respective employee id
     *
     * @param employeeId : takes the employee id of corresponding employee
     * @return the new response entity which is holding the deletion message and Http status
     */
    @DeleteMapping(value = "/details/{employeeId}")
    public ResponseEntity<ResponseDto> deleteEmployeeDetails(
            @PathVariable int employeeId
    ) {
        return new ResponseEntity<>(employeePayrollService.deleteEmployeeDataById(employeeId), HttpStatus.ACCEPTED);
    }
}
