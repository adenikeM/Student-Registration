package com.springboot.studentregistration.controller;

import com.springboot.studentregistration.model.Student;
import com.springboot.studentregistration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.springboot.studentregistration.exception.ErrorResponse.buildErrorResponse;
import static com.springboot.studentregistration.util.Validation.validateCreateStudentRequest;
import static com.springboot.studentregistration.util.Validation.validateUpdateStudent;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@Slf4j
@RequestMapping("/studentReg")
public class StudentController {

    public final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok().body(studentService.getAllStudent());
    }

    @GetMapping("/{id}")
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
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        log.info("Request to create student ==> {}", student);
        if(student.getId()!= null){
            log.info("student => {}", student);
            return validateCreateStudentRequest(student);
        }
        return ResponseEntity.ok().body(studentService.createStudent(student));
    }
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        if(student.getId() == null){
            return validateUpdateStudent(student);
        }
        Optional<Student> updatedStudent = studentService.updateStudent(student);
        if(updatedStudent.isPresent()){
            return ResponseEntity.ok(updatedStudent);
        } else{
            return ResponseEntity.badRequest().body(buildErrorResponse(
                    "Student with id " + student.getId() + "doesn't exist, kindly enter correct student id", BAD_REQUEST));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
