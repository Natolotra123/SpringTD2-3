package com.example.TD4.controller;

import com.example.TD4.Student;
import com.example.TD4.exception.BadRequestException;
import com.example.TD4.stockage.StudentStockage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private StudentStockage studentStockage;

    public StudentController(StudentStockage studentStockage) {
        this.studentStockage = studentStockage;
    }

    @PostMapping("/student")
    public ResponseEntity addStudent(@RequestBody List<Student> newStudents) {
        try {
            List<Student> students = studentStockage.addStudent(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(students);
        }catch (BadRequestException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
