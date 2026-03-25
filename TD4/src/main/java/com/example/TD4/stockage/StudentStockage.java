package com.example.TD4.stockage;

import com.example.TD4.Student;
import com.example.TD4.validator.StudentValidor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentStockage {

    private final List<Student> studentList = new ArrayList<>();
    private final StudentValidor studentValidor;

    public StudentStockage(StudentValidor studentValidor) {
        this.studentValidor = studentValidor;
    }

    public List<Student> addStudent(List<Student> newStudentList) {
        studentValidor.validate(newStudentList);
        studentList.addAll(newStudentList);
        return studentList;
    }
}