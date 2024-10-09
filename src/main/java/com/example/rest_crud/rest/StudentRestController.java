package com.example.rest_crud.rest;

import com.example.rest_crud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Josh", "Tom"));
        theStudents.add(new Student("Niko", "Bellic"));
        theStudents.add(new Student("Tommy", "Selby"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId < 0 || studentId >= theStudents.size()) {
            throw new StudentNotFoundException("Student not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

}
