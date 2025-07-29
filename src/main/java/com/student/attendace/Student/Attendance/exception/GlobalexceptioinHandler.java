package com.student.attendace.Student.Attendance.exception;

import com.student.attendace.Student.Attendance.model.ResponseError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalexceptioinHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex){
        return errorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex){
        Throwable rootCause = ex.getRootCause();
        String message = "";
        if(rootCause instanceof SQLIntegrityConstraintViolationException){
            message = "Duplicate entry or constraint violation " + rootCause.getMessage();
        }
        else{
            message = "Data integrity violation error " + ex.getMessage();
        }

        return new ResponseEntity<>(new ResponseError(LocalDateTime.now(), message, HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT);
    }


    private ResponseEntity<Object> errorResponse(Exception ex, HttpStatus status){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), status.value());
        return new ResponseEntity<>(error, status);
    }
}
