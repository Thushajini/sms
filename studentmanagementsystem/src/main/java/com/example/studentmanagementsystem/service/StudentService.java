package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.repository.CourseRepository;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student createStudent(Student student) throws ExecutionException, InterruptedException {
        if (student.getCourseId() != null && !student.getCourseId().isEmpty()) {
            for (String courseId : student.getCourseId()) {
                if (courseRepository.findById(courseId) == null) {
                    throw new IllegalArgumentException("Invalid course ID: " + courseId);
                }
            }
        }
        Student newStudent = new Student();
        newStudent.setTitle(student.getTitle());
        newStudent.setName(student.getName());
        newStudent.setAddress(student.getAddress());
        newStudent.setCity(student.getCity());
        newStudent.setCourseId(student.getCourseId());
        newStudent = studentRepository.save(newStudent);
        student.setStudentId(newStudent.getStudentId());
        return student;
    }

    public Student getStudentById(String studentId) throws ExecutionException, InterruptedException{
        return studentRepository.findById(studentId);
    }

    public List<Student> getAllStudents() throws ExecutionException,InterruptedException{
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) throws ExecutionException, InterruptedException{
        return studentRepository.update(student);
    }

    public void deleteStudent(String studentId) throws ExecutionException,InterruptedException{
        studentRepository.deleteById(studentId);
    }

//    public String enrollStudentInCourse(String studentId, String courseId) throws ExecutionException, InterruptedException {
//        Student student = getStudentById(studentId);
//        if (student != null) {
//            List<String> courseIds = student.getCourseId();
//            if (courseIds == null) {
//                courseIds = new ArrayList<>();
//            }
//
//            if (!courseIds.contains(courseId)) {
//                courseIds.add(courseId);
//                student.setCourseId(courseIds);
//                return studentRepository.save(student);
//            }
//        }
//            return "Student not found or already enrolled";
//    }
}
