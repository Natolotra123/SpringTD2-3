package com.example.TD4.validator;

import com.example.TD4.Student;
import com.example.TD4.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidor {

    public void validate(List<Student> students) {
        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("Reference est null ou blanc");
            }
            if (student.getFirstname() == null || student.getFirstname().isBlank()) {
                throw new BadRequestException("Firstname est null ou blanc");
            }
            if (student.getLastname() == null || student.getLastname().isBlank()) {
                throw new BadRequestException("Lastname est null ou blanc");
            }
        }
    }
}