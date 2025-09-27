package com.example.studentmanagementsystem.controller;


import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student)throws ExecutionException, InterruptedException{
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) throws ExecutionException, InterruptedException {
        Student student = studentService.getStudentById(studentId);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @RequestBody Student student) throws ExecutionException, InterruptedException {
        student.setStudentId(studentId);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable String studentId) throws ExecutionException, InterruptedException {
        studentService.deleteStudent(studentId);
    }

//    @PostMapping("/{studentId}/enroll/{courseId}")
//    public ResponseEntity<String> enrollStudentInCourse(@PathVariable String studentId, @PathVariable String courseId) throws ExecutionException, InterruptedException {
//        return ResponseEntity.ok(studentService.enrollStudentInCourse(studentId, courseId));
//    }
}
