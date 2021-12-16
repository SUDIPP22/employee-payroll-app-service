package com.bridgelabz.integration;

import com.bridgelabz.controller.EmployeePayrollController;
import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.entity.Employee;
import com.bridgelabz.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeePayrollController.class)
@ActiveProfiles("test")
public class EmployeePayrollControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeePayrollService employeePayrollService;

    @Test
    void addEmployeeDataTest() throws Exception {
        when(employeePayrollService.addEmployeeEntities(any())).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/detail")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\",\"gender\":\"Male\"," +
                                "\"salary\":800000,\"departments\":[\"JavaDeveloper\",\"Manager\"]," +
                                "\"notes\":\"Reliable\",\"imagePath\":\"image1.jpg\",\"joiningDate\":\"12/16/2021\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void addEmployeeDataTest_WhenValidationFailed_ShouldGetBadRequest() throws Exception {
        when(employeePayrollService.addEmployeeEntities(any())).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/detail")
                        .content("{\"firstName\":\"sudip\",\"lastName\":\"panja\",\"gender\":\"Male\"," +
                                "\"salary\":800000,\"departments\":[\"JavaDeveloper\",\"Manager\"]," +
                                "\"notes\":\"Reliable\",\"imagePath\":\"image1.jpg\",\"joiningDate\":\"12/16/2021\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getEmployeeDetailsTest() throws Exception {
        when(employeePayrollService.getAllEmployeeData()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/details"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeDataByIdTest() throws Exception {
        Employee employee = new Employee();
        when(employeePayrollService.findEmployeeDataById(1)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/detail/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeDetailsTest() throws Exception {
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(600000.00);
        employeeDetailsDto.setDepartments(List.of("Electrical"));
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setImagePath("image1.jpg");
        employeeDetailsDto.setJoiningDate("12/12/2021");
        int employeeId = 1;
        when(employeePayrollService.updateEmployeeData(employeeId, employeeDetailsDto)).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/detail/1")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\",\"gender\":\"Male\"," +
                                "\"salary\":800000,\"departments\":[\"JavaDeveloper\",\"Manager\"]," +
                                "\"notes\":\"Reliable\",\"imagePath\":\"image2.jpg\",\"joiningDate\":\"12/16/2021\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());
    }

    @Test
    void updateEmployeeDetailsTest_WhenValidationFailed_ShouldGetBadRequest() throws Exception {
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setFirstName("Sudip");
        employeeDetailsDto.setLastName("Panja");
        employeeDetailsDto.setGender("Male");
        employeeDetailsDto.setSalary(600000.00);
        employeeDetailsDto.setDepartments(List.of("Electrical"));
        employeeDetailsDto.setNotes("Reliable,Adaptable,Self-Manageable,Communicative");
        employeeDetailsDto.setImagePath("image1.jpg");
        employeeDetailsDto.setJoiningDate("12/12/2021");
        int employeeId = 1;
        when(employeePayrollService.updateEmployeeData(employeeId, employeeDetailsDto)).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/detail/1")
                        .content("{\"firstName\":\"sudip\",\"lastName\":\"Panja\",\"gender\":\"male\"," +
                                "\"salary\":800000,\"departments\":[\"JavaDeveloper\",\"Manager\"]," +
                                "\"notes\":\"Reliable\",\"imagePath\":\"image2.jpg\",\"joiningDate\":\"12/16/2021\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEmployeeDetailsTest() throws Exception {
        when(employeePayrollService.deleteEmployeeDataById(1)).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/details/1")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\",\"gender\":\"Male\"," +
                                "\"salary\":800000,\"departments\":[\"JavaDeveloper\",\"Manager\"]," +
                                "\"notes\":\"Reliable\",\"imagePath\":\"image1.jpg\",\"joiningDate\":\"12/16/2021\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());
    }
}
