package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String createStudent(Student student) throws ExecutionException, InterruptedException {
        return studentRepository.saveStudent(student);
    }

    public Student getStudentById(String studentId) throws ExecutionException, InterruptedException{
        return studentRepository.getStudentById(studentId);
    }

    public List<Student> getAllStudents() throws ExecutionException,InterruptedException{
        return studentRepository.getAllStudents();
    }

    public String updateStudent(Student student) throws ExecutionException, InterruptedException{
        return studentRepository.updateStudent(student);
    }

    public String deleteStudent(String studentId) throws ExecutionException,InterruptedException{
        return studentRepository.deleteStudentById(studentId);
    }

    public String enrollStudentInCourse(String studentId, String courseId) throws ExecutionException, InterruptedException {
        Student student = getStudentById(studentId);
        if (student != null) {
            List<String> courseIds = student.getCourseIds();
            if (courseIds == null) {
                courseIds = new ArrayList<>();
            }

            if (!courseIds.contains(courseId)) {
                courseIds.add(courseId);
                student.setCourseIds(courseIds);
                return studentRepository.saveStudent(student);
            }
        }
            return "Student not found or already enrolled";
    }
}
