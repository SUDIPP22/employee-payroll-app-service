package com.bridgelabz.service;

import com.bridgelabz.builder.EmployeePayrollBuilder;
import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;
import com.bridgelabz.exception.custom.BadRequestException;
import com.bridgelabz.exception.custom.EmployeeNotFoundException;
import com.bridgelabz.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollServiceTest {

    @InjectMocks
    private EmployeePayrollService employeePayrollService;

    @Mock
    private EmployeePayrollRepository employeePayrollRepository;

    @Mock
    private EmployeePayrollBuilder employeePayrollBuilder;

    @Test
    void whenGetAllEmployeeDataCalled_ShouldReturnTheListOfEmployee() {
        List<Employee> employeeEntityList = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setFirstName("Sudip");
        employee1.setLastName("Panja");
        employee1.setGender("Male");
        employee1.setSalary(800000.00);
        employee1.setDepartments(List.of("Electrical"));
        employee1.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employee1.setImagePath("image1.jpg");
        employee1.setJoiningDate("10/12/2021");
        employeeEntityList.add(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("David");
        employee2.setLastName("Williams");
        employee2.setGender("Male");
        employee2.setSalary(200000.00);
        employee2.setDepartments(List.of("IT"));
        employee2.setNotes("Reliable,Communicative");
        employee2.setImagePath("image2.jpg");
        employee2.setJoiningDate("11/12/2021");
        employeeEntityList.add(employee2);

        when(employeePayrollRepository.findAll()).thenReturn(employeeEntityList);
        List<Employee> actualListOfEmployeeEntity = employeePayrollService.getAllEmployeeData();
        assertEquals(2, actualListOfEmployeeEntity.size());
        assertEquals(employeeEntityList, actualListOfEmployeeEntity);
    }

    @Test
    void WhenFindEmployeeDetailsByIdCalled_ThenIfIdIsNotFound_ShouldThrowException() {
        int employeeId = 1;
        when(employeePayrollRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeePayrollService.findEmployeeDataById(employeeId));
    }

    @Test
    void whenAddEmployeeEntitiesCalled_ShouldAddEmployeeDetailsAndReturnResponseAndGenerateSuccessMessage() {
        Employee employee = new Employee();

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(800000.00);
        employeeDetailsDto.setDepartments(List.of("Electrical"));
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setImagePath("image1.jpg");
        employeeDetailsDto.setJoiningDate("12/12/2021");

        when(employeePayrollBuilder.buildEmployeeEntity(employeeDetailsDto, employee)).thenReturn(employee);
        ResponseDto actualResponse = employeePayrollService.addEmployeeEntities(employeeDetailsDto);
        ResponseDto expectedResponse = new ResponseDto(HttpStatus.CREATED,
                "Employee details are added successfully!!!");

        assertEquals(expectedResponse, actualResponse);
        verify(employeePayrollRepository, times(1)).save(employee);
    }

    @Test
    void whenAddEmployeeEntitiesCalled_ThenIfEmployeeDetailsAreNull_ShouldThrowException() {
        Employee employee = new Employee();
        EmployeeDetailsDto employeeDetailsDto = null;
        assertThrows(BadRequestException.class, () -> employeePayrollService.addEmployeeEntities(employeeDetailsDto));
        verify(employeePayrollRepository, times(0)).save(employee);
    }

    @Test
    void whenUpdateEmployeeDataByIdCalled_ShouldUpdateEmployeeAndReturnResponseAndGenerateSuccessMessage() {
        int employeeId = 1;
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(500000.00);
        employeeDetailsDto.setDepartments(List.of("Electrical"));
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setImagePath("image2.jpg");
        employeeDetailsDto.setJoiningDate("12/12/2021");

        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setFirstName("Sudip");
        employee.setLastName("Panja");
        employee.setGender("Male");
        employee.setSalary(800000.00);
        employee.setDepartments(List.of("IT"));
        employee.setNotes("Reliable,Adaptable,Self-Manageable,HardWorking");
        employee.setImagePath("image1.jpg");
        employee.setJoiningDate("12/12/2021");

        when(employeePayrollRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        employee.setSalary(employeeDetailsDto.getSalary());
        employee.setDepartments(employeeDetailsDto.getDepartments());
        employee.setImagePath(employeeDetailsDto.getImagePath());
        employee.setNotes(employeeDetailsDto.getNotes());

        when(employeePayrollBuilder.buildEmployeeEntity(employeeDetailsDto, employee)).thenReturn(employee);
        ResponseDto actualResponse = employeePayrollService.updateEmployeeData(employeeId, employeeDetailsDto);
        ResponseDto expectedResponse = new ResponseDto(HttpStatus.ACCEPTED,
                "Employee details are updated successfully!!!");
        verify(employeePayrollRepository, times(1)).save(employee);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(employeeDetailsDto.getSalary(), employee.getSalary());
        assertEquals(employeeDetailsDto.getDepartments(), employee.getDepartments());
        assertEquals(employeeDetailsDto.getImagePath(), employee.getImagePath());
        assertEquals(employeeDetailsDto.getNotes(), employee.getNotes());
    }

    @Test
    void whenUpdateEmployeeDataByIdCalled_ThenIfFindById_ShouldThrowException() {
        int employeeId = 1;

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(500000.00);
        employeeDetailsDto.setDepartments(List.of("Electrical"));
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setImagePath("image1.jpg");
        employeeDetailsDto.setJoiningDate("12/12/2021");

        when(employeePayrollRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                ()-> employeePayrollService.updateEmployeeData(employeeId, employeeDetailsDto));
    }

    @Test
    void whenDeleteEmployeeDataByIdCalled_ShouldDeleteEmployeeAndReturnResponseAndGenerateSuccessMessage() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setFirstName("Sudip");
        employee.setLastName("Panja");
        employee.setGender("Male");
        employee.setSalary(800000.00);
        employee.setDepartments(List.of("IT"));
        employee.setNotes("Reliable,Adaptable,Self-Manageable,HardWorking");
        employee.setImagePath("image2.jpg");
        employee.setJoiningDate("12/12/2021");

        when(employeePayrollRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        ResponseDto actualResponse = employeePayrollService.deleteEmployeeDataById(employee.getEmpId());
        verify(employeePayrollRepository).deleteById(employee.getEmpId());
        ResponseDto expectedResponse = new ResponseDto(HttpStatus.ACCEPTED,
                "Employee details are successfully deleted by id : " +employee.getEmpId());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenDeleteEmployeeDataByIdCalled_ThenIfFindById_ShouldThrowException() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setFirstName("Sudip");
        employee.setLastName("Panja");
        employee.setGender("Male");
        employee.setSalary(800000.00);
        employee.setDepartments(List.of("IT"));
        employee.setNotes("Reliable,Adaptable,Self-Manageable,HardWorking");
        employee.setImagePath("image3.jpg");
        employee.setJoiningDate("12/12/2021");
        when(employeePayrollRepository.findById(employee.getEmpId())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                ()-> employeePayrollService.deleteEmployeeDataById(employee.getEmpId()));
    }
}
