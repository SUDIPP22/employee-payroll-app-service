//package com.bridgelabz.builder;
//
//import com.bridgelabz.dto.EmployeeDetailsDto;
//import com.bridgelabz.entity.Employee;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class EmployeePayrollBuilderTest {
//
//    @InjectMocks
//    private EmployeePayrollBuilder employeePayrollBuilder;
//
//    @Test
//    void buildEntityTest() {
//        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
//        employeeDetailsDto.setFirstName("Sudip");
//        employeeDetailsDto.setLastName("Panja");
//        employeeDetailsDto.setGender("Male");
//        employeeDetailsDto.setSalary(800000.00);
//        employeeDetailsDto.setDepartments(List.of("Electrical"));
//        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
//        employeeDetailsDto.setJoiningDate("12/12/2021");
//        Employee employeeEntity = new Employee();
//        employeeEntity = employeePayrollBuilder.buildEmployeeEntity(employeeDetailsDto, employeeEntity);
//        assertEquals("Sudip", employeeEntity.getFirstName());
//        assertEquals("Panja", employeeEntity.getLastName());
//        assertEquals("Male", employeeEntity.getGender());
//        assertEquals(800000.00, employeeEntity.getSalary());
//        assertEquals(employeeDetailsDto.getDepartments(), employeeEntity.getDepartments());
//        assertEquals("Reliable,Adaptable,Self-Manageable,Communicative", employeeEntity.getNotes());
//        assertEquals("12/12/2021", employeeEntity.getJoiningDate());
//    }
//}
