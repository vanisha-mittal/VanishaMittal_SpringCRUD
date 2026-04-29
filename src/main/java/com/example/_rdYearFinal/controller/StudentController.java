package com.example._rdYearFinal.controller;

import com.example._rdYearFinal.dto.StudentDto;
import com.example._rdYearFinal.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<StudentDto> getAllStudents() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public String create(@RequestBody StudentDto dto) {
        service.create(dto);
        return "Student Created";
    }

    // UPDATE
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody StudentDto dto) {
        service.update(id, dto);
        return "Student Updated";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Student Deleted";
    }
}