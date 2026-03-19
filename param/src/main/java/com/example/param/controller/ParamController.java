package com.example.param.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParamController {

    private List<Students> students =  new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String students(@RequestBody List<Students> newStudents) {
        students.addAll(newStudents);

        return students.stream()
                .map(s -> s.getFirstname() + " " + s.getLastname() + " " + s.getAge())
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader ("Accept") String accept) {
        if (!accept.equals("text/plain")) {
            return "Format non supporté.";
        }

        return students.stream()
                .map(s -> s.getFirstname() + " " + s.getLastname() + " " + s.getAge())
                .collect(Collectors.joining(", "));
    }
}









