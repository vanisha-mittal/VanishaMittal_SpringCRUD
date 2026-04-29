package com.example._rdYearFinal.service;

import com.example._rdYearFinal.dto.StudentDto;
import com.example._rdYearFinal.entity.Student;
import com.example._rdYearFinal.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void create(StudentDto dto) {
        Student s = new Student(null, dto.getName(), dto.getEmail(), "course");
        repo.save(s);
    }

    public List<StudentDto> getAll() {
        return repo.findAll().stream()
                .map(s -> new StudentDto(s.getId().longValue(), s.getName(), s.getEmail()))
                .collect(Collectors.toList());
    }

    public StudentDto getById(Long id) {
        Student s = repo.findById(id.intValue());
        return new StudentDto(s.getId().longValue(), s.getName(), s.getEmail());
    }

    public void update(Long id, StudentDto dto) {
        Student s = new Student(id.intValue(), dto.getName(), dto.getEmail(), "course");
        repo.update(id.intValue(), s);
    }

    public void delete(Long id) {
        repo.delete(id.intValue());
    }
}