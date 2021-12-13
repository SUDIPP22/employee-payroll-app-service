package com.bridgelabz;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Purpose : To demonstrate employee payroll application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-06
 */
@SpringBootApplication
@EnableEncryptableProperties
public class EmployeePayrollAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppApplication.class, args);
    }

}
