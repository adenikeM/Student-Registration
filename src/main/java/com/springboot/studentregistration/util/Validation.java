package com.springboot.studentregistration.util;

import com.springboot.studentregistration.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.springboot.studentregistration.exception.ErrorResponse.buildErrorResponse;

public class Validation {


    public static ResponseEntity<?> validateCreateStudentRequest(Student student) {
        return ResponseEntity.badRequest()
                             .body(buildErrorResponse("ID should be null, Id = "
                                     + student.getId(), HttpStatus.BAD_REQUEST));
    }
    public static ResponseEntity<?> validateUpdateStudent(Student student) {
        return ResponseEntity.badRequest()
                             .body(buildErrorResponse("ID cannot be null, Id = "
                                     + student.getId(), HttpStatus.BAD_REQUEST));
    }
}
