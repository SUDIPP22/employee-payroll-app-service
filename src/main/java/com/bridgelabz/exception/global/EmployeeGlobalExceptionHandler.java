package com.bridgelabz.exception.global;

import com.bridgelabz.dto.ResponseDto;
import com.bridgelabz.exception.custom.BadRequestException;
import com.bridgelabz.exception.custom.EmployeeCustomException;
import com.bridgelabz.exception.custom.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Purpose : This class is major class for handling the all exception which can be thrown
 * while the application is running in the server side, as this is the global exception handler
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-11
 */
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {


    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({EmployeeCustomException.class})
    public ResponseEntity<ResponseDto> handleEmployeeCustomException(EmployeeCustomException exception) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<ResponseDto> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    /**
     * Purpose : This method is created for handle the global exception which can occur while running the application
     *
     * @param exception : this parameter will be the exception which will going to help
     *                  for responding back if exception occurs
     * @return : the new response entity which will holds the response DTO
     * that consists the exception message and Http status
     */
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseDto> handleBadRequestException(BadRequestException exception) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }
}
