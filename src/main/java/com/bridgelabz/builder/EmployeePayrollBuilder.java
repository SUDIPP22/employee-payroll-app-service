package com.bridgelabz.builder;

import com.bridgelabz.dto.EmployeeDetailsDto;
import com.bridgelabz.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which holds all the building related application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-10
 */
@Component
public class EmployeePayrollBuilder {

    /**
     * Purpose : This method is created to copy the properties of simple POJO to @Entity class
     *
     * @param employeeDetailsDto : This the POJO class's object which is the source for copying the properties
     * @param employee           : This is the Entity class's object which is the destination for that copied properties
     * @return the object of Employee class
     */
    public Employee buildEmployeeEntity(EmployeeDetailsDto employeeDetailsDto, Employee employee) {
        BeanUtils.copyProperties(employeeDetailsDto, employee);
        return employee;
    }
}
