package com.springboot.studentregistration.service;

import com.springboot.studentregistration.model.Course;
import com.springboot.studentregistration.model.Student;
import com.springboot.studentregistration.repository.CourseRepository;
import com.springboot.studentregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Optional<Student> getStudent(Integer id){
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student){
        List<Course> courseList = saveCoursesWithRepo(student.getCourses());
        student.setCourses(courseList);
        return studentRepository.save(student);
    }

    private List<Course> saveCoursesWithRepo(List<Course> courses) {

        return courseRepository.saveAll(courses);
    }
    public Optional<Student> updateStudent(Student student){
        studentRepository.findById(student.getId());
        if(student.getId() == null){
            throw new IllegalArgumentException("Student id cannot be null");
        }
        List<Course> courseList = saveCoursesWithRepo(student.getCourses());
        student.setCourses(courseList);
        return Optional.of(studentRepository.save(student));
    }
    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }
}
