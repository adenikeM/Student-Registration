package com.springboot.studentregistration.service;

import com.springboot.studentregistration.model.Student;
import com.springboot.studentregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Optional<Student> getStudent(Integer id){
        return studentRepository.findById(id);
    }
}
