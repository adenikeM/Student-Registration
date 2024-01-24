package com.springboot.studentregistration.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullName;
    private Integer age;
    private String address;
    private Gender gender;
    private LocalDate birthdate;
    @OneToMany
    private List <Course> courses;
}
