package com.student.attendace.Student.Attendance.exception;

import com.student.attendace.Student.Attendance.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalexceptioinHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex){
        return errorResponse(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> errorResponse(Exception ex, HttpStatus status){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), status.value());
        return new ResponseEntity<>(error, status);
    }
}
