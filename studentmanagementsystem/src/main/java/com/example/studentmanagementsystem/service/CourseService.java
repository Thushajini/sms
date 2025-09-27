package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course createCourse(Course course) throws ExecutionException, InterruptedException {
        return courseRepository.save(course);
    }

    public Course getCourseById(String courseId) throws ExecutionException, InterruptedException{
        return courseRepository.findById(courseId);
    }

    public List<Course> getAllCourses() throws ExecutionException,InterruptedException{
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) throws ExecutionException, InterruptedException{
        return courseRepository.update(course);
    }

    public void deleteCourse(String courseId) throws ExecutionException,InterruptedException{
        courseRepository.deleteById(courseId);
    }
}
