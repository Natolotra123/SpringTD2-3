package com.example.td3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class controller {

    public List<Students> student =  new ArrayList<>();

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam String name){

        if (name == null || name.isBlank()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre name manquant");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
    }

    @PostMapping("/students")
    public ResponseEntity<String> students(@RequestBody List<Students> newStudents){

        try {
            student.addAll(newStudents);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(student.toString());
        } catch (Exception e){

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudents(@RequestHeader ("Accept")  String accept){

        try {
            if (accept == null){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("En-tête Accept manquaant");
            }

            if (accept.equals("text/palin")) {
                String list = student.stream()
                        .map(s -> s.getFirstname() + " " + s.getLastname() + " " + s.getAge())
                        .collect(Collectors.joining(", "));

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(list);
            }

            if (accept.equals("application/json")){
                String list = student.stream()
                        .map(s -> s.getFirstname() + " " + s.getLastname() + " " + s.getAge())
                        .collect(Collectors.joining(", "));

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(list);
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format non supporté");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
