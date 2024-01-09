package com.springboot.studentregistration.controller;

import com.springboot.studentregistration.model.Student;
import com.springboot.studentregistration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.springboot.studentregistration.exception.ErrorResponse.buildErrorResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Slf4j
@RequestMapping("/students/")
public class StudentController {
    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok().body(studentService.getAllStudent());
    }

    @GetMapping("{/id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
        log.info("Get student with id" + id);
        if (id < 1) {
            return ResponseEntity.badRequest().body(
                    buildErrorResponse("Student Id cannot be less than 1", BAD_REQUEST));
        }

        return studentService.getStudent(id)
                .map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
