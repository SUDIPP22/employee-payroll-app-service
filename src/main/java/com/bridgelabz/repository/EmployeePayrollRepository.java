package com.bridgelabz.repository;

import com.bridgelabz.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Purpose : To implement an interface which operate the database operation
 * for EmployeeDetailsDto Payroll Application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@Repository
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {
}
