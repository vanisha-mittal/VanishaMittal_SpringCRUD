package com.example._rdYearFinal.repository;

import com.example._rdYearFinal.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student s) {
        jdbcTemplate.update(
                "INSERT INTO student(name,email,course) VALUES(?,?,?)",
                s.getName(), s.getEmail(), s.getCourse()
        );
    }

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM student", (rs, rowNum) ->
                new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                ));
    }

    public Student findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM student WHERE id=?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("course")
                        )
        );
    }

    public void update(int id, Student s) {
        jdbcTemplate.update(
                "UPDATE student SET name=?, email=?, course=? WHERE id=?",
                s.getName(), s.getEmail(), s.getCourse(), id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM student WHERE id=?", id);
    }
}