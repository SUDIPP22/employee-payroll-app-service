package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;
import com.bridgelabz.service.EmployeePayrollService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {

    @InjectMocks
    private EmployeePayrollController employeePayrollController;

    @Mock
    private EmployeePayrollService employeePayrollService;

    @Test
    void givenEmployeeDetails_WhenAdded_ThenShouldAddEmployeeDetailsAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDto> successResponseMessage = new ResponseEntity<>
                ((new ResponseDto(HttpStatus.CREATED, "Employee details are added successfully!!!")),
                        HttpStatus.CREATED);
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(800000.00);
        employeeDetailsDto.setDepartment("Electrical");
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setJoiningDate("12/12/2021");
        when(employeePayrollService.addEmployeeEntities(employeeDetailsDto))
                .thenReturn(new ResponseDto(HttpStatus.CREATED,
                        "Employee details are added successfully!!!"));
        ResponseEntity<ResponseDto> actualResponseMessage =
                employeePayrollController.addEmployeeData(employeeDetailsDto);
        assertEquals(successResponseMessage, actualResponseMessage);
    }

    @Test
    void givenEmployeeDetails_WhenGetEmployeeDetails_ThenShouldReturnListOfEmployeeDetails() {
        when(employeePayrollService.getAllEmployeeData())
                .thenReturn(Lists.newArrayList(new Employee()));
        ResponseEntity<List<Employee>> actualResponse =
                employeePayrollController.getEmployeeDetails();
        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1, actualResponse.getBody().size());
    }

    @Test
    void givenEmployeeDetails_WhenUpdateEmployeeDetailsById_ThenShouldUpdateAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDto> successResponseMessage = new ResponseEntity<>
                ((new ResponseDto(HttpStatus.ACCEPTED,
                        "Employee details are updated successfully!!!")), HttpStatus.ACCEPTED);
        int empId = 1;
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(800000.00);
        employeeDetailsDto.setDepartment("Electrical");
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setJoiningDate("12/12/2021");
        when(employeePayrollService.updateEmployeeData(empId, employeeDetailsDto))
                .thenReturn(new ResponseDto(HttpStatus.ACCEPTED,
                "Employee details are updated successfully!!!"));
        ResponseEntity<ResponseDto> actualResponseMessage =
                employeePayrollController.updateEmployeeDetails(empId, employeeDetailsDto);
        assertEquals(successResponseMessage, actualResponseMessage);
    }

    @Test
    void givenEmployeeDetails_WhenDeletedEmployeeDetailsById_ThenShouldDeleteAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDto> successResponseMessage = new ResponseEntity<>
                ((new ResponseDto(HttpStatus.ACCEPTED,
                        "Employee details are deleted successfully!!!")), HttpStatus.ACCEPTED);
        int empId = 1;
        when(employeePayrollService.deleteEmployeeDataById(empId))
                .thenReturn(new ResponseDto(HttpStatus.ACCEPTED,
                        "Employee details are deleted successfully!!!"));
        ResponseEntity<ResponseDto> actualResponseMessage =
                employeePayrollController.deleteEmployeeDetails(empId);
        assertEquals(successResponseMessage, actualResponseMessage);
    }
}
